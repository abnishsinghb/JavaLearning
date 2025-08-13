#!/bin/bash

# Docker Report Server Script
# Serves test reports through Docker container with Nginx

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
    echo -e "${PURPLE}[DOCKER-REPORTS]${NC} $1"
}

# Configuration
REPORT_CONTAINER_NAME="test-reports-server"
REPORT_PORT="8080"
NGINX_IMAGE="nginx:alpine"

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

# Function to prepare reports
prepare_reports() {
    print_info "📋 Preparing reports for Docker server..." >&2
    
    # Create reports directory structure
    local reports_dir="docker-reports"
    rm -rf $reports_dir 2>/dev/null || true
    mkdir -p $reports_dir/{allure,surefire}
    
    # Copy Allure reports if they exist
    if [ -d "target/site/allure-maven-plugin" ]; then
        print_info "📊 Copying Allure reports..." >&2
        cp -r target/site/allure-maven-plugin/* $reports_dir/allure/ 2>/dev/null || true
        print_success "✅ Allure reports copied" >&2
    else
        print_warning "⚠️ No Allure reports found" >&2
        echo "<h1>No Allure Reports Available</h1><p>Run tests first to generate reports.</p>" > $reports_dir/allure/index.html
    fi
    
    # Copy Surefire reports if they exist
    if [ -d "target/surefire-reports" ]; then
        print_info "📋 Copying Surefire reports..." >&2
        cp -r target/surefire-reports/* $reports_dir/surefire/ 2>/dev/null || true
        # Create index for surefire reports
        create_surefire_index $reports_dir/surefire
        print_success "✅ Surefire reports copied" >&2
    else
        print_warning "⚠️ No Surefire reports found" >&2
        echo "<h1>No Surefire Reports Available</h1><p>Run tests first to generate reports.</p>" > $reports_dir/surefire/index.html
    fi
    
    # Copy test summary if it exists
    if [ -f "target/test-summary.html" ]; then
        print_info "📝 Copying test summary..." >&2
        cp target/test-summary.html $reports_dir/
        print_success "✅ Test summary copied" >&2
    else
        print_warning "⚠️ No test summary found" >&2
        create_default_summary $reports_dir
    fi
    
    # Create main index page
    create_main_index $reports_dir
    
    print_success "✅ Reports prepared in $reports_dir/" >&2
    echo "$reports_dir"
}

# Function to create Surefire index
create_surefire_index() {
    local surefire_dir="$1"
    
    cat > "$surefire_dir/index.html" << 'EOF'
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Surefire Test Reports</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background: #f5f5f5; }
        .container { max-width: 1000px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        .header { text-align: center; margin-bottom: 30px; }
        .file-list { list-style: none; padding: 0; }
        .file-list li { margin: 10px 0; padding: 15px; background: #f8f9fa; border-radius: 5px; }
        .file-list a { text-decoration: none; color: #007bff; font-weight: bold; }
        .file-list a:hover { color: #0056b3; }
        .file-size { color: #6c757d; font-size: 0.9em; }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>📋 Surefire Test Reports</h1>
            <p>Raw test execution reports and XML files</p>
        </div>
        <ul class="file-list">
EOF

    # List all files in the surefire directory
    if [ -d "$surefire_dir" ]; then
        for file in "$surefire_dir"/*; do
            if [ -f "$file" ] && [ "$(basename "$file")" != "index.html" ]; then
                local filename=$(basename "$file")
                local filesize=$(ls -lh "$file" | awk '{print $5}')
                echo "            <li><a href=\"$filename\">$filename</a> <span class=\"file-size\">($filesize)</span></li>" >> "$surefire_dir/index.html"
            fi
        done
    fi
    
    cat >> "$surefire_dir/index.html" << 'EOF'
        </ul>
        <div style="margin-top: 30px; text-align: center;">
            <a href="../" style="color: #007bff; text-decoration: none;">← Back to Main Reports</a>
        </div>
    </div>
</body>
</html>
EOF
}

# Function to create default summary
create_default_summary() {
    local reports_dir="$1"
    
    cat > "$reports_dir/test-summary.html" << 'EOF'
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Test Summary</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background: #f5f5f5; }
        .container { max-width: 800px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        .header { text-align: center; margin-bottom: 30px; }
        .warning { background: #fff3cd; border: 1px solid #ffeaa7; padding: 15px; border-radius: 5px; }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>📊 Test Summary</h1>
        </div>
        <div class="warning">
            <h3>⚠️ No Test Results Available</h3>
            <p>No test summary was found. Please run the tests first using:</p>
            <code>./run-tests.sh</code>
        </div>
    </div>
</body>
</html>
EOF
}

# Function to create main index page
create_main_index() {
    local reports_dir="$1"
    
    cat > "$reports_dir/index.html" << 'EOF'
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MongoDB Integration Test Reports</title>
    <style>
        body { 
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; 
            margin: 0; 
            padding: 20px; 
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
        }
        .container { 
            max-width: 1000px; 
            margin: 0 auto; 
            background: white; 
            border-radius: 15px; 
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
            overflow: hidden;
        }
        .header { 
            background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);
            color: white; 
            padding: 40px; 
            text-align: center; 
        }
        .header h1 { margin: 0 0 10px 0; font-size: 2.5em; }
        .header p { margin: 0; opacity: 0.9; font-size: 1.1em; }
        .content { padding: 40px; }
        .reports-grid { 
            display: grid; 
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); 
            gap: 30px; 
            margin: 30px 0; 
        }
        .report-card { 
            background: #f8f9fa; 
            border-radius: 10px; 
            padding: 30px; 
            text-align: center; 
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            border: 2px solid transparent;
        }
        .report-card:hover { 
            transform: translateY(-5px); 
            box-shadow: 0 15px 35px rgba(0,0,0,0.1);
            border-color: #4facfe;
        }
        .report-card h3 { 
            color: #333; 
            margin: 0 0 15px 0; 
            font-size: 1.4em;
        }
        .report-card p { 
            color: #666; 
            margin: 0 0 20px 0; 
            line-height: 1.6;
        }
        .btn { 
            display: inline-block; 
            padding: 12px 30px; 
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white; 
            text-decoration: none; 
            border-radius: 25px; 
            font-weight: bold;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        .btn:hover { 
            transform: translateY(-2px);
            box-shadow: 0 8px 20px rgba(0,0,0,0.2);
            color: white;
            text-decoration: none;
        }
        .btn.primary { background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%); }
        .btn.secondary { background: linear-gradient(135deg, #43e97b 0%, #38f9d7 100%); }
        .btn.tertiary { background: linear-gradient(135deg, #fa709a 0%, #fee140 100%); }
        .icon { font-size: 3em; margin-bottom: 15px; }
        .footer { 
            background: #f8f9fa; 
            padding: 20px; 
            text-align: center; 
            color: #666;
            border-top: 1px solid #e9ecef;
        }
        .timestamp { 
            background: #e9ecef; 
            padding: 15px; 
            border-radius: 8px; 
            margin: 20px 0;
            text-align: center;
            color: #495057;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>🚀 MongoDB Integration Test Reports</h1>
            <p>Comprehensive test results and analysis dashboard</p>
        </div>
        
        <div class="content">
            <div class="timestamp">
                <strong>📅 Generated:</strong> <span id="timestamp"></span>
            </div>
            
            <div class="reports-grid">
                <div class="report-card">
                    <div class="icon">🌐</div>
                    <h3>Allure Report</h3>
                    <p>Interactive test results with detailed execution timeline, screenshots, and comprehensive analytics.</p>
                    <a href="/allure/" class="btn primary">View Allure Report</a>
                </div>
                
                <div class="report-card">
                    <div class="icon">📋</div>
                    <h3>Surefire Reports</h3>
                    <p>Raw Maven Surefire test execution reports with XML data and detailed logs.</p>
                    <a href="/surefire/" class="btn secondary">View Surefire Reports</a>
                </div>
                
                <div class="report-card">
                    <div class="icon">📊</div>
                    <h3>Test Summary</h3>
                    <p>Quick overview of test execution results with key metrics and success rates.</p>
                    <a href="/test-summary.html" class="btn tertiary">View Summary</a>
                </div>
            </div>
            
            <div style="margin-top: 40px; padding: 20px; background: #e3f2fd; border-radius: 8px;">
                <h3 style="color: #1976d2; margin-top: 0;">🔧 Test Environment Information</h3>
                <ul style="color: #424242; line-height: 1.8;">
                    <li><strong>MongoDB Version:</strong> 7.0 (Docker)</li>
                    <li><strong>Test Framework:</strong> JUnit 5 + Maven Surefire</li>
                    <li><strong>Reporting:</strong> Allure Framework</li>
                    <li><strong>Server:</strong> Docker + Nginx</li>
                </ul>
            </div>
        </div>
        
        <div class="footer">
            <p>🐳 Reports served via Docker container | ⚡ Auto-refresh available</p>
        </div>
    </div>
    
    <script>
        // Set current timestamp
        document.getElementById('timestamp').textContent = new Date().toLocaleString();
        
        // Auto-refresh every 30 seconds if reports are being generated
        if (window.location.search.includes('auto-refresh')) {
            setTimeout(() => {
                window.location.reload();
            }, 30000);
        }
    </script>
</body>
</html>
EOF
}

# Function to start report server
start_report_server() {
    local reports_dir="$1"
    
    print_info "🌐 Starting Docker report server..."
    
    # Stop any existing container
    docker stop $REPORT_CONTAINER_NAME 2>/dev/null || true
    docker rm $REPORT_CONTAINER_NAME 2>/dev/null || true
    
    # Start new container
    local container_id=$(docker run -d \
        --name $REPORT_CONTAINER_NAME \
        -p $REPORT_PORT:80 \
        -v "$(pwd)/$reports_dir:/usr/share/nginx/html:ro" \
        -v "$(pwd)/docker/nginx.conf:/etc/nginx/conf.d/default.conf:ro" \
        $NGINX_IMAGE)
    
    if [ $? -eq 0 ]; then
        print_success "✅ Report server started: $container_id"
        print_info "🌐 Server URL: http://localhost:$REPORT_PORT"
        return 0
    else
        print_error "❌ Failed to start report server"
        return 1
    fi
}

# Function to check server status
check_server_status() {
    print_info "🔍 Checking server status..."
    
    if docker ps -q --filter "name=$REPORT_CONTAINER_NAME" | grep -q .; then
        print_success "✅ Report server is running"
        print_info "📊 Available reports:"
        print_info "   • Main Dashboard: http://localhost:$REPORT_PORT"
        print_info "   • Allure Reports: http://localhost:$REPORT_PORT/allure/"
        print_info "   • Surefire Reports: http://localhost:$REPORT_PORT/surefire/"
        print_info "   • Test Summary: http://localhost:$REPORT_PORT/test-summary.html"
        return 0
    else
        print_error "❌ Report server is not running"
        return 1
    fi
}

# Function to stop server
stop_server() {
    print_info "🛑 Stopping report server..."
    
    docker stop $REPORT_CONTAINER_NAME 2>/dev/null || true
    docker rm $REPORT_CONTAINER_NAME 2>/dev/null || true
    
    print_success "✅ Report server stopped"
}

# Function to show logs
show_logs() {
    print_info "📋 Report server logs:"
    docker logs $REPORT_CONTAINER_NAME 2>/dev/null || print_warning "⚠️ No logs available"
}

# Function to open browser
open_browser() {
    local url="http://localhost:$REPORT_PORT"
    
    print_info "🌐 Opening browser..."
    
    # Try to open browser based on OS
    if command -v open >/dev/null 2>&1; then
        # macOS
        open "$url"
    elif command -v xdg-open >/dev/null 2>&1; then
        # Linux
        xdg-open "$url"
    elif command -v start >/dev/null 2>&1; then
        # Windows
        start "$url"
    else
        print_info "📱 Please open your browser and visit: $url"
    fi
}

# Function to display help
show_help() {
    echo "Docker Report Server - Test Reports Viewer"
    echo ""
    echo "Usage: $0 [command]"
    echo ""
    echo "Commands:"
    echo "  start     - Start the report server (default)"
    echo "  stop      - Stop the report server"
    echo "  restart   - Restart the report server"
    echo "  status    - Check server status"
    echo "  logs      - Show server logs"
    echo "  open      - Open browser to reports"
    echo "  help      - Show this help message"
    echo ""
    echo "Server runs on: http://localhost:$REPORT_PORT"
}

# Main function
main() {
    local command="${1:-start}"
    
    print_header "🐳 Docker Test Reports Server"
    echo "=================================================================="
    echo
    
    case $command in
        start)
            check_docker
            reports_dir=$(prepare_reports)
            if start_report_server "$reports_dir"; then
                echo
                check_server_status
                echo
                print_info "🚀 To view reports, run: $0 open"
                print_info "🛑 To stop server, run: $0 stop"
            fi
            ;;
        stop)
            stop_server
            ;;
        restart)
            stop_server
            echo
            main start
            ;;
        status)
            check_server_status
            ;;
        logs)
            show_logs
            ;;
        open)
            if check_server_status >/dev/null 2>&1; then
                open_browser
            else
                print_error "❌ Server is not running. Start it first with: $0 start"
                exit 1
            fi
            ;;
        help|--help|-h)
            show_help
            ;;
        *)
            print_error "❌ Unknown command: $command"
            echo
            show_help
            exit 1
            ;;
    esac
}

# Execute main function
main "$@"
