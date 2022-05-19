Feature: Categories
  As an admin
  I want to modify categories
  So that I can access categories feature

  Scenario: GET - As an admin I have to be able to get all categories
    Given I set an endpoint for GET all categories
    When I request GET detail category
    Then I validate the status code for GET all categories is 200
    And validate the data details

  Scenario Outline: POST - As an admin I have to be able to create new category
    Given I set an endpoint for POST new category
    When I request POST detail category with input name is "<categoryName>"
    Then I validate the status code for POST new category is <statusCode>
    And validate the data details and "<message>" message after create new category
    Examples:
      | categoryName   | statusCode | message |
      | null           | 500        | error   |
      | Perfume        | 200        | success |