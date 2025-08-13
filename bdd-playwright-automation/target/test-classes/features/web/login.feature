Feature: Login

  Scenario: Successful login
    Given User navigates to login page
    When User enters username "testuser" and password "password"
    And User clicks the login button
    Then Login is successful