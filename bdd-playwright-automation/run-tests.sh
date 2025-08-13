#!/bin/bash

# CI/CD Test Execution Script
# Runs MongoDB integration tests in CI/CD environment with comprehensive reporting

set -e

# Colors for output
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
RED='\033[0;31m'
PURPLE='\033[0;35m'
NC='\033[0m' # No Color

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_info() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

print_header() {
    echo -e "${PURPLE}[CI/CD]${NC} $1"
}

# Configuration
MONGODB_CONTAINER_NAME="mongodb-test"
MONGODB_PORT="27017"
MONGODB_USER="admin"
MONGODB_PASS="admin123"
MAX_WAIT_TIME=60
TEST_RESULTS_DIR="target/surefire-reports"
ALLURE_RESULTS_DIR="allure-results"
ALLURE_REPORT_DIR="target/site/allure-maven-plugin"

# Function to check if Docker is available
check_docker() {
    print_info "🐳 Checking Docker availability..."
    
    if ! command -v docker >/dev/null 2>&1; then
        print_error "❌ Docker is not installed or not in PATH"
        exit 1
    fi
    
    if ! docker info >/dev/null 2>&1; then
        print_error "❌ Docker daemon is not running or not accessible"
        exit 1
    fi
    
    print_success "✅ Docker is available"
}

# Function to start MongoDB container
start_mongodb() {
    print_info "🗄️ Setting up MongoDB container for testing..."
    
    # Check if MongoDB is already running
    if docker ps -q --filter "name=$MONGODB_CONTAINER_NAME" | grep -q .; then
        print_info "🔄 MongoDB test container already running"
        return 0
    fi
    
    # Check if port 27017 is already in use
    if lsof -i :27017 >/dev/null 2>&1 || docker ps -q --filter "publish=27017" | grep -q .; then
        print_warning "⚠️  Port 27017 is already in use. Using existing MongoDB instance."
        # Wait a moment for any existing MongoDB to be fully ready
        sleep 2
        return 0
    fi
    
    # Remove any stopped containers with the same name
    docker rm -f $MONGODB_CONTAINER_NAME >/dev/null 2>&1 || true
    
    # Start MongoDB container with label to identify it as our test container
    print_info "🚀 Starting MongoDB container..."
    local container_id=$(docker run -d \
        --name $MONGODB_CONTAINER_NAME \
        --label ci-test=true \
        -p 27017:27017 \
        -e MONGO_INITDB_ROOT_USERNAME=$MONGODB_USER \
        -e MONGO_INITDB_ROOT_PASSWORD=$MONGODB_PASS \
        mongo:7.0)
    
    if [ $? -eq 0 ]; then
        print_success "✅ MongoDB container started: $container_id"
        return 0
    else
        print_error "❌ Failed to start MongoDB container"
        return 1
    fi
}

# Function to wait for MongoDB to be ready
wait_for_mongodb() {
    print_info "⏳ Waiting for MongoDB to be ready..."
    
    local wait_time=0
    
    # Check if we have our own container or an existing MongoDB instance
    local mongodb_available=false
    
    while [ $wait_time -lt $MAX_WAIT_TIME ]; do
        # Try to connect to MongoDB regardless of whether it's our container or existing instance
        if docker exec $MONGODB_CONTAINER_NAME mongosh --quiet --eval "db.runCommand('ping')" >/dev/null 2>&1; then
            mongodb_available=true
            break
        elif mongosh --quiet --eval "db.runCommand('ping')" >/dev/null 2>&1; then
            # MongoDB is available locally (not in container)
            mongodb_available=true
            break
        elif nc -z localhost 27017 >/dev/null 2>&1; then
            # MongoDB port is open, assume it's ready
            mongodb_available=true
            break
        fi
        
        sleep 2
        wait_time=$((wait_time + 2))
        print_info "⏳ Still waiting... (${wait_time}s)"
    done
    
    if [ "$mongodb_available" = "true" ]; then
        print_success "✅ MongoDB is ready (waited ${wait_time}s)"
        return 0
    else
        print_error "❌ MongoDB failed to start within ${MAX_WAIT_TIME}s"
        # Only show logs if we have our container
        if docker ps -q --filter "name=$MONGODB_CONTAINER_NAME" | grep -q .; then
            docker logs $MONGODB_CONTAINER_NAME
        fi
        exit 1
    fi
}

# Function to run tests
run_tests() {
    print_info "🧪 Running MongoDB integration tests..."
    
    # Clean previous results
    rm -rf $TEST_RESULTS_DIR $ALLURE_RESULTS_DIR $ALLURE_REPORT_DIR 2>/dev/null || true
    
    # Set environment variables for tests
    export MONGODB_HOST="localhost"
    export MONGODB_PORT=$MONGODB_PORT
    export MONGODB_USER=$MONGODB_USER
    export MONGODB_PASS=$MONGODB_PASS
    
    # Run tests with Maven
    if mvn clean test -Dtest=DockerMongoDBIntegrationTest; then
        print_success "✅ All tests passed successfully"
        return 0
    else
        print_error "❌ Some tests failed"
        return 1
    fi
}

# Function to generate reports
generate_reports() {
    print_info "📊 Generating test reports..."
    
    # Generate Allure report
    if mvn allure:report; then
        print_success "✅ Allure report generated"
    else
        print_warning "⚠️ Failed to generate Allure report"
    fi
    
    # Create custom HTML summary
    create_summary_report
}

# Function to create summary report
create_summary_report() {
    print_info "📝 Creating test summary report..."
    
    local timestamp=$(date '+%Y-%m-%d %H:%M:%S')
    local test_status="UNKNOWN"
    local test_count=0
    local failed_count=0
    
    # Parse test results if available
    if [ -f "$TEST_RESULTS_DIR/TEST-com.bdd.framework.mongo.DockerMongoDBIntegrationTest.xml" ]; then
        test_count=$(grep -o 'tests="[0-9]*"' "$TEST_RESULTS_DIR/TEST-com.bdd.framework.mongo.DockerMongoDBIntegrationTest.xml" | cut -d'"' -f2)
        failed_count=$(grep -o 'failures="[0-9]*"' "$TEST_RESULTS_DIR/TEST-com.bdd.framework.mongo.DockerMongoDBIntegrationTest.xml" | cut -d'"' -f2)
        
        if [ "$failed_count" = "0" ]; then
            test_status="PASSED"
        else
            test_status="FAILED"
        fi
    fi
    
    # Create summary HTML
    cat > target/test-summary.html << EOF
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MongoDB Integration Test Summary</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background: #f5f5f5; }
        .container { max-width: 800px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        .header { text-align: center; margin-bottom: 30px; }
        .status-passed { color: #28a745; }
        .status-failed { color: #dc3545; }
        .status-unknown { color: #6c757d; }
        .metrics { display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 20px; margin: 20px 0; }
        .metric { background: #f8f9fa; padding: 15px; border-radius: 5px; text-align: center; }
        .metric-value { font-size: 2em; font-weight: bold; }
        .links { margin-top: 30px; }
        .btn { display: inline-block; padding: 10px 20px; margin: 5px; background: #007bff; color: white; text-decoration: none; border-radius: 5px; }
        .btn:hover { background: #0056b3; }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>🚀 MongoDB Integration Test Results</h1>
            <h2 class="status-$(echo $test_status | tr '[:upper:]' '[:lower:]')">Status: $test_status</h2>
            <p>Generated on: $timestamp</p>
        </div>
        
        <div class="metrics">
            <div class="metric">
                <div class="metric-value">$test_count</div>
                <div>Total Tests</div>
            </div>
            <div class="metric">
                <div class="metric-value status-passed">$((test_count - failed_count))</div>
                <div>Passed</div>
            </div>
            <div class="metric">
                <div class="metric-value status-failed">$failed_count</div>
                <div>Failed</div>
            </div>
            <div class="metric">
                <div class="metric-value">$([ "$failed_count" = "0" ] && echo "100%" || echo "$((100 * (test_count - failed_count) / test_count))%")</div>
                <div>Success Rate</div>
            </div>
        </div>
        
        <div class="links">
            <h3>📊 Detailed Reports</h3>
            <a href="/allure/" class="btn">🌐 Allure Report (Interactive)</a>
            <a href="/surefire/" class="btn">📋 Surefire Report (HTML)</a>
        </div>
        
        <div style="margin-top: 30px; padding: 15px; background: #e9ecef; border-radius: 5px;">
            <h4>🔧 Test Environment</h4>
            <ul>
                <li><strong>MongoDB Version:</strong> 7.0</li>
                <li><strong>Java Version:</strong> $(java -version 2>&1 | head -n 1 | cut -d'"' -f2)</li>
                <li><strong>Maven Version:</strong> $(mvn -v | head -n 1 | cut -d' ' -f3)</li>
                <li><strong>Docker Container:</strong> $MONGODB_CONTAINER_NAME</li>
            </ul>
        </div>
    </div>
</body>
</html>
EOF

    print_success "✅ Test summary report created: target/test-summary.html"
}

# Function to cleanup
cleanup() {
    print_info "🧹 Cleaning up test environment..."
    
    # Only stop the container if we started it and it's our test container
    if docker ps -q --filter "name=$MONGODB_CONTAINER_NAME" | grep -q .; then
        # Check if this is our test container or an existing one
        if docker port $MONGODB_CONTAINER_NAME 27017 2>/dev/null | grep -q "0.0.0.0:27017"; then
            print_info "🛑 Stopping CI test MongoDB container..."
            docker stop $MONGODB_CONTAINER_NAME 2>/dev/null || true
            docker rm $MONGODB_CONTAINER_NAME 2>/dev/null || true
        else
            print_info "ℹ️  Leaving existing MongoDB container running"
        fi
    fi
    
    print_success "✅ Cleanup completed"
}

# Function to display results
display_results() {
    echo
    print_header "📊 Test Execution Summary"
    echo "=================================================================="
    
    if [ -d "$TEST_RESULTS_DIR" ]; then
        print_info "📁 Test Reports Directory: $TEST_RESULTS_DIR"
        ls -la $TEST_RESULTS_DIR 2>/dev/null || true
    fi
    
    if [ -d "$ALLURE_REPORT_DIR" ]; then
        print_info "📁 Allure Report Directory: $ALLURE_REPORT_DIR"
    fi
    
    echo "=================================================================="
}

# Trap cleanup on exit
trap cleanup EXIT INT TERM

# Main execution
main() {
    print_header "🚀 CI/CD MongoDB Integration Test Pipeline"
    echo "=================================================================="
    echo
    
    # Step 1: Check prerequisites
    check_docker
    echo
    
    # Step 2: Start MongoDB
    start_mongodb
    echo
    
    # Step 3: Wait for MongoDB
    wait_for_mongodb
    echo
    
    # Step 4: Run tests
    if run_tests; then
        TEST_EXIT_CODE=0
    else
        TEST_EXIT_CODE=1
    fi
    echo
    
    # Step 5: Generate reports
    generate_reports
    echo
    
    # Step 6: Display results
    display_results
    
    # Return appropriate exit code
    exit $TEST_EXIT_CODE
}

# Execute main function
main "$@"
