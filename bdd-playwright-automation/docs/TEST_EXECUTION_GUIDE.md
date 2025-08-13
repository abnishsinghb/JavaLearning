# Test Execution Guide

This document provides detailed instructions for running tests in different scenarios and configurations.

## 🚀 Quick Start

### Run All Tests
```bash
mvn test
```

### Run Tests with Allure Reporting
```bash
mvn clean test
allure serve allure-results
```

## 🎯 Test Execution Strategies

### 1. By Test Type

#### API Tests Only
```bash
mvn test -Dcucumber.filter.tags="@api"
```

#### Web Tests Only
```bash
mvn test -Dcucumber.filter.tags="@web"
```

#### Smoke Tests
```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

#### Regression Tests
```bash
mvn test -Dcucumber.filter.tags="@regression"
```

### 2. By Feature Files

#### Specific Feature
```bash
mvn test -Dcucumber.features="src/test/resources/features/api/user.feature"
```

#### Multiple Features
```bash
mvn test -Dcucumber.features="src/test/resources/features/api,src/test/resources/features/web"
```

### 3. By Scenarios

#### Specific Scenario by Name
```bash
mvn test -Dcucumber.filter.name="Get user details"
```

#### Multiple Scenarios
```bash
mvn test -Dcucumber.filter.name="Get user details|Create a new user"
```

## 🔧 Configuration Options

### Browser Configuration (Web Tests)

#### Different Browsers
```bash
# Chrome (default)
mvn test -Dbrowser=chromium

# Firefox
mvn test -Dbrowser=firefox

# Safari (macOS only)
mvn test -Dbrowser=webkit
```

#### Browser Mode
```bash
# Headless mode (default)
mvn test -Dheadless=true

# Headed mode (visible browser)
mvn test -Dheadless=false
```

#### Browser Size
```bash
mvn test -Dviewport.width=1920 -Dviewport.height=1080
```

### Environment Configuration

#### Different Environments
```bash
# Development
mvn test -Denv=dev

# Staging
mvn test -Denv=staging

# Production
mvn test -Denv=prod
```

#### Custom URLs
```bash
# Web Application URL
mvn test -Dbase.url=https://your-app.com

# API Base URL
mvn test -Dapi.url=https://api.your-app.com
```

### Parallel Execution

#### Enable Parallel Execution
```bash
mvn test -Dparallel=true -Dthread.count=4
```

#### Configure in pom.xml
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <configuration>
        <parallel>methods</parallel>
        <threadCount>4</threadCount>
    </configuration>
</plugin>
```

## 📊 Reporting Options

### Allure Reports

#### Generate and Serve (Recommended)
```bash
# After running tests
allure serve allure-results
```

#### Generate Static Report
```bash
allure generate allure-results --clean -o allure-report
allure open allure-report
```

#### Custom Report Directory
```bash
allure generate allure-results -o custom-report-dir
```

### Console Output

#### Verbose Output
```bash
mvn test -X
```

#### Specific Log Levels
```bash
mvn test -Dlogging.level.com.framework=DEBUG
```

## 🔍 Debugging Tests

### Debug Mode
```bash
# Enable debug logging
mvn test -Ddebug=true

# Enable Maven debug
mvn -X test
```

### Screenshots on Failure
Web tests automatically capture screenshots on failure. Configure in step definitions:
```java
@After
public void tearDown(Scenario scenario) {
    if (scenario.isFailed() && page != null) {
        byte[] screenshot = page.screenshot();
        scenario.attach(screenshot, "image/png", "Screenshot");
    }
}
```

### Video Recording
Enable video recording for web tests:
```java
BrowserContext context = browser.newContext(new Browser.NewContextOptions()
    .setRecordVideoDir(Paths.get("videos/")));
```

## 📋 Test Data Management

### External Test Data
```bash
# Using external properties file
mvn test -Dtest.data.file=src/test/resources/testdata/users.properties
```

### Database Configuration
```bash
# Database connection for test data
mvn test -Ddb.url=jdbc:mysql://localhost:3306/testdb -Ddb.user=testuser -Ddb.password=testpass
```

## 🚀 CI/CD Integration

### Jenkins Pipeline
```groovy
pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
            post {
                always {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'allure-results']]
                    ])
                }
            }
        }
    }
}
```

### GitHub Actions
```yaml
name: Test Execution
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v2
    - name: Set up JDK 17
      uses: actions/setup-java@v2
      with:
        java-version: '17'
        distribution: 'adopt'
    - name: Run tests
      run: mvn clean test
    - name: Generate Allure Report
      uses: simple-elf/allure-report-action@master
      if: always()
      with:
        allure_results: allure-results
```

### Docker Execution
```dockerfile
FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y maven
COPY . /app
WORKDIR /app
RUN mvn clean compile
CMD ["mvn", "test"]
```

```bash
# Build and run tests in Docker
docker build -t bdd-tests .
docker run --rm -v $(pwd)/allure-results:/app/allure-results bdd-tests
```

## 📈 Performance Monitoring

### Execution Time Tracking
```bash
# Enable timing information
mvn test -Dcucumber.publish.quiet=false
```

### Resource Monitoring
```bash
# Monitor JVM memory usage
mvn test -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
```

## 🔄 Test Retry Mechanism

### Configure Retries in Runner
```java
@ConfigurationParameter(key = "cucumber.execution.retry", value = "2")
public class RunAllTests {}
```

### Conditional Retries
```java
@Test
@RepeatedTest(3)
public void retryFailedTest() {
    // Test implementation
}
```

## 📝 Test Execution Checklist

Before running tests:
- ✅ Verify Java and Maven versions
- ✅ Check network connectivity for API tests
- ✅ Ensure test environment is accessible
- ✅ Confirm browser drivers are installed (for web tests)
- ✅ Validate test data is available
- ✅ Check for any environment-specific configurations

After running tests:
- ✅ Review test results in console output
- ✅ Generate and review Allure reports
- ✅ Check for any failed tests and investigate
- ✅ Archive test reports for future reference
- ✅ Update test execution documentation if needed

## 🚨 Troubleshooting Common Issues

### Test Discovery Problems
```bash
# Verify test classes are compiled
mvn compile test-compile

# Check test runner configuration
mvn test -Dtest=RunAllTests
```

### Resource Cleanup
```bash
# Clean Maven cache
mvn dependency:purge-local-repository

# Reset workspace
mvn clean
```

### Browser Issues
```bash
# Reinstall browsers
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install --force"
```

---
*For additional help, refer to the main README.md or contact the framework maintainers.*
