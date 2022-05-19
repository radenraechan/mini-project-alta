Feature: Buy Product
  As a user
  I want to buy product
  So that I can order product

  Scenario Outline: I have to be able to buy product
    Given I am on product page
    When I click "<id_product>" buy button
    And click cart button
    Then validate "<product_name>" on cart page
    Examples:
      | id_product | product_name         |
      | 1          | Tensi Darah Elektrik |
      | 10         | Kamera               |
      | 41         | Stetoskop            |

  Scenario: Null Order - I can't buy product by null order
    Given I am on product page
    When click cart button
    Then I will recive card message that "Order is empty!"