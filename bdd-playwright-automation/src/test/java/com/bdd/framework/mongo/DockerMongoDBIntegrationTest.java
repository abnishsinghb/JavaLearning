package com.bdd.framework.mongo;

import com.framework.database.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test for Docker-based MongoDB setup
 * Tests MongoDB connectivity, database operations, and Docker environment
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DockerMongoDBIntegrationTest {
    
    private static final Logger logger = LoggerFactory.getLogger(DockerMongoDBIntegrationTest.class);
    private static MongoDBConnection mongoConnection;
    
    @BeforeAll
    static void setupClass() {
        logger.info("Starting Docker MongoDB Integration Tests");
        
        // Wait for Docker containers to be ready
        try {
            Thread.sleep(5000); // Give containers time to start
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        mongoConnection = MongoDBConnection.getInstance();
        assertNotNull(mongoConnection, "MongoDB connection should be initialized");
    }
    
    @AfterAll
    static void tearDownClass() {
        if (mongoConnection != null) {
            mongoConnection.closeConnection();
        }
        logger.info("Docker MongoDB Integration Tests completed");
    }
    
    @Test
    @Order(1)
    @DisplayName("Test MongoDB Docker Container Connection")
    void testDockerConnection() {
        logger.info("Testing Docker MongoDB connection...");
        
        boolean isConnected = mongoConnection.isConnected();
        assertTrue(isConnected, "MongoDB connection should be healthy");
        
        MongoDatabase database = mongoConnection.getDatabase();
        assertNotNull(database, "Database connection should not be null");
        assertEquals("testdb", database.getName(), "Should connect to testdb database");
        
        logger.info("✅ Docker MongoDB connection test passed");
    }
    
    @Test
    @Order(2)
    @DisplayName("Test MongoDB Environment")
    void testMongoDBEnvironment() {
        logger.info("Testing MongoDB Docker environment...");
        
        boolean isDockerEnv = mongoConnection.isDockerEnvironment();
        assertTrue(isDockerEnv, "Should detect Docker environment");
        
        String connectionInfo = mongoConnection.getConnectionInfo();
        assertNotNull(connectionInfo, "Connection info should be available");
        logger.info("Connection info: {}", connectionInfo);
        
        logger.info("✅ MongoDB environment test passed");
    }
    
    @Test
    @Order(3)
    @DisplayName("Test Database Operations")
    void testDatabaseOperations() {
        logger.info("Testing basic database operations...");
        
        MongoDatabase database = mongoConnection.getDatabase();
        MongoCollection<Document> collection = database.getCollection("integration_test");
        
        // Clear collection
        collection.deleteMany(new Document());
        
        // Test insert
        Document testDoc = new Document("name", "Docker Test")
            .append("timestamp", Instant.now().toString())
            .append("environment", "docker")
            .append("test_id", "integration_001");
        
        collection.insertOne(testDoc);
        
        // Test find
        Document found = collection.find(new Document("test_id", "integration_001")).first();
        assertNotNull(found, "Should find inserted document");
        assertEquals("Docker Test", found.getString("name"));
        assertEquals("docker", found.getString("environment"));
        
        // Test update
        collection.updateOne(
            new Document("test_id", "integration_001"),
            new Document("$set", new Document("status", "verified"))
        );
        
        Document updated = collection.find(new Document("test_id", "integration_001")).first();
        assertEquals("verified", updated.getString("status"));
        
        // Test count
        long count = collection.countDocuments();
        assertEquals(1L, count, "Should have exactly one document");
        
        logger.info("✅ Database operations test passed");
    }
    
    @Test
    @Order(4)
    @DisplayName("Test Multiple Database Access")
    void testMultipleDatabaseAccess() {
        logger.info("Testing multiple database access...");
        
        // Test accessing different databases
        String[] databases = {"testdb", "testdb_qa", "testdb_staging"};
        
        for (String dbName : databases) {
            MongoDatabase db = mongoConnection.getDatabase(dbName);
            assertNotNull(db, "Should access " + dbName + " database");
            assertEquals(dbName, db.getName(), "Database name should match");
            
            // Test collection access
            MongoCollection<Document> collection = db.getCollection("multi_db_test");
            
            Document testDoc = new Document("database", dbName)
                .append("timestamp", Instant.now().toString())
                .append("test", "multi_db_access");
            
            collection.insertOne(testDoc);
            
            long count = collection.countDocuments(new Document("database", dbName));
            assertTrue(count > 0, "Should insert document in " + dbName);
        }
        
        logger.info("✅ Multiple database access test passed");
    }
    
    @Test
    @Order(5)
    @DisplayName("Test Connection Pool Performance")
    void testConnectionPoolPerformance() {
        logger.info("Testing connection pool performance...");
        
        MongoDatabase database = mongoConnection.getDatabase();
        MongoCollection<Document> collection = database.getCollection("performance_test");
        
        // Clear collection
        collection.deleteMany(new Document());
        
        int documentCount = 100;
        Instant startTime = Instant.now();
        
        // Insert multiple documents to test connection pooling
        List<Document> documents = new ArrayList<>();
        for (int i = 0; i < documentCount; i++) {
            documents.add(new Document("index", i)
                .append("data", "performance_test_" + i)
                .append("timestamp", Instant.now().toString()));
        }
        
        collection.insertMany(documents);
        
        Duration insertDuration = Duration.between(startTime, Instant.now());
        
        // Test concurrent reads
        startTime = Instant.now();
        long totalCount = collection.countDocuments();
        Duration countDuration = Duration.between(startTime, Instant.now());
        
        assertEquals(documentCount, totalCount, "Should insert all documents");
        assertTrue(insertDuration.toMillis() < 5000, "Insert should complete within 5 seconds");
        assertTrue(countDuration.toMillis() < 1000, "Count should complete within 1 second");
        
        logger.info("✅ Performance test passed - Insert: {}ms, Count: {}ms", 
            insertDuration.toMillis(), countDuration.toMillis());
    }
    
    @Test
    @Order(6)
    @DisplayName("Test Basic Operations")
    void testBasicOperations() {
        logger.info("Testing MongoDB basic operations...");
        
        MongoDatabase database = mongoConnection.getDatabase();
        MongoCollection<Document> collection = database.getCollection("operation_test");
        
        // Clear collection
        collection.deleteMany(new Document());
        
        // Test simple operations
        collection.insertOne(new Document("operation", "test1").append("amount", 100));
        collection.insertOne(new Document("operation", "test2").append("amount", 200));
        
        long count = collection.countDocuments();
        assertEquals(2L, count, "Should insert both documents");
        
        // Verify data
        Document doc1 = collection.find(new Document("operation", "test1")).first();
        Document doc2 = collection.find(new Document("operation", "test2")).first();
        
        assertNotNull(doc1, "First document should exist");
        assertNotNull(doc2, "Second document should exist");
        assertEquals(100, doc1.getInteger("amount"));
        assertEquals(200, doc2.getInteger("amount"));
        
        logger.info("✅ Basic operations test passed");
    }
    
    @Test
    @Order(7)
    @DisplayName("Test Index Operations")
    void testIndexOperations() {
        logger.info("Testing MongoDB index operations...");
        
        MongoDatabase database = mongoConnection.getDatabase();
        MongoCollection<Document> collection = database.getCollection("index_test");
        
        // Clear collection
        collection.deleteMany(new Document());
        
        // Create compound index
        collection.createIndex(new Document("email", 1).append("status", 1));
        
        // Insert test data
        List<Document> testData = Arrays.asList(
            new Document("email", "user1@test.com").append("status", "active").append("score", 95),
            new Document("email", "user2@test.com").append("status", "inactive").append("score", 87),
            new Document("email", "user3@test.com").append("status", "active").append("score", 92)
        );
        
        collection.insertMany(testData);
        
        // Test index usage with queries
        long activeUsers = collection.countDocuments(new Document("status", "active"));
        assertEquals(2L, activeUsers, "Should find 2 active users");
        
        Document user1 = collection.find(new Document("email", "user1@test.com")).first();
        assertNotNull(user1, "Should find user1 by email index");
        assertEquals("active", user1.getString("status"));
        
        logger.info("✅ Index operations test passed");
    }
    
    @Test
    @Order(8)
    @DisplayName("Test Aggregation Pipeline")
    void testAggregationPipeline() {
        logger.info("Testing MongoDB aggregation pipeline...");
        
        MongoDatabase database = mongoConnection.getDatabase();
        MongoCollection<Document> collection = database.getCollection("aggregation_test");
        
        // Clear collection
        collection.deleteMany(new Document());
        
        // Insert test data for aggregation
        List<Document> salesData = Arrays.asList(
            new Document("product", "laptop").append("category", "electronics").append("price", 1000).append("quantity", 2),
            new Document("product", "mouse").append("category", "electronics").append("price", 25).append("quantity", 10),
            new Document("product", "book").append("category", "education").append("price", 15).append("quantity", 5),
            new Document("product", "phone").append("category", "electronics").append("price", 800).append("quantity", 3)
        );
        
        collection.insertMany(salesData);
        
        // Aggregation pipeline: group by category and calculate totals
        List<Document> pipeline = Arrays.asList(
            new Document("$group", new Document("_id", "$category")
                .append("totalRevenue", new Document("$sum", new Document("$multiply", Arrays.asList("$price", "$quantity"))))
                .append("itemCount", new Document("$sum", 1))
                .append("avgPrice", new Document("$avg", "$price"))),
            new Document("$sort", new Document("totalRevenue", -1))
        );
        
        List<Document> results = collection.aggregate(pipeline).into(new ArrayList<>());
        
        assertFalse(results.isEmpty(), "Aggregation should return results");
        
        // Find electronics category result
        Document electronicsResult = results.stream()
            .filter(doc -> "electronics".equals(doc.getString("_id")))
            .findFirst()
            .orElse(null);
        
        assertNotNull(electronicsResult, "Should have electronics category result");
        assertEquals(3, electronicsResult.getInteger("itemCount"), "Electronics should have 3 items");
        
        // Total revenue: (1000*2) + (25*10) + (800*3) = 2000 + 250 + 2400 = 4650
        assertEquals(4650, electronicsResult.getInteger("totalRevenue"), "Electronics revenue should be 4650");
        
        logger.info("✅ Aggregation pipeline test passed");
    }
    
    @Test
    @Order(9)
    @DisplayName("Test Connection Resilience")
    void testConnectionResilience() {
        logger.info("Testing connection resilience...");
        
        // Test connection health check
        assertTrue(mongoConnection.isConnected(), "Connection should be healthy initially");
        
        // Simulate network delay by performing operations
        MongoDatabase database = mongoConnection.getDatabase();
        MongoCollection<Document> collection = database.getCollection("resilience_test");
        
        // Multiple rapid operations to test connection stability
        for (int i = 0; i < 10; i++) {
            Document doc = new Document("iteration", i)
                .append("timestamp", Instant.now().toString())
                .append("test", "resilience");
            
            collection.insertOne(doc);
            
            // Brief pause
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        long count = collection.countDocuments(new Document("test", "resilience"));
        assertEquals(10L, count, "All operations should complete successfully");
        
        // Verify connection is still healthy
        assertTrue(mongoConnection.isConnected(), "Connection should remain healthy after operations");
        
        logger.info("✅ Connection resilience test passed");
    }
    
    @Test
    @Order(10)
    @DisplayName("Test Cleanup and Final Verification")
    void testCleanupAndVerification() {
        logger.info("Testing cleanup and final verification...");
        
        MongoDatabase database = mongoConnection.getDatabase();
        
        // List all test collections
        String[] testCollections = {
            "integration_test", "multi_db_test", "performance_test", 
            "operation_test", "index_test", "aggregation_test", "resilience_test"
        };
        
        int cleanedCollections = 0;
        for (String collectionName : testCollections) {
            try {
                MongoCollection<Document> collection = database.getCollection(collectionName);
                long docCount = collection.countDocuments();
                
                if (docCount > 0) {
                    collection.deleteMany(new Document());
                    cleanedCollections++;
                    logger.info("Cleaned collection '{}' with {} documents", collectionName, docCount);
                }
            } catch (Exception e) {
                logger.warn("Could not clean collection '{}': {}", collectionName, e.getMessage());
            }
        }
        
        // Final health check
        boolean finalHealthCheck = mongoConnection.isConnected();
        assertTrue(finalHealthCheck, "MongoDB should be healthy after all tests");
        
        logger.info("✅ Cleanup completed - {} collections cleaned", cleanedCollections);
        logger.info("🎉 All Docker MongoDB integration tests passed successfully!");
    }
}
