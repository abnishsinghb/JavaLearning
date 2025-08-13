# BDD Playwright Automation Framework - Complete Implementation Guide

## 🎯 Overview

This document provides a comprehensive guide for the BDD Playwright Automation Framework implementation, specifically adapted for DemoQA website testing. The framework combines **Behavior-Driven Development (BDD)** with **Playwright** for robust web automation testing.

## 📋 What We've Implemented

### 1. Framework Architecture

```
BDD Playwright Framework for DemoQA
├── Core Framework Components
│   ├── BasePage.java                    # Base page object with common functionality
│   ├── DemoQALoginPage.java            # DemoQA-specific login page object
│   ├── DemoQAConfig.java               # Configuration settings
│   └── TestDataManager.java           # Test data management utility
├── Test Implementation
│   ├── demoqa-login.feature            # BDD scenarios for login testing
│   ├── DemoQALoginSteps.java           # Step definitions
│   └── DemoQATestRunner.java           # Test execution runner
├── Test Data & Configuration
│   ├── demoqa-testdata.properties      # Test data configuration
│   └── Test execution scripts          # Automated test execution
└── Documentation
    ├── DEMOQA_README.md                # DemoQA-specific documentation
    └── Implementation guides           # Complete setup instructions
```

### 2. Key Features Implemented

#### ✅ Page Object Model (POM)
- **BasePage.java**: Common functionality for all pages
- **DemoQALoginPage.java**: DemoQA login-specific implementation
- Modular, maintainable, and reusable page objects

#### ✅ BDD Test Scenarios
Comprehensive test coverage including:
- **Positive Tests**: Valid login scenarios
- **Negative Tests**: Invalid credentials, empty fields
- **Validation Tests**: Form validation, field requirements
- **UI Tests**: Element visibility, form functionality
- **Security Tests**: Password masking, field types

#### ✅ Smart Configuration Management
- **Environment-specific settings**
- **Test data externalization**
- **Browser configuration options**
- **Execution parameters**

#### ✅ Test Execution Framework
- **Multiple browser support** (Chromium, Firefox, WebKit)
- **Headless and headed modes**
- **Screenshot capture on failure**
- **Video recording capability**
- **Parallel execution support**

#### ✅ Reporting & Debugging
- **Allure integration** for detailed reports
- **Screenshot attachments**
- **Console logging**
- **Debug mode support**

## 🚀 Quick Start Guide

### Prerequisites
1. **Java 17+** installed
2. **Maven 3.6+** installed
3. **Valid DemoQA account**

### Setup Steps

1. **Navigate to project directory**:
   ```bash
   cd /Users/abnish/api-web-play/bdd-playwright-automation
   ```

2. **Install dependencies**:
   ```bash
   mvn clean install
   ```

3. **Install Playwright browsers**:
   ```bash
   mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
   ```

4. **Configure test credentials** (Update in `src/test/resources/testdata/demoqa-testdata.properties`):
   ```properties
   demoqa.valid.username=your_username
   demoqa.valid.password=your_password
   ```

### Running Tests

#### Option 1: Using Test Runner Class
```bash
mvn test -Dtest=DemoQATestRunner
```

#### Option 2: Using Execution Scripts

**Linux/macOS**:
```bash
./run-demoqa-tests.sh
./run-demoqa-tests.sh --visible --slowmo 1000  # Debug mode
./run-demoqa-tests.sh --smoke --browser firefox # Smoke tests in Firefox
```

**Windows**:
```cmd
run-demoqa-tests.bat
run-demoqa-tests.bat --visible --slowmo 1000
run-demoqa-tests.bat --smoke --browser firefox
```

#### Option 3: Maven with Parameters
```bash
# Run all DemoQA tests
mvn test -Dtest=DemoQATestRunner -Dcucumber.filter.tags="@demoqa"

# Run smoke tests only
mvn test -Dtest=DemoQATestRunner -Dcucumber.filter.tags="@demoqa and @smoke"

# Run in visible mode with slow motion
mvn test -Dtest=DemoQATestRunner -Dheadless=false -Dslowmo=1000

# Run with video recording
mvn test -Dtest=DemoQATestRunner -Drecord.video=true
```

## 📊 Test Scenarios Covered

### 1. Login Functionality (`@smoke`)
- ✅ Successful login with valid credentials
- ✅ Redirection to profile page after login
- ✅ Logout functionality

### 2. Negative Testing (`@negative`)
- ✅ Login with invalid username
- ✅ Login with invalid password
- ✅ Login with empty credentials
- ✅ Error message validation

### 3. Form Validation (`@validation`)
- ✅ Username field validation
- ✅ Password field validation
- ✅ Required field checking

### 4. UI Testing (`@ui`)
- ✅ Form element visibility
- ✅ Button state verification
- ✅ Field clearing functionality

### 5. Security Testing (`@security`)
- ✅ Password field masking
- ✅ Input type verification

## 🛠️ Framework Components Deep Dive

### 1. BasePage.java
**Purpose**: Provides common functionality for all page objects

**Key Features**:
- Navigation utilities
- Element interaction methods
- Wait strategies
- Screenshot capabilities
- Verification utilities

**Usage**:
```java
public class YourPage extends BasePage {
    public YourPage(Page page) {
        super(page);
    }
    
    public void performAction() {
        clickElement("#button");
        fillField("#input", "text");
        verifyElementVisible("#result");
    }
}
```

### 2. DemoQALoginPage.java
**Purpose**: Handles all DemoQA login page interactions

**Key Methods**:
- `navigateToLoginPage()`: Navigate to login page
- `enterUsername(String)`: Enter username
- `enterPassword(String)`: Enter password
- `clickLoginButton()`: Click login button
- `performLogin(String, String)`: Complete login flow
- `isLoginSuccessful()`: Verify login success
- `isOnLoginPage()`: Verify current page

### 3. DemoQALoginSteps.java
**Purpose**: Implements Cucumber step definitions

**Key Features**:
- Browser setup and teardown
- Step implementations for all scenarios
- Test data integration
- Assertion handling

### 4. TestDataManager.java
**Purpose**: Manages test data and configuration

**Key Features**:
- Properties file loading
- Environment-specific data
- Default value handling
- Test data generation utilities

## 🔧 Configuration Options

### Browser Configuration
```bash
-Dbrowser=chromium          # Chrome/Chromium (default)
-Dbrowser=firefox           # Firefox
-Dbrowser=webkit            # Safari/WebKit
```

### Execution Mode
```bash
-Dheadless=false           # Visible browser mode
-Dslowmo=1000              # Slow motion delay (ms)
-Dviewport.width=1920      # Browser width
-Dviewport.height=1080     # Browser height
```

### Test Data
```bash
-Ddemoqa.username=user     # Override username
-Ddemoqa.password=pass     # Override password
-Ddemoqa.base.url=url      # Override base URL
```

### Reporting
```bash
-Dscreenshots=true         # Enable screenshots
-Drecord.video=true        # Enable video recording
```

## 📈 Reporting & Analysis

### 1. Allure Reports
Generate comprehensive test reports:
```bash
# After test execution
allure serve allure-results
```

**Report Features**:
- Test execution timeline
- Step-by-step breakdown
- Screenshot attachments
- Error analysis
- Trend analysis

### 2. Console Output
Real-time test execution feedback with:
- Test progress indicators
- Configuration details
- Pass/fail status
- Error messages

### 3. Screenshots
Automatic screenshot capture:
- On test failures
- During debug mode
- Stored in `target/screenshots/`

## 🐛 Debugging Guide

### 1. Visual Debugging
```bash
# Run in visible mode with slow motion
./run-demoqa-tests.sh --visible --slowmo 2000
```

### 2. Video Recording
```bash
# Record test execution
./run-demoqa-tests.sh --video
```

### 3. Log Analysis
- Check console output for errors
- Review Allure reports for detailed steps
- Examine screenshots for UI issues

### 4. Common Issues & Solutions

**Issue**: Tests failing with element not found
**Solution**: 
- Check if DemoQA UI has changed
- Update locators in `DemoQALoginPage.java`
- Increase timeout values

**Issue**: Login credentials not working
**Solution**:
- Verify credentials are correct
- Check test data configuration
- Ensure DemoQA account is active

**Issue**: Browser launch failures
**Solution**:
- Install Playwright browsers: `mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"`
- Try different browser types
- Check system compatibility

## 🔄 Extending the Framework

### 1. Adding New Page Objects
```java
public class DemoQABooksPage extends BasePage {
    private static final String BOOKS_LIST = ".books-wrapper";
    
    public DemoQABooksPage(Page page) {
        super(page);
    }
    
    public void searchBook(String title) {
        fillField("#searchBox", title);
        clickElement("#basic-addon2");
    }
}
```

### 2. Adding New Test Scenarios
```gherkin
@web @demoqa @books
Scenario: Search for a book
  Given I am logged in to DemoQA
  When I navigate to books section
  And I search for "Git Pocket Guide"
  Then I should see the book in results
```

### 3. Adding New Step Definitions
```java
@When("I navigate to books section")
public void i_navigate_to_books_section() {
    page.navigate("https://demoqa.com/books");
}

@When("I search for {string}")
public void i_search_for_book(String bookTitle) {
    booksPage.searchBook(bookTitle);
}
```

## 📚 Project Files Summary

### Created/Modified Files:
1. **`src/main/java/com/framework/web/pages/BasePage.java`** - Base page object
2. **`src/main/java/com/framework/web/pages/DemoQALoginPage.java`** - DemoQA login page
3. **`src/main/java/com/framework/config/DemoQAConfig.java`** - Configuration class
4. **`src/main/java/com/framework/utils/TestDataManager.java`** - Test data utility
5. **`src/test/java/com/framework/web/stepdefs/DemoQALoginSteps.java`** - Step definitions
6. **`src/test/java/com/framework/runners/DemoQATestRunner.java`** - Test runner
7. **`src/test/resources/features/web/demoqa-login.feature`** - BDD scenarios
8. **`src/test/resources/testdata/demoqa-testdata.properties`** - Test data
9. **`run-demoqa-tests.sh`** - Linux/macOS execution script
10. **`run-demoqa-tests.bat`** - Windows execution script
11. **`DEMOQA_README.md`** - Detailed DemoQA documentation

## 🎯 Next Steps

1. **Set up valid DemoQA credentials** in test data properties
2. **Run initial test execution** to verify setup
3. **Generate Allure reports** to view results
4. **Extend framework** with additional DemoQA features
5. **Integrate with CI/CD** pipeline for automated execution

## 🔗 Additional Resources

- [DemoQA Website](https://demoqa.com)
- [Playwright Documentation](https://playwright.dev/java/)
- [Cucumber Documentation](https://cucumber.io/docs/cucumber/)
- [Allure Reports](https://docs.qameta.io/allure/)

---

**This implementation provides a complete, production-ready test automation framework for DemoQA website testing using BDD principles and Playwright automation.**
