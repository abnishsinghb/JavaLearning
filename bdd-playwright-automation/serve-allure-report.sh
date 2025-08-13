#!/bin/bash

# Allure Report Server Script
# Starts a local HTTP server and opens the Allure report in browser

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
    echo -e "${PURPLE}[ALLURE]${NC} $1"
}

# Configuration
ALLURE_REPORT_DIR="target/site/allure-maven-plugin"
PORT=8080
URL="http://localhost:$PORT"

# Function to check if port is available
check_port() {
    if lsof -Pi :$PORT -sTCP:LISTEN -t >/dev/null 2>&1; then
        print_warning "Port $PORT is already in use. Trying to stop existing server..."
        pkill -f "python.*http.server.*$PORT" 2>/dev/null || true
        sleep 2
    fi
}

# Function to start HTTP server
start_server() {
    print_info "🚀 Starting HTTP server for Allure report..."
    
    # Store current directory
    ORIGINAL_DIR=$(pwd)
    
    # Check if report directory exists
    if [ ! -d "$ALLURE_REPORT_DIR" ]; then
        print_error "❌ Allure report directory not found: $ALLURE_REPORT_DIR"
        print_info "Please run 'mvn allure:report' first to generate the report"
        exit 1
    fi
    
    # Check if index.html exists
    if [ ! -f "$ALLURE_REPORT_DIR/index.html" ]; then
        print_error "❌ Allure report index.html not found"
        print_info "Please run 'mvn allure:report' first to generate the report"
        exit 1
    fi
    
    # Check port availability
    check_port
    
    # Start server in background
    cd "$ALLURE_REPORT_DIR"
    python3 -m http.server $PORT >/dev/null 2>&1 &
    SERVER_PID=$!
    
    # Return to original directory
    cd "$ORIGINAL_DIR"
    
    # Wait a moment for server to start
    sleep 2
    
    # Check if server started successfully
    if kill -0 $SERVER_PID 2>/dev/null; then
        print_success "✅ HTTP server started successfully (PID: $SERVER_PID)"
        print_info "📍 Server running at: $URL"
    else
        print_error "❌ Failed to start HTTP server"
        exit 1
    fi
}

# Function to open browser
open_browser() {
    print_info "🌐 Opening Allure report in browser..."
    
    # Wait for server to be ready
    for i in {1..10}; do
        if curl -s $URL >/dev/null 2>&1; then
            break
        fi
        sleep 1
    done
    
    # Open in default browser
    if command -v open >/dev/null 2>&1; then
        open "$URL"
        print_success "🎉 Opened in default browser"
    fi
    
    # Also open in VS Code Simple Browser if available
    if command -v code >/dev/null 2>&1; then
        code --open-url "$URL"
        print_success "📱 Opened in VS Code Simple Browser"
    fi
}

# Function to show report info
show_report_info() {
    echo
    print_header "📊 Allure Report Information"
    echo "=================================================================="
    print_info "🔗 Report URL: $URL"
    print_info "📁 Report Directory: $ORIGINAL_DIR/$ALLURE_REPORT_DIR"
    print_info "🔧 Server PID: $SERVER_PID"
    echo
    print_info "🎯 To stop the server later, run:"
    echo "   kill $SERVER_PID"
    echo "   or"
    echo "   pkill -f 'python.*http.server.*$PORT'"
    echo
    print_success "✨ Your MongoDB integration test results are now viewable!"
    echo "=================================================================="
}

# Function to cleanup on exit
cleanup() {
    if [ ! -z "$SERVER_PID" ] && kill -0 $SERVER_PID 2>/dev/null; then
        print_info "🛑 Stopping HTTP server (PID: $SERVER_PID)..."
        kill $SERVER_PID 2>/dev/null || true
    fi
}

# Trap cleanup on exit
trap cleanup EXIT INT TERM

# Main execution
main() {
    print_header "🚀 Allure Report Server"
    echo "=================================================================="
    echo
    
    start_server
    echo
    
    open_browser
    echo
    
    show_report_info
    
    # Keep server running
    print_info "⏳ Keeping server running... Press Ctrl+C to stop"
    wait $SERVER_PID
}

# Handle command line arguments
case "${1:-}" in
    "stop")
        print_info "🛑 Stopping any running Allure report servers..."
        pkill -f "python.*http.server.*$PORT" 2>/dev/null || true
        print_success "✅ Server stopped"
        exit 0
        ;;
    "status")
        if lsof -Pi :$PORT -sTCP:LISTEN -t >/dev/null 2>&1; then
            print_success "✅ Server is running on port $PORT"
            print_info "🔗 URL: $URL"
        else
            print_warning "⚠️ No server running on port $PORT"
        fi
        exit 0
        ;;
    "help"|"-h"|"--help")
        echo "Usage: $0 [stop|status|help]"
        echo ""
        echo "Commands:"
        echo "  (no args)  Start server and open report"
        echo "  stop       Stop any running servers"
        echo "  status     Check if server is running"
        echo "  help       Show this help message"
        exit 0
        ;;
esac

# Execute main function
main "$@"
