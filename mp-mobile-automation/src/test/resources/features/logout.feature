Feature: Logout

  Background:
    Given I am on product page
    When I click user icon

  Scenario: I have to be able to logout
    Given I have logged in
    When I click logout button
    Then validate success logout