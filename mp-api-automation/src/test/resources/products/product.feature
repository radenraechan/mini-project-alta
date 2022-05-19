Feature: Products
  As an admin
  I want to modify products
  So that I can access products feature

  Scenario: GET - As an admin I have to be able to get all products
    Given I set an endpoint for GET all products
    When I request GET detail product
    Then I validate the status code for GET all products is 200
    And validate the data details for get product

  Scenario Outline: GET - As an admin I have to be able to get product by id
    Given I set an endpoint for GET product by id is "<id>"
    When I request GET detail product with input "<id>"
    Then I validate the status code for GET product by id is <statusCode>
    And validate the data details and "<message>" details
    Examples:
      | id     | statusCode | message          |
      | -2     | 500        | error            |
      | 1      | 404        | record not found |
      |        | 404        | record not found |
      | 2000   | 200        | success          |

  Scenario Outline: POST - As an admin I have to be able to create new product
    Given I set an endpoint for POST new product
    When I request POST detail product with input "<name>" and "<price>"
    Then I validate the status code for POST new product is <statusCode>
    And validate the data details and "<message>" after create new product
    Examples:
      | name              | price  | statusCode | message |
      |                   |        | 500        | error   |
      |                   | 1000   | 500        | error   |
      | Bawang Merah      |        | 200        | success |
      | Sweater Crewneck  | 199000 | 200        | success |