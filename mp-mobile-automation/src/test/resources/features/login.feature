Feature: Login

  Background:
    Given I am on product page
    When I click user icon

  Scenario Outline: I have to be able to login
    Given I am on login page
    When I input "<email>" and "<password>"
    And click login button
    Then validate the "<message>" after login
    Examples:
      | email                | password  | message           |
      |                      |           | required email    |
      |                      | pass123*  | required email    |
      | email@gmail.com      |           | required password |
      | wrongemail@gmail.com | pass123*  | not existed       |
      | email@gmail.com      | wrongpass | not existed       |
      | email@gmail.com      | pass123*  | success           |