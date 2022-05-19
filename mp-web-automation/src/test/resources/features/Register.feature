Feature: Register
  As a user
  I want to register / create a new account
  So that I can login

#  Background:
#    Given I am on product page
#    And I click login button
#    And click link register

  Scenario Outline: I have to be able to create new account
    Given I am on register page
    When I input "<fullName>" fullName
    And input "<email>" email
    And input "<password>" password
    And double click register button
    Then I get the "<result>" and "<message>" after register
    Examples:
      | fullName | email               | password | result       | message              |
      |          |                     |          | icon warning | fullname is required |
      |          | email123@gmail.com  | pass123* | icon warning | fullname is required |
      | name     |                     | pass123* | icon warning | email is required    |
      | name     | email123@gmail.com  |          | icon warning | password is required |
      | name     | same                | pass123* | error        |                      |
      | name     | email               | pass123* | icon warning | email is invalid     |
      | name     | new                 | pass123* | direct to login page |              |