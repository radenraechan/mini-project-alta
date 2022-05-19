Feature: Logout
  As a user
  I want to logout
  So that I can get out from the system

#  Background:
#    Given I am on login page
#    When I input valid email and password
#    And click login button

  Scenario: I have to be able to logout
    Given I have logged in
    When I click user icon
    And click logout button
    Then I success logout