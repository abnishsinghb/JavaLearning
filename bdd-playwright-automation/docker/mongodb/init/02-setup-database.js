// MongoDB Database Initialization Script for Docker
// This script sets up databases, users, and initial data for testing

// Switch to admin database to create users
db = db.getSiblingDB('admin');

print('🔧 Setting up MongoDB databases and users...');

// Create test databases
const databases = ['testdb', 'testdb_qa', 'testdb_staging'];

databases.forEach(function(dbName) {
    print('📚 Setting up database: ' + dbName);
    
    // Switch to the database
    const targetDb = db.getSiblingDB(dbName);
    
    // Create a test user for this database (optional, admin user can access all)
    try {
        targetDb.createUser({
            user: dbName + '_user',
            pwd: 'test123',
            roles: [
                { role: 'readWrite', db: dbName }
            ]
        });
        print('✅ Created user: ' + dbName + '_user');
    } catch (e) {
        print('ℹ️  User ' + dbName + '_user already exists or creation failed: ' + e.message);
    }
    
    // Create collections with sample data
    print('📦 Creating collections for ' + dbName + '...');
    
    // Users collection
    const usersCollection = targetDb.getCollection('users');
    usersCollection.insertMany([
        {
            userId: 1,
            username: 'testuser1',
            email: 'testuser1@example.com',
            status: 'active',
            createdAt: new Date(),
            profile: {
                firstName: 'Test',
                lastName: 'User1',
                age: 25
            }
        },
        {
            userId: 2,
            username: 'testuser2',
            email: 'testuser2@example.com',
            status: 'inactive',
            createdAt: new Date(),
            profile: {
                firstName: 'Test',
                lastName: 'User2',
                age: 30
            }
        }
    ]);
    print('✅ Inserted sample users');
    
    // Orders collection
    const ordersCollection = targetDb.getCollection('orders');
    ordersCollection.insertMany([
        {
            orderId: 1,
            userId: 1,
            items: [
                { productId: 1, name: 'Laptop', quantity: 1, price: 999.99 },
                { productId: 2, name: 'Mouse', quantity: 2, price: 25.99 }
            ],
            totalAmount: 1051.97,
            status: 'completed',
            orderDate: new Date()
        },
        {
            orderId: 2,
            userId: 2,
            items: [
                { productId: 3, name: 'Keyboard', quantity: 1, price: 79.99 }
            ],
            totalAmount: 79.99,
            status: 'pending',
            orderDate: new Date()
        }
    ]);
    print('✅ Inserted sample orders');
    
    // Products collection
    const productsCollection = targetDb.getCollection('products');
    productsCollection.insertMany([
        {
            productId: 1,
            name: 'Laptop',
            category: 'electronics',
            price: 999.99,
            inStock: true,
            quantity: 50,
            description: 'High-performance laptop for testing'
        },
        {
            productId: 2,
            name: 'Mouse',
            category: 'electronics',
            price: 25.99,
            inStock: true,
            quantity: 100,
            description: 'Wireless mouse for testing'
        },
        {
            productId: 3,
            name: 'Keyboard',
            category: 'electronics',
            price: 79.99,
            inStock: true,
            quantity: 75,
            description: 'Mechanical keyboard for testing'
        }
    ]);
    print('✅ Inserted sample products');
    
    // Create useful indexes
    usersCollection.createIndex({ userId: 1 }, { unique: true });
    usersCollection.createIndex({ email: 1 }, { unique: true });
    usersCollection.createIndex({ username: 1 });
    
    ordersCollection.createIndex({ orderId: 1 }, { unique: true });
    ordersCollection.createIndex({ userId: 1 });
    ordersCollection.createIndex({ status: 1 });
    
    productsCollection.createIndex({ productId: 1 }, { unique: true });
    productsCollection.createIndex({ category: 1 });
    productsCollection.createIndex({ name: 1 });
    
    print('✅ Created indexes for ' + dbName);
});

print('🎉 MongoDB database initialization completed successfully!');
print('📊 Available databases: ' + databases.join(', '));
print('👤 Admin user: admin/admin123');
print('🔗 Connection: mongodb://admin:admin123@localhost:27017/testdb?authSource=admin');
