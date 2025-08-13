# DemoQA Testing with BDD Playwright Framework

This document explains how to use the BDD Playwright Automation Framework to test the DemoQA website (https://demoqa.com).

## 🎯 Overview

The DemoQA implementation includes:
- **Login Page Object**: `DemoQALoginPage.java` - Handles all login functionality
- **Step Definitions**: `DemoQALoginSteps.java` - Cucumber step implementations
- **Feature File**: `demoqa-login.feature` - BDD scenarios for login testing
- **Test Runner**: `DemoQATestRunner.java` - Executes DemoQA-specific tests
- **Configuration**: `DemoQAConfig.java` - Test configuration and settings

## 🏗️ Project Structure

```
src/
├── main/java/com/framework/
│   ├── config/
│   │   └── DemoQAConfig.java           # DemoQA test configuration
│   └── web/pages/
│       ├── BasePage.java               # Base page object with common functionality
│       └── DemoQALoginPage.java        # DemoQA login page object
└── test/
    ├── java/com/framework/
    │   ├── runners/
    │   │   └── DemoQATestRunner.java   # Test runner for DemoQA tests
    │   └── web/stepdefs/
    │       └── DemoQALoginSteps.java   # Step definitions for DemoQA login
    └── resources/features/web/
        └── demoqa-login.feature        # BDD scenarios for DemoQA login
```

## 🚀 Running DemoQA Tests

### Prerequisites

1. **Valid DemoQA Account**: You need a valid account on https://demoqa.com
2. **Framework Setup**: Complete the main framework setup first

### Test Execution Commands

#### Run All DemoQA Tests
```bash
mvn test -Dtest=DemoQATestRunner
```

#### Run with Different Browsers
```bash
# Chrome (default)
mvn test -Dtest=DemoQATestRunner -Dbrowser=chromium

# Firefox
mvn test -Dtest=DemoQATestRunner -Dbrowser=firefox

# Safari (macOS only)
mvn test -Dtest=DemoQATestRunner -Dbrowser=webkit
```

#### Run in Headed Mode (Visible Browser)
```bash
mvn test -Dtest=DemoQATestRunner -Dheadless=false
```

#### Run with Custom Credentials
```bash
mvn test -Dtest=DemoQATestRunner -Ddemoqa.username=your_username -Ddemoqa.password=your_password
```

#### Run Specific Test Scenarios
```bash
# Smoke tests only
mvn test -Dtest=DemoQATestRunner -Dcucumber.filter.tags="@smoke"

# Negative tests only
mvn test -Dtest=DemoQATestRunner -Dcucumber.filter.tags="@negative"

# Validation tests only
mvn test -Dtest=DemoQATestRunner -Dcucumber.filter.tags="@validation"
```

#### Run with Screenshots and Video Recording
```bash
mvn test -Dtest=DemoQATestRunner -Dscreenshots=true -Drecord.video=true
```

### Configuration Options

You can customize the test execution using system properties:

| Property | Default | Description |
|----------|---------|-------------|
| `demoqa.base.url` | `https://demoqa.com` | DemoQA base URL |
| `demoqa.username` | `testuser` | Valid username for login |
| `demoqa.password` | `TestPassword123!` | Valid password for login |
| `browser` | `chromium` | Browser type (chromium, firefox, webkit) |
| `headless` | `true` | Run in headless mode |
| `slowmo` | `500` | Slow motion delay in milliseconds |
| `viewport.width` | `1920` | Browser viewport width |
| `viewport.height` | `1080` | Browser viewport height |
| `screenshots` | `true` | Take screenshots on failure |
| `record.video` | `false` | Record video of test execution |

## 📋 Test Scenarios

### Covered Test Cases

1. **Positive Login Tests**
   - Successful login with valid credentials
   - Logout functionality

2. **Negative Login Tests**
   - Login with invalid username
   - Login with invalid password
   - Login with empty credentials

3. **Validation Tests**
   - Username field validation
   - Password field validation
   - Form element presence

4. **UI Tests**
   - Form elements visibility
   - Password field masking
   - Field clearing functionality

5. **Security Tests**
   - Password field type verification

### Test Tags

| Tag | Purpose |
|-----|---------|
| `@demoqa` | All DemoQA tests |
| `@smoke` | Critical functionality tests |
| `@negative` | Negative scenario tests |
| `@validation` | Input validation tests |
| `@functional` | Functional behavior tests |
| `@ui` | User interface tests |
| `@security` | Security-related tests |

## 🔧 Customization Guide

### Adding New Test Scenarios

1. **Add to Feature File** (`demoqa-login.feature`):
```gherkin
@web @demoqa @new-feature
Scenario: Your new test scenario
  Given I navigate to DemoQA login page
  When I perform some action
  Then I should see expected result
```

2. **Implement Step Definitions** (`DemoQALoginSteps.java`):
```java
@When("I perform some action")
public void i_perform_some_action() {
    // Implementation
}

@Then("I should see expected result")
public void i_should_see_expected_result() {
    // Assertions
}
```

### Extending Page Objects

Add new methods to `DemoQALoginPage.java`:
```java
public void performNewAction() {
    // New functionality
}

public boolean verifyNewCondition() {
    // New verification
    return true;
}
```

### Adding New Page Objects

Create new page objects for other DemoQA sections:
```java
public class DemoQABooksPage extends BasePage {
    // Implementation for Books section
}
```

## 📊 Reports and Debugging

### Allure Reports

Generate and view detailed test reports:
```bash
# After running tests
allure serve allure-results
```

### Screenshots

Screenshots are automatically captured on test failures and stored in:
- `target/screenshots/`

### Videos

Enable video recording to capture test execution:
```bash
mvn test -Dtest=DemoQATestRunner -Drecord.video=true
```

Videos are stored in:
- `target/videos/`

### Debug Mode

Run tests in debug mode with visible browser and slow motion:
```bash
mvn test -Dtest=DemoQATestRunner -Dheadless=false -Dslowmo=1000
```

## 🐛 Troubleshooting

### Common Issues

1. **Login Failures**
   - Verify credentials are correct
   - Check if DemoQA website is accessible
   - Ensure no CAPTCHA or additional security measures

2. **Element Not Found**
   - DemoQA may update their UI - check locators in `DemoQALoginPage.java`
   - Increase timeout values if elements load slowly

3. **Browser Issues**
   - Try different browsers using `-Dbrowser` parameter
   - Update Playwright browsers: `mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"`

4. **Network Issues**
   - Verify internet connection
   - Check if corporate firewall blocks DemoQA

### Debugging Steps

1. **Enable Visual Mode**:
   ```bash
   mvn test -Dtest=DemoQATestRunner -Dheadless=false -Dslowmo=2000
   ```

2. **Check Logs**:
   - Console output for error messages
   - Allure reports for detailed execution steps

3. **Verify Configuration**:
   ```bash
   mvn test -Dtest=DemoQATestRunner -X
   ```

## 📝 Best Practices

1. **Test Data Management**
   - Use configuration properties for credentials
   - Don't hardcode sensitive data in test files

2. **Page Object Design**
   - Keep locators updated with DemoQA UI changes
   - Use explicit waits for dynamic content

3. **Test Maintenance**
   - Run tests regularly to detect DemoQA changes
   - Update selectors when UI changes

4. **Parallel Execution**
   - Use separate test data for parallel runs
   - Avoid shared state between tests

## 🔗 Related Documentation

- [Main Framework README](../../README.md)
- [Web Testing Guide](../../docs/WEB_TESTING_GUIDE.md)
- [Framework Development Guide](../../docs/FRAMEWORK_DEVELOPMENT_GUIDE.md)
- [Test Execution Guide](../../docs/TEST_EXECUTION_GUIDE.md)

---

**Happy Testing with DemoQA! 🚀**
