Feature: Login functionality

  Scenario: Valid login with standard user
    Given I am on the login page
    When I login with username "standard_user" and password "secret_sauce"
    Then I should be redirected to the inventory page

  Scenario: Invalid login with wrong credentials
    Given I am on the login page
    When I login with username "invalid_user" and password "wrong_pass"
    Then I should see a login error message
