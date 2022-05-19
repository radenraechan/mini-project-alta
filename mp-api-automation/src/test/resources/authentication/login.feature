Feature: Login
  As an admin
  I want to login user
  So that I can access the detail data user

  Scenario Outline: POST - As an admin I have to be able to login user
    Given I set an endpoint for POST login user
    When I request POST login user "<email>" and "<password>"
    Then I validate the status code is <statusCode>
    And validate the data details and "<message>" after login user
    Examples:
      | email           | password  | statusCode | message                      |
      |                 |           | 400        | email is required            |
      |                 | pass123*  | 400        | email is required            |
      | existed         |           | 400        | password is required         |
      | existed         | wrongPass | 400        | email or password is invalid |
      | not existed     | pass123*  | 400        | record not found             |
      | existed         | pass123*  | 200        | success                      |