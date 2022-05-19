Feature: Add or Remove Product Quantity
  As a user
  I want to add or remove product quantity
  So that I can order product

  Background:
    Given I am on product page
    When I click buy button of product
    And click cart button

  Scenario: I have to be able to add product
    And I click add button
    Then I can see detail of my order

  Scenario: I have to be able to remove product
    And I click remove button
    Then I will recive card message that "Order is empty!"