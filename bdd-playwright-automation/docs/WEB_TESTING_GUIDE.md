# Web Testing Guide

This comprehensive guide covers web UI testing using Playwright within the BDD Playwright Automation Framework, implementing the Page Object Model pattern.

## 🎯 Overview

The framework provides robust web UI testing capabilities using Microsoft Playwright, integrated with Cucumber BDD for readable test scenarios and comprehensive Allure reporting with screenshots and video recording.

## 🏗️ Web Testing Architecture

### Components Structure
```
src/main/java/com/framework/web/
├── pages/               # Page Object Model classes
│   ├── LoginPage.java
│   ├── HomePage.java
│   └── BasePage.java
├── components/          # Reusable UI components
│   ├── Header.java
│   ├── Navigation.java
│   └── Modal.java
└── utils/               # Web-specific utilities
    ├── ElementUtils.java
    └── WaitUtils.java

src/test/java/com/framework/web/
└── stepdefs/            # Web step definitions
    ├── LoginSteps.java
    ├── NavigationSteps.java
    └── CommonWebSteps.java
```

## 🖥️ Browser Configuration

### Browser Types and Setup

```java
package com.framework.config;

public class BrowserConfig {
    
    public enum BrowserType {
        CHROMIUM("chromium"),
        FIREFOX("firefox"),
        WEBKIT("webkit");
        
        private final String name;
        
        BrowserType(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
    }
    
    public static Browser createBrowser(BrowserType browserType) {
        Playwright playwright = Playwright.create();
        
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
            .setHeadless(Boolean.parseBoolean(System.getProperty("headless", "true")))
            .setSlowMo(Integer.parseInt(System.getProperty("slowmo", "0")))
            .setDevtools(Boolean.parseBoolean(System.getProperty("devtools", "false")));
        
        switch (browserType) {
            case CHROMIUM:
                return playwright.chromium().launch(options);
            case FIREFOX:
                return playwright.firefox().launch(options);
            case WEBKIT:
                return playwright.webkit().launch(options);
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }
    }
    
    public static BrowserContext createContext(Browser browser) {
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
            .setViewportSize(1920, 1080)
            .setLocale("en-US")
            .setTimezoneId("America/New_York");
        
        // Enable video recording if specified
        if (Boolean.parseBoolean(System.getProperty("record.video", "false"))) {
            contextOptions.setRecordVideoDir(Paths.get("videos/"));
        }
        
        return browser.newContext(contextOptions);
    }
}
```

## 📄 Page Object Model Implementation

### 1. Base Page Class

```java
package com.framework.web.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.Step;

public abstract class BasePage {
    protected final Page page;
    
    public BasePage(Page page) {
        this.page = page;
    }
    
    // Common page actions
    @Step("Navigate to URL: {url}")
    public void navigateTo(String url) {
        page.navigate(url);
        waitForPageLoad();
    }
    
    @Step("Get page title")
    public String getPageTitle() {
        return page.title();
    }
    
    @Step("Get current URL")
    public String getCurrentUrl() {
        return page.url();
    }
    
    @Step("Take screenshot")
    public byte[] takeScreenshot() {
        return page.screenshot();
    }
    
    // Wait utilities
    protected void waitForPageLoad() {
        page.waitForLoadState();
    }
    
    protected void waitForElement(String selector) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions()
            .setState(WaitForSelectorState.VISIBLE)
            .setTimeout(10000));
    }
    
    protected void waitForElementToBeHidden(String selector) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions()
            .setState(WaitForSelectorState.HIDDEN)
            .setTimeout(10000));
    }
    
    // Element interaction utilities
    protected void clickElement(String selector) {
        waitForElement(selector);
        page.click(selector);
    }
    
    protected void fillField(String selector, String text) {
        waitForElement(selector);
        page.fill(selector, text);
    }
    
    protected void selectOption(String selector, String value) {
        waitForElement(selector);
        page.selectOption(selector, value);
    }
    
    protected String getText(String selector) {
        waitForElement(selector);
        return page.textContent(selector);
    }
    
    protected boolean isElementVisible(String selector) {
        return page.isVisible(selector);
    }
    
    protected boolean isElementEnabled(String selector) {
        return page.isEnabled(selector);
    }
    
    // Verification utilities
    protected void verifyElementText(String selector, String expectedText) {
        String actualText = getText(selector);
        if (!actualText.equals(expectedText)) {
            throw new AssertionError(
                String.format("Expected text '%s' but found '%s'", expectedText, actualText));
        }
    }
    
    protected void verifyElementVisible(String selector) {
        if (!isElementVisible(selector)) {
            throw new AssertionError("Element should be visible: " + selector);
        }
    }
    
    protected void verifyElementHidden(String selector) {
        if (isElementVisible(selector)) {
            throw new AssertionError("Element should be hidden: " + selector);
        }
    }
}
```

### 2. Specific Page Implementation

```java
package com.framework.web.pages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class LoginPage extends BasePage {
    
    // Locators
    private static final String USERNAME_FIELD = "#username";
    private static final String PASSWORD_FIELD = "#password";
    private static final String LOGIN_BUTTON = "#loginBtn";
    private static final String ERROR_MESSAGE = ".error-message";
    private static final String SUCCESS_MESSAGE = ".success-message";
    private static final String FORGOT_PASSWORD_LINK = "a[href*='forgot-password']";
    private static final String REMEMBER_ME_CHECKBOX = "#rememberMe";
    
    public LoginPage(Page page) {
        super(page);
    }
    
    // Page actions
    @Step("Enter username: {username}")
    public LoginPage enterUsername(String username) {
        fillField(USERNAME_FIELD, username);
        return this;
    }
    
    @Step("Enter password")
    public LoginPage enterPassword(String password) {
        fillField(PASSWORD_FIELD, password);
        return this;
    }
    
    @Step("Click login button")
    public void clickLoginButton() {
        clickElement(LOGIN_BUTTON);
    }
    
    @Step("Click forgot password link")
    public void clickForgotPasswordLink() {
        clickElement(FORGOT_PASSWORD_LINK);
    }
    
    @Step("Check remember me option")
    public LoginPage checkRememberMe() {
        if (!page.isChecked(REMEMBER_ME_CHECKBOX)) {
            clickElement(REMEMBER_ME_CHECKBOX);
        }
        return this;
    }
    
    @Step("Perform complete login with username: {username}")
    public void performLogin(String username, String password) {
        enterUsername(username)
            .enterPassword(password)
            .clickLoginButton();
    }
    
    // Verification methods
    @Step("Verify login was successful")
    public boolean isLoginSuccessful() {
        try {
            waitForElement(SUCCESS_MESSAGE);
            return isElementVisible(SUCCESS_MESSAGE);
        } catch (Exception e) {
            return false;
        }
    }
    
    @Step("Get error message")
    public String getErrorMessage() {
        if (isElementVisible(ERROR_MESSAGE)) {
            return getText(ERROR_MESSAGE);
        }
        return "";
    }
    
    @Step("Verify error message is displayed")
    public boolean isErrorMessageDisplayed() {
        return isElementVisible(ERROR_MESSAGE);
    }
    
    @Step("Verify username field is empty")
    public boolean isUsernameFieldEmpty() {
        return page.inputValue(USERNAME_FIELD).isEmpty();
    }
    
    @Step("Verify login button is enabled")
    public boolean isLoginButtonEnabled() {
        return isElementEnabled(LOGIN_BUTTON);
    }
    
    // Form validation
    @Step("Clear all fields")
    public void clearAllFields() {
        page.fill(USERNAME_FIELD, "");
        page.fill(PASSWORD_FIELD, "");
    }
    
    @Step("Verify form validation messages")
    public void verifyFormValidation() {
        // Check for HTML5 validation or custom validation messages
        String usernameValidationMessage = page.getAttribute(USERNAME_FIELD, "validationMessage");
        String passwordValidationMessage = page.getAttribute(PASSWORD_FIELD, "validationMessage");
        
        // Add assertions based on your application's validation behavior
    }
}
```

### 3. Component Classes

```java
package com.framework.web.components;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class NavigationHeader {
    private final Page page;
    
    // Locators
    private static final String LOGO = ".logo";
    private static final String USER_MENU = ".user-menu";
    private static final String LOGOUT_BUTTON = "#logout";
    private static final String PROFILE_LINK = "a[href*='profile']";
    private static final String SETTINGS_LINK = "a[href*='settings']";
    
    public NavigationHeader(Page page) {
        this.page = page;
    }
    
    @Step("Click on logo")
    public void clickLogo() {
        page.click(LOGO);
    }
    
    @Step("Open user menu")
    public void openUserMenu() {
        page.click(USER_MENU);
    }
    
    @Step("Click logout")
    public void logout() {
        openUserMenu();
        page.click(LOGOUT_BUTTON);
    }
    
    @Step("Navigate to profile")
    public void goToProfile() {
        openUserMenu();
        page.click(PROFILE_LINK);
    }
    
    @Step("Navigate to settings")
    public void goToSettings() {
        openUserMenu();
        page.click(SETTINGS_LINK);
    }
    
    @Step("Verify user is logged in")
    public boolean isUserLoggedIn() {
        return page.isVisible(USER_MENU);
    }
}
```

## 📝 Writing Web Tests

### 1. Feature File Structure

```gherkin
Feature: User Login

  Background:
    Given I am on the login page

  @web @smoke
  Scenario: Successful login with valid credentials
    When I enter username "testuser" and password "password123"
    And I click the login button
    Then I should be logged in successfully
    And I should see the dashboard page

  @web @negative
  Scenario: Login with invalid credentials
    When I enter username "testuser" and password "wrongpassword"
    And I click the login button
    Then I should see an error message "Invalid credentials"
    And I should remain on the login page

  @web @validation
  Scenario: Login form validation
    When I click the login button without entering credentials
    Then I should see validation messages
    And the login button should be disabled

  @web @ui
  Scenario: Remember me functionality
    When I enter username "testuser" and password "password123"
    And I check the "Remember Me" option
    And I click the login button
    Then I should be logged in successfully
    And the session should be remembered

  @web @responsive
  Scenario: Login on mobile device
    Given I am using a mobile device
    When I enter username "testuser" and password "password123"
    And I click the login button
    Then I should be logged in successfully
    And the mobile navigation should be visible
```

### 2. Step Definitions Implementation

```java
package com.framework.web.stepdefs;

import com.framework.web.pages.LoginPage;
import com.framework.web.pages.DashboardPage;
import com.framework.web.components.NavigationHeader;
import com.framework.config.TestConfiguration;
import com.microsoft.playwright.*;
import io.cucumber.java.en.*;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class LoginSteps {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private NavigationHeader navigationHeader;
    private Page page;
    
    public LoginSteps() {
        // Page instances will be injected through hooks or dependency injection
    }
    
    @Given("I am on the login page")
    @Step("Navigate to login page")
    public void i_am_on_the_login_page() {
        String loginUrl = TestConfiguration.getInstance().getBaseUrl() + "/login";
        loginPage = new LoginPage(page);
        loginPage.navigateTo(loginUrl);
        
        // Verify we're on the correct page
        Assertions.assertTrue(page.url().contains("/login"), 
            "Should be on login page");
    }
    
    @Given("I am using a mobile device")
    @Step("Set mobile viewport")
    public void i_am_using_mobile_device() {
        // Set mobile viewport
        page.setViewportSize(375, 667); // iPhone dimensions
    }
    
    @When("I enter username {string} and password {string}")
    @Step("Enter login credentials - Username: {0}")
    public void i_enter_credentials(String username, String password) {
        loginPage.enterUsername(username)
                 .enterPassword(password);
    }
    
    @When("I click the login button")
    @Step("Click login button")
    public void i_click_login_button() {
        loginPage.clickLoginButton();
    }
    
    @When("I click the login button without entering credentials")
    @Step("Attempt login without credentials")
    public void i_click_login_button_without_credentials() {
        loginPage.clickLoginButton();
    }
    
    @When("I check the {string} option")
    @Step("Check option: {0}")
    public void i_check_option(String option) {
        if (option.equals("Remember Me")) {
            loginPage.checkRememberMe();
        }
    }
    
    @Then("I should be logged in successfully")
    @Step("Verify successful login")
    public void i_should_be_logged_in_successfully() {
        Assertions.assertTrue(loginPage.isLoginSuccessful(), 
            "Login should be successful");
    }
    
    @Then("I should see the dashboard page")
    @Step("Verify dashboard page is displayed")
    public void i_should_see_dashboard_page() {
        dashboardPage = new DashboardPage(page);
        Assertions.assertTrue(dashboardPage.isDisplayed(), 
            "Dashboard page should be displayed");
    }
    
    @Then("I should see an error message {string}")
    @Step("Verify error message: {0}")
    public void i_should_see_error_message(String expectedMessage) {
        Assertions.assertTrue(loginPage.isErrorMessageDisplayed(), 
            "Error message should be displayed");
        
        String actualMessage = loginPage.getErrorMessage();
        Assertions.assertEquals(expectedMessage, actualMessage, 
            "Error message should match expected text");
    }
    
    @Then("I should remain on the login page")
    @Step("Verify still on login page")
    public void i_should_remain_on_login_page() {
        Assertions.assertTrue(page.url().contains("/login"), 
            "Should still be on login page");
    }
    
    @Then("I should see validation messages")
    @Step("Verify form validation messages")
    public void i_should_see_validation_messages() {
        loginPage.verifyFormValidation();
    }
    
    @Then("the login button should be disabled")
    @Step("Verify login button is disabled")
    public void login_button_should_be_disabled() {
        Assertions.assertFalse(loginPage.isLoginButtonEnabled(), 
            "Login button should be disabled");
    }
    
    @Then("the session should be remembered")
    @Step("Verify session persistence")
    public void session_should_be_remembered() {
        // Refresh page and verify user is still logged in
        page.reload();
        navigationHeader = new NavigationHeader(page);
        Assertions.assertTrue(navigationHeader.isUserLoggedIn(), 
            "User should remain logged in after page refresh");
    }
    
    @Then("the mobile navigation should be visible")
    @Step("Verify mobile navigation")
    public void mobile_navigation_should_be_visible() {
        // Verify mobile-specific navigation elements
        Assertions.assertTrue(page.isVisible(".mobile-nav"), 
            "Mobile navigation should be visible");
    }
}
```

### 3. Test Hooks for Web Testing

```java
package com.framework.hooks;

import com.framework.config.BrowserConfig;
import com.framework.utils.ThreadLocalManager;
import com.microsoft.playwright.*;
import io.cucumber.java.*;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;

public class WebTestHooks {
    private static Playwright playwright;
    private static Browser browser;
    
    @BeforeAll
    public static void setupPlaywright() {
        playwright = Playwright.create();
        
        String browserType = System.getProperty("browser", "chromium");
        browser = BrowserConfig.createBrowser(
            BrowserConfig.BrowserType.valueOf(browserType.toUpperCase()));
    }
    
    @Before("@web")
    public void setupWebTest(Scenario scenario) {
        BrowserContext context = BrowserConfig.createContext(browser);
        Page page = context.newPage();
        
        // Store in thread-local for parallel execution
        ThreadLocalManager.setContext(context);
        ThreadLocalManager.setPage(page);
        
        // Enable console logging
        page.onConsoleMessage(msg -> 
            System.out.println("Console: " + msg.text()));
        
        // Enable request/response logging if needed
        if (Boolean.parseBoolean(System.getProperty("log.network", "false"))) {
            page.onRequest(request -> 
                System.out.println("Request: " + request.url()));
            page.onResponse(response -> 
                System.out.println("Response: " + response.url() + " - " + response.status()));
        }
    }
    
    @After("@web")
    public void teardownWebTest(Scenario scenario) {
        Page page = ThreadLocalManager.getPage();
        BrowserContext context = ThreadLocalManager.getContext();
        
        if (scenario.isFailed() && page != null) {
            // Take screenshot on failure
            byte[] screenshot = page.screenshot();
            Allure.addAttachment("Screenshot on Failure", "image/png", 
                new ByteArrayInputStream(screenshot), "png");
            
            // Add page source
            String pageSource = page.content();
            Allure.addAttachment("Page Source", "text/html", pageSource, "html");
            
            // Add console logs
            StringBuilder consoleLogs = new StringBuilder();
            page.onConsoleMessage(msg -> consoleLogs.append(msg.text()).append("\n"));
            if (consoleLogs.length() > 0) {
                Allure.addAttachment("Console Logs", consoleLogs.toString());
            }
        }
        
        // Cleanup
        if (context != null) {
            context.close();
        }
        ThreadLocalManager.cleanup();
    }
    
    @AfterAll
    public static void teardownPlaywright() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
```

## 🔧 Advanced Web Testing Features

### 1. File Upload and Download

```java
public class FileOperationsPage extends BasePage {
    
    private static final String FILE_INPUT = "input[type='file']";
    private static final String DOWNLOAD_LINK = "a[download]";
    
    public FileOperationsPage(Page page) {
        super(page);
    }
    
    @Step("Upload file: {fileName}")
    public void uploadFile(String fileName) {
        Path filePath = Paths.get("src/test/resources/testfiles/" + fileName);
        page.setInputFiles(FILE_INPUT, filePath);
    }
    
    @Step("Download file")
    public Path downloadFile() {
        Download download = page.waitForDownload(() -> {
            page.click(DOWNLOAD_LINK);
        });
        
        Path downloadPath = Paths.get("downloads/" + download.suggestedFilename());
        download.saveAs(downloadPath);
        return downloadPath;
    }
}
```

### 2. Iframe Handling

```java
public class IframePage extends BasePage {
    
    private static final String IFRAME_SELECTOR = "#content-iframe";
    
    public IframePage(Page page) {
        super(page);
    }
    
    @Step("Switch to iframe and perform action")
    public void interactWithIframe() {
        Frame iframe = page.frame(IFRAME_SELECTOR);
        
        // Perform actions within iframe
        iframe.click("#button-in-iframe");
        iframe.fill("#input-in-iframe", "test data");
        
        // Switch back to main frame automatically handled by Playwright
    }
}
```

### 3. Alert and Dialog Handling

```java
public class AlertsPage extends BasePage {
    
    public AlertsPage(Page page) {
        super(page);
    }
    
    @Step("Handle alert dialog")
    public void handleAlert(String action, String inputText) {
        page.onDialog(dialog -> {
            System.out.println("Dialog message: " + dialog.message());
            
            switch (action.toLowerCase()) {
                case "accept":
                    dialog.accept();
                    break;
                case "dismiss":
                    dialog.dismiss();
                    break;
                case "input":
                    dialog.accept(inputText);
                    break;
            }
        });
        
        // Trigger the dialog
        page.click("#alert-button");
    }
}
```

### 4. Responsive Testing

```java
public class ResponsiveTestUtils {
    
    public enum DeviceType {
        DESKTOP(1920, 1080),
        TABLET(768, 1024),
        MOBILE(375, 667);
        
        private final int width;
        private final int height;
        
        DeviceType(int width, int height) {
            this.width = width;
            this.height = height;
        }
        
        public int getWidth() { return width; }
        public int getHeight() { return height; }
    }
    
    @Step("Set viewport to {deviceType}")
    public static void setViewport(Page page, DeviceType deviceType) {
        page.setViewportSize(deviceType.getWidth(), deviceType.getHeight());
    }
    
    @Step("Verify responsive layout")
    public static void verifyResponsiveLayout(Page page, String selector, DeviceType deviceType) {
        setViewport(page, deviceType);
        
        // Wait for layout adjustment
        page.waitForTimeout(500);
        
        // Verify element visibility and positioning based on device type
        boolean isVisible = page.isVisible(selector);
        BoundingBox boundingBox = page.locator(selector).boundingBox();
        
        // Add device-specific assertions
        switch (deviceType) {
            case MOBILE:
                // Mobile-specific verifications
                break;
            case TABLET:
                // Tablet-specific verifications
                break;
            case DESKTOP:
                // Desktop-specific verifications
                break;
        }
    }
}
```

### 5. Performance Monitoring

```java
public class PerformanceUtils {
    
    @Step("Measure page load time")
    public static long measurePageLoadTime(Page page, String url) {
        long startTime = System.currentTimeMillis();
        
        page.navigate(url);
        page.waitForLoadState();
        
        long endTime = System.currentTimeMillis();
        long loadTime = endTime - startTime;
        
        Allure.addAttachment("Page Load Time", loadTime + "ms");
        return loadTime;
    }
    
    @Step("Get network performance metrics")
    public static void captureNetworkMetrics(Page page) {
        // Enable network domain
        page.route("**/*", route -> {
            System.out.println("Request: " + route.request().url() + 
                " - Size: " + route.request().postData().length());
            route.continue_();
        });
        
        page.onResponse(response -> {
            System.out.println("Response: " + response.url() + 
                " - Status: " + response.status() + 
                " - Time: " + response.request().timing().responseEnd);
        });
    }
}
```

## 🔍 Debugging and Troubleshooting

### 1. Debug Mode

```java
// Enable debug mode with visible browser and slow motion
System.setProperty("headless", "false");
System.setProperty("slowmo", "1000");
System.setProperty("devtools", "true");
```

### 2. Element Inspection

```java
public class DebugUtils {
    
    @Step("Highlight element for debugging")
    public static void highlightElement(Page page, String selector) {
        page.evaluate("(selector) => {" +
            "const element = document.querySelector(selector);" +
            "if (element) {" +
            "  element.style.border = '3px solid red';" +
            "  element.style.backgroundColor = 'yellow';" +
            "}" +
            "}", selector);
    }
    
    @Step("Log element properties")
    public static void logElementProperties(Page page, String selector) {
        Object properties = page.evaluate("(selector) => {" +
            "const element = document.querySelector(selector);" +
            "if (element) {" +
            "  return {" +
            "    tagName: element.tagName," +
            "    id: element.id," +
            "    className: element.className," +
            "    textContent: element.textContent," +
            "    visible: window.getComputedStyle(element).display !== 'none'" +
            "  };" +
            "}" +
            "return null;" +
            "}", selector);
        
        System.out.println("Element properties: " + properties);
    }
}
```

### 3. Custom Wait Conditions

```java
public class CustomWaitConditions {
    
    public static void waitForElementText(Page page, String selector, String expectedText) {
        page.waitForCondition(() -> {
            String actualText = page.textContent(selector);
            return actualText != null && actualText.contains(expectedText);
        });
    }
    
    public static void waitForElementAttribute(Page page, String selector, 
                                             String attribute, String expectedValue) {
        page.waitForCondition(() -> {
            String actualValue = page.getAttribute(selector, attribute);
            return expectedValue.equals(actualValue);
        });
    }
    
    public static void waitForUrlContains(Page page, String urlPart) {
        page.waitForCondition(() -> page.url().contains(urlPart));
    }
}
```

## 📋 Best Practices

### 1. Page Object Design
- ✅ Keep locators as private constants
- ✅ Use method chaining for fluent interface
- ✅ Implement wait conditions in base methods
- ✅ Separate actions from assertions
- ✅ Use descriptive method names

### 2. Test Organization
- ✅ Use meaningful scenario names
- ✅ Group related tests in feature files
- ✅ Implement proper test data setup/cleanup
- ✅ Use tags for test categorization

### 3. Element Handling
- ✅ Always use explicit waits
- ✅ Handle dynamic content appropriately
- ✅ Use stable locators (avoid fragile selectors)
- ✅ Implement proper error handling

### 4. Cross-Browser Testing
- ✅ Test on multiple browsers (Chromium, Firefox, WebKit)
- ✅ Verify responsive behavior
- ✅ Handle browser-specific differences
- ✅ Use consistent viewport sizes

### 5. Performance and Reliability
- ✅ Optimize test execution time
- ✅ Handle flaky elements properly
- ✅ Use appropriate timeouts
- ✅ Implement retry mechanisms where needed

---
*Refer to the main README.md for setup instructions and general framework information.*
