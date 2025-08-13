Feature: User API

  Scenario: Get user details
    When I get user with id 1
    Then the API response status is 200
    And the API response contains "Leanne Graham"

  Scenario: Create a new user
    When I create a new user with name "Jane" username "jane_doe" and email "jane@example.com"
    Then the API response status is 201