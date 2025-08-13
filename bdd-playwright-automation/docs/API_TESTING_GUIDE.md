# API Testing Guide

This guide covers comprehensive API testing strategies and best practices using REST Assured within the BDD Playwright Automation Framework.

## 🎯 Overview

The framework provides robust API testing capabilities using REST Assured, integrated with Cucumber BDD for readable test scenarios and Allure for detailed reporting.

## 🏗️ API Testing Architecture

### Components Structure
```
src/main/java/com/framework/api/
├── client/              # API client classes
│   ├── UserClient.java
│   └── ProductClient.java
├── model/               # Request/Response DTOs
│   ├── UserRequest.java
│   ├── UserResponse.java
│   └── ErrorResponse.java
└── utils/               # API utilities
    ├── ApiUtils.java
    └── JsonUtils.java

src/test/java/com/framework/api/
└── stepdefs/            # API step definitions
    ├── UserSteps.java
    └── CommonApiSteps.java
```

## 📝 Writing API Tests

### 1. Feature File Structure

```gherkin
Feature: User Management API

  Background:
    Given the API base URL is configured
    And the API service is available

  @api @smoke
  Scenario: Get user by ID
    When I send a GET request to "/users/1"
    Then the response status code should be 200
    And the response should contain user details
    And the response time should be less than 2000ms

  @api @crud
  Scenario: Create a new user
    Given I have a valid user payload:
      | name     | username | email           |
      | John Doe | johndoe  | john@example.com |
    When I send a POST request to "/users"
    Then the response status code should be 201
    And the response should contain the created user ID
    And the response should match the user schema

  @api @validation
  Scenario: Create user with invalid data
    Given I have an invalid user payload:
      | name | username | email |
      |      | johndoe  | invalid-email |
    When I send a POST request to "/users"
    Then the response status code should be 400
    And the response should contain validation errors

  @api @security
  Scenario: Access protected endpoint without authentication
    When I send a GET request to "/users/profile" without authentication
    Then the response status code should be 401
    And the response should contain "Unauthorized" message
```

### 2. API Client Implementation

```java
package com.framework.api.client;

import com.framework.api.model.*;
import com.framework.config.TestConfiguration;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class UserClient {
    private final String baseUrl;
    private final TestConfiguration config;
    
    public UserClient() {
        this.config = TestConfiguration.getInstance();
        this.baseUrl = config.getApiUrl();
    }
    
    // Basic CRUD Operations
    public Response getUser(int userId) {
        return getBaseRequest()
            .when()
            .get("/users/" + userId);
    }
    
    public Response getAllUsers() {
        return getBaseRequest()
            .when()
            .get("/users");
    }
    
    public Response createUser(UserRequest request) {
        return getBaseRequest()
            .body(request)
            .when()
            .post("/users");
    }
    
    public Response updateUser(int userId, UserRequest request) {
        return getBaseRequest()
            .body(request)
            .when()
            .put("/users/" + userId);
    }
    
    public Response partialUpdateUser(int userId, Object request) {
        return getBaseRequest()
            .body(request)
            .when()
            .patch("/users/" + userId);
    }
    
    public Response deleteUser(int userId) {
        return getBaseRequest()
            .when()
            .delete("/users/" + userId);
    }
    
    // Advanced Operations
    public Response searchUsers(String query) {
        return getBaseRequest()
            .queryParam("q", query)
            .when()
            .get("/users/search");
    }
    
    public Response getUsersWithPagination(int page, int size) {
        return getBaseRequest()
            .queryParam("page", page)
            .queryParam("size", size)
            .when()
            .get("/users");
    }
    
    public Response getUsersByStatus(String status) {
        return getBaseRequest()
            .queryParam("status", status)
            .when()
            .get("/users");
    }
    
    // Authentication
    public Response loginUser(String username, String password) {
        LoginRequest loginRequest = new LoginRequest(username, password);
        return getBaseRequest()
            .body(loginRequest)
            .when()
            .post("/auth/login");
    }
    
    public Response getProtectedUserProfile(String token) {
        return getAuthenticatedRequest(token)
            .when()
            .get("/users/profile");
    }
    
    // Base request configurations
    private RequestSpecification getBaseRequest() {
        return given()
            .baseUri(baseUrl)
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .log().all(); // Enable request logging
    }
    
    private RequestSpecification getAuthenticatedRequest(String token) {
        return getBaseRequest()
            .header("Authorization", "Bearer " + token);
    }
}
```

### 3. Data Models (DTOs)

**Request Models:**
```java
package com.framework.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;

public class UserRequest {
    @NotBlank(message = "Name is required")
    @JsonProperty("name")
    private String name;
    
    @NotBlank(message = "Username is required")
    @JsonProperty("username")
    private String username;
    
    @Email(message = "Invalid email format")
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("phone")
    private String phone;
    
    @JsonProperty("website")
    private String website;
    
    @JsonProperty("address")
    private Address address;
    
    @JsonProperty("company")
    private Company company;
    
    // Constructors
    public UserRequest() {}
    
    public UserRequest(String name, String username, String email) {
        this.name = name;
        this.username = username;
        this.email = email;
    }
    
    // Getters and Setters
    // ... (standard getters and setters)
    
    // Builder Pattern
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        private UserRequest request = new UserRequest();
        
        public Builder name(String name) {
            request.setName(name);
            return this;
        }
        
        public Builder username(String username) {
            request.setUsername(username);
            return this;
        }
        
        public Builder email(String email) {
            request.setEmail(email);
            return this;
        }
        
        public Builder phone(String phone) {
            request.setPhone(phone);
            return this;
        }
        
        public UserRequest build() {
            return request;
        }
    }
}
```

**Response Models:**
```java
package com.framework.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {
    @JsonProperty("id")
    private Integer id;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("username")
    private String username;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("phone")
    private String phone;
    
    @JsonProperty("website")
    private String website;
    
    @JsonProperty("address")
    private Address address;
    
    @JsonProperty("company")
    private Company company;
    
    // Getters and Setters
    // ... (standard getters and setters)
}

public class ErrorResponse {
    @JsonProperty("error")
    private String error;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("code")
    private String code;
    
    @JsonProperty("details")
    private List<String> details;
    
    // Getters and Setters
    // ... (standard getters and setters)
}
```

### 4. Step Definitions

```java
package com.framework.api.stepdefs;

import com.framework.api.client.UserClient;
import com.framework.api.model.*;
import com.framework.utils.JsonSchemaValidator;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import java.util.Map;

public class UserApiSteps {
    private UserClient userClient;
    private Response response;
    private UserRequest userRequest;
    private String authToken;
    
    public UserApiSteps() {
        this.userClient = new UserClient();
    }
    
    // Background Steps
    @Given("the API base URL is configured")
    @Step("Configure API base URL")
    public void the_api_base_url_is_configured() {
        // Configuration is handled in UserClient constructor
        Allure.addAttachment("API Base URL", userClient.getBaseUrl());
    }
    
    @Given("the API service is available")
    @Step("Verify API service availability")
    public void the_api_service_is_available() {
        response = userClient.healthCheck();
        Assertions.assertEquals(200, response.getStatusCode(), 
            "API service is not available");
    }
    
    // Request Steps
    @When("I send a GET request to {string}")
    @Step("Send GET request to: {0}")
    public void i_send_get_request_to(String endpoint) {
        if (endpoint.contains("/users/")) {
            String userId = endpoint.split("/users/")[1];
            response = userClient.getUser(Integer.parseInt(userId));
        } else if (endpoint.equals("/users")) {
            response = userClient.getAllUsers();
        }
        
        addRequestResponseToReport("GET", endpoint, null, response);
    }
    
    @When("I send a POST request to {string}")
    @Step("Send POST request to: {0}")
    public void i_send_post_request_to(String endpoint) {
        response = userClient.createUser(userRequest);
        addRequestResponseToReport("POST", endpoint, userRequest, response);
    }
    
    @When("I send a GET request to {string} without authentication")
    @Step("Send unauthenticated GET request to: {0}")
    public void i_send_get_request_without_auth(String endpoint) {
        response = userClient.getProtectedUserProfile(null);
        addRequestResponseToReport("GET", endpoint, null, response);
    }
    
    // Data Preparation Steps
    @Given("I have a valid user payload:")
    @Step("Prepare valid user payload")
    public void i_have_valid_user_payload(DataTable dataTable) {
        Map<String, String> userData = dataTable.asMap(String.class, String.class);
        userRequest = UserRequest.builder()
            .name(userData.get("name"))
            .username(userData.get("username"))
            .email(userData.get("email"))
            .build();
        
        Allure.addAttachment("User Request Payload", 
            JsonUtils.toJson(userRequest));
    }
    
    @Given("I have an invalid user payload:")
    @Step("Prepare invalid user payload")
    public void i_have_invalid_user_payload(DataTable dataTable) {
        Map<String, String> userData = dataTable.asMap(String.class, String.class);
        userRequest = new UserRequest();
        userRequest.setName(userData.get("name")); // Empty name
        userRequest.setUsername(userData.get("username"));
        userRequest.setEmail(userData.get("email")); // Invalid email
        
        Allure.addAttachment("Invalid User Request Payload", 
            JsonUtils.toJson(userRequest));
    }
    
    // Assertion Steps
    @Then("the response status code should be {int}")
    @Step("Verify response status code: {0}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
        Assertions.assertEquals(expectedStatusCode, response.getStatusCode(),
            "Unexpected status code. Response: " + response.getBody().asString());
    }
    
    @Then("the response should contain user details")
    @Step("Verify response contains user details")
    public void the_response_should_contain_user_details() {
        UserResponse userResponse = response.as(UserResponse.class);
        Assertions.assertNotNull(userResponse.getId(), "User ID should not be null");
        Assertions.assertNotNull(userResponse.getName(), "User name should not be null");
        Assertions.assertNotNull(userResponse.getEmail(), "User email should not be null");
    }
    
    @Then("the response should contain the created user ID")
    @Step("Verify response contains created user ID")
    public void the_response_should_contain_created_user_id() {
        UserResponse userResponse = response.as(UserResponse.class);
        Assertions.assertNotNull(userResponse.getId(), "Created user ID should not be null");
        Assertions.assertTrue(userResponse.getId() > 0, "User ID should be positive");
    }
    
    @Then("the response should match the user schema")
    @Step("Validate response against user schema")
    public void the_response_should_match_user_schema() {
        JsonSchemaValidator.validateResponse(response, "schemas/user-schema.json");
    }
    
    @Then("the response should contain validation errors")
    @Step("Verify response contains validation errors")
    public void the_response_should_contain_validation_errors() {
        ErrorResponse errorResponse = response.as(ErrorResponse.class);
        Assertions.assertNotNull(errorResponse.getError(), "Error message should be present");
        Assertions.assertFalse(errorResponse.getDetails().isEmpty(), 
            "Validation details should be present");
    }
    
    @Then("the response should contain {string} message")
    @Step("Verify response contains message: {0}")
    public void the_response_should_contain_message(String expectedMessage) {
        String responseBody = response.getBody().asString();
        Assertions.assertTrue(responseBody.contains(expectedMessage),
            "Response should contain: " + expectedMessage);
    }
    
    @Then("the response time should be less than {int}ms")
    @Step("Verify response time is less than {0}ms")
    public void the_response_time_should_be_less_than(int maxResponseTime) {
        long responseTime = response.getTime();
        Assertions.assertTrue(responseTime < maxResponseTime,
            "Response time " + responseTime + "ms exceeds maximum " + maxResponseTime + "ms");
        
        Allure.addAttachment("Response Time", responseTime + "ms");
    }
    
    // Utility Methods
    private void addRequestResponseToReport(String method, String endpoint, 
                                          Object requestBody, Response response) {
        // Add request details
        Allure.addAttachment("HTTP Method", method);
        Allure.addAttachment("Endpoint", endpoint);
        
        if (requestBody != null) {
            Allure.addAttachment("Request Body", "application/json",
                JsonUtils.toJson(requestBody), "json");
        }
        
        // Add response details
        Allure.addAttachment("Response Status", String.valueOf(response.getStatusCode()));
        Allure.addAttachment("Response Headers", response.getHeaders().toString());
        Allure.addAttachment("Response Body", "application/json",
            response.getBody().asString(), "json");
        Allure.addAttachment("Response Time", response.getTime() + "ms");
    }
}
```

## 🔧 Advanced API Testing Features

### 1. JSON Schema Validation

```java
package com.framework.utils;

import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchemaValidator {
    
    public static void validateResponse(Response response, String schemaPath) {
        response.then().assertThat()
            .body(matchesJsonSchemaInClasspath(schemaPath));
    }
    
    public static void validateJsonString(String jsonString, String schemaPath) {
        // Custom validation logic
    }
}
```

**Example Schema File (`src/test/resources/schemas/user-schema.json`):**
```json
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "type": "object",
  "properties": {
    "id": {
      "type": "integer",
      "minimum": 1
    },
    "name": {
      "type": "string",
      "minLength": 1
    },
    "username": {
      "type": "string",
      "minLength": 1
    },
    "email": {
      "type": "string",
      "format": "email"
    },
    "phone": {
      "type": "string"
    },
    "website": {
      "type": "string",
      "format": "uri"
    }
  },
  "required": ["id", "name", "username", "email"],
  "additionalProperties": true
}
```

### 2. Authentication & Authorization

```java
package com.framework.api.auth;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class AuthenticationManager {
    private String accessToken;
    private String refreshToken;
    
    public String login(String username, String password) {
        Response response = given()
            .header("Content-Type", "application/json")
            .body(new LoginRequest(username, password))
            .when()
            .post("/auth/login");
            
        LoginResponse loginResponse = response.as(LoginResponse.class);
        this.accessToken = loginResponse.getAccessToken();
        this.refreshToken = loginResponse.getRefreshToken();
        
        return accessToken;
    }
    
    public RequestSpecification getAuthenticatedRequest() {
        return given()
            .header("Authorization", "Bearer " + accessToken);
    }
    
    public void refreshTokenIfNeeded() {
        // Check token expiry and refresh if needed
        if (isTokenExpired()) {
            refreshAccessToken();
        }
    }
    
    private void refreshAccessToken() {
        Response response = given()
            .header("Content-Type", "application/json")
            .body(new RefreshTokenRequest(refreshToken))
            .when()
            .post("/auth/refresh");
            
        LoginResponse loginResponse = response.as(LoginResponse.class);
        this.accessToken = loginResponse.getAccessToken();
    }
    
    private boolean isTokenExpired() {
        // JWT token expiry check logic
        return false; // Simplified
    }
}
```

### 3. Data-Driven Testing

```java
// Step definition for data-driven tests
@Test
@ParameterizedTest
@CsvSource({
    "1, Leanne Graham, Sincere@april.biz",
    "2, Ervin Howell, Shanna@melissa.tv",
    "3, Clementine Bauch, Nathan@yesenia.net"
})
public void verify_multiple_users(int userId, String expectedName, String expectedEmail) {
    Response response = userClient.getUser(userId);
    
    Assertions.assertEquals(200, response.getStatusCode());
    
    UserResponse user = response.as(UserResponse.class);
    Assertions.assertEquals(expectedName, user.getName());
    Assertions.assertEquals(expectedEmail, user.getEmail());
}
```

### 4. Database Validation

```java
package com.framework.database;

import java.sql.*;

public class DatabaseValidator {
    private String dbUrl;
    private String username;
    private String password;
    
    public DatabaseValidator() {
        this.dbUrl = TestConfiguration.getInstance().getDbUrl();
        this.username = TestConfiguration.getInstance().getDbUsername();
        this.password = TestConfiguration.getInstance().getDbPassword();
    }
    
    public boolean verifyUserExists(int userId) {
        String query = "SELECT COUNT(*) FROM users WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database validation failed", e);
        }
        
        return false;
    }
    
    public void cleanupTestData(int userId) {
        String query = "DELETE FROM users WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cleanup failed", e);
        }
    }
}
```

## 📊 Performance Testing

### 1. Response Time Validation

```java
@Then("the response time should be less than {int} milliseconds")
public void verify_response_time(int maxResponseTime) {
    long actualResponseTime = response.getTime();
    
    Assertions.assertTrue(actualResponseTime < maxResponseTime,
        String.format("Response time %dms exceeded maximum %dms", 
            actualResponseTime, maxResponseTime));
    
    // Add to Allure report
    Allure.addAttachment("Performance Metrics", 
        String.format("Response Time: %dms\nThreshold: %dms", 
            actualResponseTime, maxResponseTime));
}
```

### 2. Load Testing Integration

```java
package com.framework.performance;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LoadTestExecutor {
    
    public void executeLoadTest(int numberOfThreads, int requestsPerThread, 
                               Runnable testAction) {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failureCount = new AtomicInteger(0);
        
        for (int i = 0; i < numberOfThreads; i++) {
            executor.submit(() -> {
                for (int j = 0; j < requestsPerThread; j++) {
                    try {
                        testAction.run();
                        successCount.incrementAndGet();
                    } catch (Exception e) {
                        failureCount.incrementAndGet();
                    }
                }
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Report results
        Allure.addAttachment("Load Test Results",
            String.format("Threads: %d\nRequests per thread: %d\nSuccess: %d\nFailures: %d",
                numberOfThreads, requestsPerThread, successCount.get(), failureCount.get()));
    }
}
```

## 🔍 Debugging and Troubleshooting

### 1. Request/Response Logging

```java
// Enable detailed REST Assured logging
public class ApiTestBase {
    
    @BeforeEach
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        
        // Custom request filter for logging
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}
```

### 2. Custom Assertions

```java
package com.framework.assertions;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class ApiAssertions {
    
    public static void assertSuccessResponse(Response response) {
        Assertions.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 300,
            "Expected success status code but got: " + response.getStatusCode());
    }
    
    public static void assertErrorResponse(Response response, int expectedStatusCode) {
        Assertions.assertEquals(expectedStatusCode, response.getStatusCode());
        
        // Verify error response structure
        String responseBody = response.getBody().asString();
        Assertions.assertTrue(responseBody.contains("error") || responseBody.contains("message"),
            "Error response should contain error details");
    }
    
    public static void assertResponseContains(Response response, String expectedContent) {
        String responseBody = response.getBody().asString();
        Assertions.assertTrue(responseBody.contains(expectedContent),
            "Response should contain: " + expectedContent);
    }
    
    public static void assertJsonPath(Response response, String jsonPath, Object expectedValue) {
        Object actualValue = response.jsonPath().get(jsonPath);
        Assertions.assertEquals(expectedValue, actualValue,
            "JSON path " + jsonPath + " should contain: " + expectedValue);
    }
}
```

## 📋 Best Practices

### 1. Test Organization
- ✅ Group related API tests in the same feature file
- ✅ Use meaningful scenario names that describe the business logic
- ✅ Implement proper test data setup and cleanup
- ✅ Use tags for categorizing tests (@api, @smoke, @regression, @crud)

### 2. API Client Design
- ✅ Create separate client classes for different API modules
- ✅ Implement proper error handling and logging
- ✅ Use builder pattern for complex request objects
- ✅ Handle authentication centrally

### 3. Response Validation
- ✅ Validate both status codes and response bodies
- ✅ Use JSON schema validation for response structure
- ✅ Implement custom assertions for domain-specific validations
- ✅ Check response times for performance requirements

### 4. Test Data Management
- ✅ Use external test data files for complex scenarios
- ✅ Implement proper test data cleanup
- ✅ Use random/unique data where appropriate
- ✅ Validate test data in both API responses and database

### 5. Reporting and Debugging
- ✅ Add detailed information to Allure reports
- ✅ Include request/response details in failure reports
- ✅ Log performance metrics
- ✅ Use appropriate log levels for different environments

---
*Refer to the main README.md for setup instructions and general framework information.*
