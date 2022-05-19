Feature: Register

  Background:
    Given I am on product page
    When I click user icon
    And click register link

  Scenario Outline: I have to be able to register
    Given I am on register page
    When I input "<fullname>", "<email>", and "<password>"
    And click register button
    Then validate the "<message>"
    Examples:
      | fullname | email           | password | message           |
      |          |                 |          | required email    |
      |          | email@gmail.com | pass123* | required fullname |
      | user     |                 | pass123* | required email    |
      | user     | email@gmail.com |          | required password |
      | user     | email@gmail.com | pass123* | existed           |
      | user     | new             | pass123* | success           |