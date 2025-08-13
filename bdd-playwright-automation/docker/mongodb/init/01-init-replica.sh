#!/bin/bash

# MongoDB Standalone Setup Script for Docker Container
# This script performs basic health checks and initialization

echo "🚀 Starting MongoDB standalone initialization..."

# Wait for MongoDB to be ready
echo "⏳ Waiting for MongoDB to start..."
until mongosh --eval "print("MongoDB is ready")" > /dev/null 2>&1; do
  echo "   MongoDB not ready yet, waiting..."
  sleep 2
done

echo "✅ MongoDB is ready and accepting connections"

# Basic health check
echo "🔍 Performing health check..."
mongosh --eval "
  try {
    var result = db.adminCommand('ping');
    if (result.ok === 1) {
      print('✅ MongoDB health check passed');
    } else {
      print('❌ MongoDB health check failed');
    }
  } catch (error) {
    print('❌ MongoDB health check error: ' + error);
  }
"

echo "✅ MongoDB standalone initialization completed successfully!"
