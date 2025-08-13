#!/bin/bash
# DemoQA Test Runner for Docker CI/CD
set -e

echo "🚀 Starting DemoQA Test Suite in Docker..."

# Run MongoDB Integration Tests
echo "📊 Running MongoDB Integration Tests..."
mvn clean test -Dtest=MongoDBIntegrationTest -B

# Generate Allure reports
echo "📈 Generating Allure Reports..."
mvn allure:report -B

echo "✅ DemoQA Tests completed successfully!"