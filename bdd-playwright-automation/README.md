# BDD Playwright Automation Framework

A comprehensive test automation framework combining **Behavior-Driven Development (BDD)** with **Playwright** for web testing and **REST Assured** for API testing. This framework uses **Cucumber** for BDD implementation and **Allure** for detailed test reporting.

## 📋 Table of Contents

- [Framework Architecture](#framework-architecture)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Running Tests](#running-tests)
- [Generating Reports](#generating-reports)
- [Writing Tests](#writing-tests)
- [Configuration](#configuration)
- [Best Practices](#best-practices)
- [Troubleshooting](#troubleshooting)

## 🏗️ Framework Architecture

This framework follows a **hybrid approach** supporting both:
- **Web UI Testing** using Playwright with Page Object Model (POM)
- **API Testing** using REST Assured with dedicated client classes
- **BDD Implementation** with Cucumber for readable test scenarios
- **Comprehensive Reporting** with Allure integration

### Key Design Patterns:
- **Page Object Model (POM)** for web UI components
- **Client Pattern** for API interactions
- **Step Definition Pattern** for BDD implementation
- **Data Transfer Objects (DTOs)** for API request/response handling

## 🛠️ Technology Stack

| Component | Technology | Version |
|-----------|------------|---------|
| **Build Tool** | Maven | Latest |
| **Programming Language** | Java | 17 |
| **BDD Framework** | Cucumber | 7.16.1 |
| **Web Automation** | Playwright | 1.44.0 |
| **API Testing** | REST Assured | 5.4.0 |
| **Test Runner** | JUnit 5 | 5.10.2 |
| **Reporting** | Allure | 2.24.0 |
| **JSON Processing** | Jackson | 2.17.1 |

## 📁 Project Structure

```
bdd-playwright-automation/
├── src/
│   ├── main/java/com/framework/
│   │   ├── api/
│   │   │   ├── client/           # API client classes
│   │   │   │   └── UserClient.java
│   │   │   └── model/            # Data models for API
│   │   │       └── UserRequest.java
│   │   ├── config/               # Configuration classes
│   │   │   └── EnvironmentConfig.java
│   │   ├── utils/                # Utility classes
│   │   │   └── JsonUtil.java
│   │   └── web/
│   │       └── pages/            # Page Object Model classes
│   │           └── LoginPage.java
│   └── test/
│       ├── java/com/framework/
│       │   ├── api/stepdefs/     # API step definitions
│       │   │   └── ApiSteps.java
│       │   ├── runners/          # Test runners
│       │   │   └── RunAllTests.java
│       │   └── web/stepdefs/     # Web step definitions
│       │       └── LoginSteps.java
│       └── resources/
│           ├── features/         # Cucumber feature files
│           │   ├── api/
│           │   │   └── user.feature
│           │   └── web/
│           │       └── login.feature
│           └── login.html        # Test page for web testing
├── allure-results/               # Allure test results
├── allure-report/               # Generated Allure reports
├── target/                      # Maven build output
└── pom.xml                      # Maven configuration
```

## ✅ Prerequisites

Before setting up the framework, ensure you have:

### Required Software:
1. **Java Development Kit (JDK) 17 or higher**
   ```bash
   java --version
   ```

2. **Apache Maven 3.6 or higher**
   ```bash
   mvn --version
   ```

3. **Allure CLI** (for report generation)
   ```bash
   # Install via Homebrew (macOS)
   brew install allure
   
   # Install via npm (cross-platform)
   npm install -g allure-commandline
   
   # Verify installation
   allure --version
   ```

### Optional but Recommended:
- **IntelliJ IDEA** or **Visual Studio Code** with Java extensions
- **Git** for version control

## 🚀 Setup Instructions

### Step 1: Clone the Repository
```bash
git clone <repository-url>
cd bdd-playwright-automation
```

### Step 2: Install Dependencies
```bash
mvn clean install
```

### Step 3: Install Playwright Browsers
```bash
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

### Step 4: Verify Setup
```bash
mvn test
```

## 🏃‍♂️ Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Feature Files
```bash
# Run only API tests
mvn test -Dcucumber.filter.tags="@api"

# Run only Web tests  
mvn test -Dcucumber.filter.tags="@web"
```

### Run with Maven Profiles (if configured)
```bash
# Run in different environments
mvn test -Pdev
mvn test -Pstaging
mvn test -Pprod
```

### Command Line Options
```bash
# Run with specific browser (for web tests)
mvn test -Dbrowser=firefox

# Run in headed mode (visible browser)
mvn test -Dheadless=false

# Run with parallel execution
mvn test -Dthreads=4
```

## 📊 Generating Reports

### Method 1: Using Allure CLI (Recommended)

1. **Generate and Open Report**:
   ```bash
   allure serve allure-results
   ```
   This will automatically open the report in your default browser.

2. **Generate Static Report**:
   ```bash
   allure generate allure-results --clean -o allure-report
   allure open allure-report
   ```

### Method 2: Using Maven Plugin

1. **Generate Report**:
   ```bash
   mvn allure:report
   ```

2. **Serve Report**:
   ```bash
   mvn allure:serve
   ```

### Report Features
The Allure report includes:
- ✅ Test execution overview and statistics
- 📈 Trend analysis and history
- 🐛 Detailed failure analysis with screenshots
- ⏱️ Execution timeline and performance metrics
- 📋 Test categorization and filtering
- 📎 Attachments (screenshots, logs, etc.)

## ✍️ Writing Tests

### Creating Feature Files

Create new feature files in `src/test/resources/features/`:

```gherkin
# Example: src/test/resources/features/api/product.feature
Feature: Product API Testing

  @api @smoke
  Scenario: Get product details
    Given the product service is available
    When I request product with id "123"
    Then the response status should be 200
    And the response should contain product name "Sample Product"

  @api @regression  
  Scenario: Create new product
    Given the product service is available
    When I create a product with name "New Product" and price "99.99"
    Then the response status should be 201
    And the product should be created successfully
```

### Implementing Step Definitions

Create corresponding step definitions in `src/test/java/com/framework/*/stepdefs/`:

```java
// Example: API Step Definitions
@Given("the product service is available")
public void the_product_service_is_available() {
    // Verify API endpoint is accessible
}

@When("I request product with id {string}")
public void i_request_product_with_id(String productId) {
    response = productClient.getProduct(productId);
}

@Then("the response status should be {int}")
public void the_response_status_should_be(int expectedStatus) {
    assertEquals(expectedStatus, response.getStatusCode());
}
```

### Creating Page Objects

For web testing, create page objects in `src/main/java/com/framework/web/pages/`:

```java
public class ProductPage {
    private final Page page;
    
    // Locators
    private static final String SEARCH_INPUT = "#search-input";
    private static final String SEARCH_BUTTON = "#search-btn";
    private static final String PRODUCT_TITLE = ".product-title";
    
    public ProductPage(Page page) {
        this.page = page;
    }
    
    public void searchProduct(String productName) {
        page.fill(SEARCH_INPUT, productName);
        page.click(SEARCH_BUTTON);
    }
    
    public String getProductTitle() {
        return page.textContent(PRODUCT_TITLE);
    }
}
```

### Creating API Clients

For API testing, create client classes in `src/main/java/com/framework/api/client/`:

```java
public class ProductClient {
    private static final String BASE_URL = "https://api.example.com";
    
    public Response getProduct(String productId) {
        return given()
            .baseUri(BASE_URL)
            .when()
            .get("/products/" + productId);
    }
    
    public Response createProduct(ProductRequest request) {
        return given()
            .baseUri(BASE_URL)
            .header("Content-Type", "application/json")
            .body(request)
            .when()
            .post("/products");
    }
}
```

## ⚙️ Configuration

### Environment Configuration

Create environment-specific properties in `src/main/java/com/framework/config/`:

```java
public class EnvironmentConfig {
    public static final String BASE_URL = System.getProperty("base.url", "https://staging.example.com");
    public static final String API_URL = System.getProperty("api.url", "https://api.staging.example.com");
    public static final boolean HEADLESS = Boolean.parseBoolean(System.getProperty("headless", "true"));
    public static final String BROWSER = System.getProperty("browser", "chromium");
}
```

### Maven Configuration

Key configurations in `pom.xml`:

```xml
<!-- Surefire Plugin Configuration -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.2.5</version>
    <configuration>
        <includes>
            <include>**/RunAllTests.java</include>
        </includes>
        <systemPropertyVariables>
            <headless>${headless}</headless>
            <browser>${browser}</browser>
        </systemPropertyVariables>
    </configuration>
</plugin>
```

## 🎯 Best Practices

### Test Organization
- ✅ Use meaningful feature and scenario names
- ✅ Group related scenarios in the same feature file
- ✅ Use tags for test categorization (`@smoke`, `@regression`, `@api`, `@web`)
- ✅ Keep scenarios independent and atomic

### Page Object Model
- ✅ Use descriptive locator names
- ✅ Implement explicit waits for element interactions
- ✅ Separate page actions from assertions
- ✅ Use constants for locators

### API Testing
- ✅ Use separate client classes for different API modules
- ✅ Implement proper request/response models
- ✅ Add appropriate headers and authentication
- ✅ Validate both positive and negative scenarios

### Code Quality
- ✅ Follow Java naming conventions
- ✅ Add meaningful comments and documentation
- ✅ Use dependency injection where appropriate
- ✅ Implement proper exception handling

### Reporting
- ✅ Add screenshots for failed web tests
- ✅ Include API request/response details in reports
- ✅ Use Allure annotations for better reporting (`@Step`, `@Description`)
- ✅ Attach relevant logs and data

## 🔧 Troubleshooting

### Common Issues and Solutions

#### 1. Playwright Browser Installation Issues
```bash
# Error: Browser not found
# Solution: Install browsers explicitly
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

#### 2. Allure Report Not Generating
```bash
# Error: Allure command not found
# Solution: Install Allure CLI
brew install allure  # macOS
npm install -g allure-commandline  # Cross-platform
```

#### 3. Test Discovery Issues
- Ensure test runner class (`RunAllTests.java`) is in the correct package
- Verify feature files are in `src/test/resources/features/`
- Check step definition packages are correctly configured in runner

#### 4. Dependency Conflicts
```bash
# Solution: Clean and reinstall dependencies
mvn clean install -U
```

#### 5. Port Already in Use (Allure Server)
```bash
# Error: Port already in use
# Solution: Use different port
allure serve allure-results --port 8081
```

### Debug Mode
Enable debug logging by adding to JVM arguments:
```bash
mvn test -Dlogging.level.root=DEBUG
```

### Getting Help
- Check Maven logs for detailed error messages
- Verify Java and Maven versions meet requirements
- Ensure all dependencies are properly resolved
- Review feature files and step definitions for syntax errors

---

## 📞 Support

For issues or questions:
1. Check this documentation first
2. Review error logs and stack traces
3. Verify environment setup and dependencies
4. Create detailed issue reports with reproduction steps

**Happy Testing! 🚀**
