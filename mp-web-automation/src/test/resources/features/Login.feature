Feature: Login
  As a user
  I want to login
  So that I can access cart page

#  Background:
#    Given I am on product page
#    And I click user icon

  Scenario Outline: I have to be able to login
    Given I am on login page
    When I input "<email>" email
    And input "<password>" password on login page
    And click login button
    Then I get the "<result>" and "<text>" after login
    Examples:
      | email              | password | result             | text                 |
      |                    |          | icon warning       | email is required    |
      |                    | pass123* | icon warning       | email is required    |
      | email123@gmail.com |          | icon warning       | password is required |
      | email123@gmail.com | pass123# | icon warning       | record not found     |
      | email123@gmail.com | pass123* | direct to homepage | |