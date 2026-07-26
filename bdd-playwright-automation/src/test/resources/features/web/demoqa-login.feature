Feature: DemoQA Book Store Login
As a QA engineer validating the DemoQA Book Store login form, I want invalid credentials to be rejected with a clear error message so that the authentication flow fails safely.

@demoqa @web @negative
Scenario: Login fails with invalid credentials
Given User navigates to the DemoQA login page
When User enters DemoQA username "invalidUser123" and password "InvalidPass123!"
And User clicks the DemoQA login button
Then User should see the DemoQA error message "Invalid username or password!"
