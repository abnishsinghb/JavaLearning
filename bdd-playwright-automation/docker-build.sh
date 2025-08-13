#!/bin/bash

# Docker Build and Test Script for CI/CD Pipeline
# Builds Docker images and runs tests in containerized environment

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
    echo -e "${PURPLE}[DOCKER]${NC} $1"
}

# Configuration
IMAGE_NAME="mongodb-integration-tests"
TAG=${1:-latest}
FULL_IMAGE_NAME="$IMAGE_NAME:$TAG"

# Function to build Docker image
build_image() {
    print_info "🔨 Building Docker image: $FULL_IMAGE_NAME"
    
    docker build \
        --target test-runner \
        --tag $FULL_IMAGE_NAME \
        --build-arg BUILD_DATE=$(date -u +'%Y-%m-%dT%H:%M:%SZ') \
        --build-arg VCS_REF=$(git rev-parse --short HEAD 2>/dev/null || echo "unknown") \
        .
    
    print_success "✅ Docker image built successfully: $FULL_IMAGE_NAME"
}

# Function to test the image
test_image() {
    print_info "🧪 Testing Docker image..."
    
    # Run basic validation
    docker run --rm $FULL_IMAGE_NAME java -version
    docker run --rm $FULL_IMAGE_NAME mvn -version
    
    print_success "✅ Docker image validation passed"
}

# Function to show image info
show_image_info() {
    print_info "📋 Docker image information:"
    echo
    docker images $FULL_IMAGE_NAME
    echo
    print_info "🏷️ Image labels:"
    docker inspect $FULL_IMAGE_NAME --format='{{json .Config.Labels}}' | python3 -m json.tool
}

# Main execution
main() {
    print_header "🐳 Docker Build Pipeline - Step 1"
    echo "=================================================================="
    echo
    
    build_image
    echo
    
    test_image
    echo
    
    show_image_info
    
    echo
    print_success "🎉 Step 1 completed successfully!"
    print_info "Next: Run './run-tests.sh' to test the pipeline"
    echo "=================================================================="
}

# Execute main function
main "$@"
