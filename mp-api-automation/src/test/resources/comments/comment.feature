Feature: Comments
  As an admin
  I want to modify comments product
  So that I can access comments feature

  Scenario Outline: GET - As an admin I have to be able to get comments by id products
    Given I set an endpoint for GET comments
    When I request GET detail comments product with input "<id>"
    Then I validate the status code for GET comments product is <statusCode>
    And validate the data details and "<message>" for get comments
    Examples:
      | id    | statusCode | message     |
      |       | 404        | not found   |
      | str   | 400        | error       |
      | 99999 | 200        | not existed |
      | 1000  | 200        | success     |

  Scenario Outline: POST - As an admin I have to be able to write comments to products
    Given I set an endpoint for POST comments
    When I request POST detail comments product with "<status>", "<statusToken>", input "<id>" and "<content>"
    Then I validate the status code for POST comments product is <statusCode>
    And validate the data details and "<message>" for post comments
    Examples:
      | status         | statusToken | id    | content | statusCode | message      |
      | not authorized |             |       |         | 404        | not found    |
      | authorized     | null        |       |         | 404        | not found    |
      | authorized     | invalid     |       |         | 404        | not found    |
      | authorized     | valid       |       |         | 404        | not found    |
      | authorized     | valid       |       | Mantap  | 404        | not found    |
      | authorized     | valid       | 1000  |         | 500        | error        |
      | authorized     | valid       | str   | Mantap  | 400        | error        |
#      | authorized     | valid       | 9999  | Mantap  | 200        | error        |
      | authorized     | valid       | 1000  | Mantap  | 200        | success      |