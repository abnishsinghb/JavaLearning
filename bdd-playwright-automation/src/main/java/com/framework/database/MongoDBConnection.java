package com.framework.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.bson.Document;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.concurrent.TimeUnit;

/**
 * MongoDB Connection Manager for Docker-based Testing
 * 
 * Features:
 * - Docker container connectivity
 * - Singleton pattern for connection management
 * - Health check capabilities
 * - Connection pooling
 * - Environment-specific configuration
 * - Error handling and logging
 * 
 * @author Framework Team
 * @version 2.0
 */
public class MongoDBConnection {
    private static final Logger LOGGER = Logger.getLogger(MongoDBConnection.class.getName());
    
    private static MongoDBConnection instance;
    private MongoClient mongoClient;
    private MongoDatabase database;
    
    // Docker-based MongoDB Configuration
    private static final String DEFAULT_DOCKER_URI = "mongodb://admin:admin123@localhost:27017/testdb?authSource=admin";
    private static final String MONGO_URI = System.getProperty("mongodb.uri", DEFAULT_DOCKER_URI);
    private static final String DATABASE_NAME = System.getProperty("mongodb.database", "testdb");
    private static final String ENV_NAME = System.getProperty("mongodb.environment", "DOCKER");
    
    // Connection pool settings
    private static final int MIN_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 20;
    private static final int CONNECTION_TIMEOUT_MS = 30000;
    private static final int SOCKET_TIMEOUT_MS = 30000;
    private static final int SERVER_SELECTION_TIMEOUT_MS = 30000;
    
    private MongoDBConnection() {
        initializeConnection();
    }
    
    public static MongoDBConnection getInstance() {
        if (instance == null) {
            synchronized (MongoDBConnection.class) {
                if (instance == null) {
                    instance = new MongoDBConnection();
                }
            }
        }
        return instance;
    }
    
    private void initializeConnection() {
        try {
            LOGGER.info("Connecting to MongoDB Docker environment: " + ENV_NAME);
            LOGGER.info("Connection URI: " + maskConnectionString(MONGO_URI));
            
            // Suppress MongoDB driver logging
            Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
            
            // Configure MongoDB client settings
            MongoClientSettings.Builder settingsBuilder = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(MONGO_URI))
                .applyToConnectionPoolSettings(builder -> 
                    builder.minSize(MIN_POOL_SIZE)
                           .maxSize(MAX_POOL_SIZE)
                           .maxConnectionIdleTime(60, TimeUnit.SECONDS)
                           .maxConnectionLifeTime(30, TimeUnit.MINUTES))
                .applyToSocketSettings(builder -> 
                    builder.connectTimeout(CONNECTION_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                           .readTimeout(SOCKET_TIMEOUT_MS, TimeUnit.MILLISECONDS))
                .applyToServerSettings(builder -> 
                    builder.heartbeatFrequency(10, TimeUnit.SECONDS)
                           .minHeartbeatFrequency(500, TimeUnit.MILLISECONDS))
                .applyToClusterSettings(builder -> 
                    builder.serverSelectionTimeout(SERVER_SELECTION_TIMEOUT_MS, TimeUnit.MILLISECONDS));
            
            mongoClient = MongoClients.create(settingsBuilder.build());
            database = mongoClient.getDatabase(DATABASE_NAME);
            
            // Test connection with retry logic
            testConnectionWithRetry();
            
            LOGGER.info("Successfully connected to MongoDB Docker database: " + DATABASE_NAME);
            
        } catch (Exception e) {
            LOGGER.severe("Failed to connect to MongoDB Docker container: " + e.getMessage());
            throw new RuntimeException("MongoDB Docker connection failed", e);
        }
    }
    
    private void testConnectionWithRetry() {
        int maxRetries = 5;
        int retryDelayMs = 2000;
        
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                database.runCommand(new Document("ping", 1));
                LOGGER.info("MongoDB connection test successful on attempt " + attempt);
                return;
            } catch (Exception e) {
                LOGGER.warning("MongoDB connection test failed on attempt " + attempt + ": " + e.getMessage());
                
                if (attempt == maxRetries) {
                    throw new RuntimeException("Failed to connect to MongoDB after " + maxRetries + " attempts", e);
                }
                
                try {
                    Thread.sleep(retryDelayMs);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Connection retry interrupted", ie);
                }
            }
        }
    }
    
    public MongoDatabase getDatabase() {
        if (database == null) {
            throw new IllegalStateException("MongoDB database not initialized");
        }
        return database;
    }
    
    public MongoDatabase getDatabase(String databaseName) {
        if (mongoClient == null) {
            throw new IllegalStateException("MongoDB client not initialized");
        }
        return mongoClient.getDatabase(databaseName);
    }
    
    public MongoCollection<Document> getCollection(String collectionName) {
        return getDatabase().getCollection(collectionName);
    }
    
    public MongoCollection<Document> getCollection(String databaseName, String collectionName) {
        return getDatabase(databaseName).getCollection(collectionName);
    }
    
    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            LOGGER.info("MongoDB connection closed");
        }
    }
    
    // Health check method
    public boolean isConnected() {
        try {
            if (database == null) return false;
            database.runCommand(new Document("ping", 1));
            return true;
        } catch (Exception e) {
            LOGGER.warning("MongoDB connection health check failed: " + e.getMessage());
            return false;
        }
    }
    
    // Environment-specific database access
    public MongoDatabase getTestDatabase() {
        return getDatabase("testdb");
    }
    
    public MongoDatabase getQADatabase() {
        return getDatabase("testdb_qa");
    }
    
    public MongoDatabase getStagingDatabase() {
        return getDatabase("testdb_staging");
    }
    
    // Get connection info
    public String getConnectionInfo() {
        return String.format("Environment: %s, Database: %s, URI: %s", 
                           ENV_NAME, DATABASE_NAME, maskConnectionString(MONGO_URI));
    }
    
    private String maskConnectionString(String uri) {
        if (uri == null) return "null";
        return uri.replaceAll("admin:admin123", "***:***")
                 .replaceAll("testuser:testpass123", "***:***");
    }
    
    // Get replica set status
    public Document getReplicaSetStatus() {
        try {
            return database.runCommand(new Document("replSetGetStatus", 1));
        } catch (Exception e) {
            LOGGER.warning("Failed to get replica set status: " + e.getMessage());
            return null;
        }
    }
    
    // Docker-specific utilities
    public boolean isDockerEnvironment() {
        return ENV_NAME.equals("DOCKER") || MONGO_URI.contains("localhost:27017");
    }
    
    public void waitForReplicaSet() {
        int maxWaitTime = 60; // seconds
        int waitInterval = 2; // seconds
        
        for (int waited = 0; waited < maxWaitTime; waited += waitInterval) {
            try {
                Document status = getReplicaSetStatus();
                if (status != null && status.getInteger("ok", 0) == 1) {
                    LOGGER.info("Replica set is ready");
                    return;
                }
            } catch (Exception e) {
                // Continue waiting
            }
            
            try {
                Thread.sleep(waitInterval * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Wait for replica set interrupted", e);
            }
        }
        
        throw new RuntimeException("Replica set not ready after " + maxWaitTime + " seconds");
    }
}
