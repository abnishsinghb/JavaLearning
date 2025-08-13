#!/bin/bash

# MongoDB Integration Tests - Report Viewer
# Opens both Surefire and Allure reports in browser with HTTP server for proper loading

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
    echo -e "${PURPLE}[REPORTS]${NC} $1"
}

# Function to open Surefire reports
open_surefire_reports() {
    print_info "📊 Opening Surefire reports..."
    
    local surefire_report="target/surefire-reports/index.html"
    local custom_report="mongodb-test-results-aligned.html"
    
    # Check for custom aligned report first
    if [ -f "$custom_report" ]; then
        local full_path="file://$(pwd)/$custom_report"
        
        # Open in default browser
        if command -v open >/dev/null 2>&1; then
            open "$full_path"
            print_success "Opened custom aligned report in default browser"
        fi
        
        print_success "🎉 Custom aligned Surefire report opened!"
        print_info "📍 Report location: $custom_report"
        
    elif [ -f "$surefire_report" ]; then
        local full_path="file://$(pwd)/$surefire_report"
        
        # Open in default browser
        if command -v open >/dev/null 2>&1; then
            open "$full_path"
            print_success "Opened standard Surefire report"
        fi
        
        print_success "🎉 Standard Surefire report opened!"
        print_info "📍 Report location: $surefire_report"
        
    else
        print_warning "No Surefire reports found"
        return 1
    fi
}

# Function to open Allure reports with HTTP server
open_allure_reports() {
    print_info "🌐 Opening Allure reports..."
    
    local allure_report="target/site/allure-maven-plugin/index.html"
    
    if [ -f "$allure_report" ]; then
        # Start HTTP server for Allure report to avoid CORS issues
        print_info "🚀 Starting HTTP server for Allure report..."
        
        # Kill any existing server on port 8080
        pkill -f "python.*http.server.*8080" 2>/dev/null || true
        sleep 1
        
        # Start server in background
        cd target/site/allure-maven-plugin
        python3 -m http.server 8080 >/dev/null 2>&1 &
        local server_pid=$!
        cd - >/dev/null
        
        # Wait for server to start
        sleep 2
        
        local allure_url="http://localhost:8080"
        
        # Open in default browser
        if command -v open >/dev/null 2>&1; then
            open "$allure_url"
            print_success "Opened Allure report in default browser"
        fi
        
        # Also open in VS Code Simple Browser
        if command -v code >/dev/null 2>&1; then
            code --open-url "$allure_url"
            print_success "Opened Allure report in VS Code Simple Browser"
        fi
        
        echo
        print_success "🎉 Allure report successfully opened!"
        print_info "📍 Allure report URL: $allure_url"
        print_info "🔧 Server PID: $server_pid (kill $server_pid to stop)"
        
    else
        print_warning "Allure report not found. Generating it now..."
        if mvn allure:report; then
            print_success "Allure report generated. Opening now..."
            open_allure_reports  # Recursive call after generation
        else
            print_error "Failed to generate Allure report"
        fi
    fi
}

# Function to display menu
show_menu() {
    echo
    print_header "🚀 MongoDB Integration Test Reports"
    echo "=================================================================="
    echo "1) 📊 Open Surefire Report (Static HTML)"
    echo "2) 🌐 Open Allure Report (Interactive - HTTP Server)"
    echo "3) 🎯 Open Both Reports"
    echo "4) 🔄 Regenerate and Open Allure Report"
    echo "5) 🛑 Stop Allure HTTP Server"
    echo "6) ❌ Exit"
    echo "=================================================================="
    echo
}

# Function to stop HTTP server
stop_server() {
    print_info "🛑 Stopping Allure HTTP server..."
    pkill -f "python.*http.server.*8080" 2>/dev/null || true
    print_success "✅ Server stopped"
}

# Function to regenerate and open Allure report
regenerate_allure() {
    print_info "🔄 Regenerating Allure report..."
    
    # Clean old results
    rm -rf allure-results/* target/site/allure-maven-plugin/* 2>/dev/null || true
    
    # Run tests and generate report
    if mvn clean test -Dtest=DockerMongoDBIntegrationTest; then
        print_success "Tests completed successfully"
        
        if mvn allure:report; then
            print_success "Allure report regenerated"
            open_allure_reports
        else
            print_error "Failed to generate Allure report"
        fi
    else
        print_error "Tests failed"
    fi
}

# Main execution
main() {
    # Handle command line arguments
    case "${1:-}" in
        "surefire")
            open_surefire_reports
            exit 0
            ;;
        "allure")
            open_allure_reports
            exit 0
            ;;
        "both")
            open_surefire_reports
            sleep 2
            open_allure_reports
            exit 0
            ;;
        "stop")
            stop_server
            exit 0
            ;;
        "regenerate")
            regenerate_allure
            exit 0
            ;;
        "help"|"-h"|"--help")
            echo "Usage: $0 [surefire|allure|both|stop|regenerate|help]"
            echo ""
            echo "Commands:"
            echo "  surefire     Open Surefire report only"
            echo "  allure       Open Allure report only (with HTTP server)"
            echo "  both         Open both reports"
            echo "  stop         Stop Allure HTTP server"
            echo "  regenerate   Regenerate and open Allure report"
            echo "  help         Show this help message"
            exit 0
            ;;
    esac
    
    # Interactive menu if no arguments
    while true; do
        show_menu
        read -p "Please select an option (1-6): " choice
        
        case $choice in
            1)
                open_surefire_reports
                ;;
            2)
                open_allure_reports
                ;;
            3)
                open_surefire_reports
                sleep 2
                open_allure_reports
                ;;
            4)
                regenerate_allure
                ;;
            5)
                stop_server
                ;;
            6)
                print_info "👋 Goodbye!"
                exit 0
                ;;
            *)
                print_error "Invalid option. Please select 1-6."
                ;;
        esac
        
        echo
        read -p "Press Enter to continue..."
    done
}

# Execute main function
main "$@"
