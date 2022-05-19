Feature: Detail Product
  As a user
  I want to get detail product
  So that I can see product's detail

  Scenario Outline: I have to be able to get detail product by id
    Given I am on product page
    When I click "<id_product>" detail button
    Then I will direct to detail product page
    And validate "<product_name>" on detail product page
    Examples:
      | id_product | product_name         |
      | 1          | Tensi Darah Elektrik |
      | 10         | Kamera               |
      | 41         | Stetoskop            |