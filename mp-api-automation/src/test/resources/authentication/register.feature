Feature: Register
  As an admin
  I want to create new user
  So that I can access the detail data user

  Scenario Outline: POST - As an admin I have to be able to create new user
    Given I set an endpoint for POST new user
    When I request POST detail user with input "<fullname>", "<email>" and "<password>"
    Then I validate the status code is <statusCode>
    And validate the data details and "<message>" after create user
    Examples:
      | fullname | email           | password  | statusCode | message              |
      |          |                 |           | 400        | email is required    |
      | user1234 | same            |           | 400        | password is required |
      | user1234 |                 | pass123*  | 400        | email is required    |
      |          | same            | pass123*  | 400        | fullname is required |
      | user1234 | same            | pass123*  | 400        | existed              |
      | user1234 | new             | pass123*  | 200        | success              |