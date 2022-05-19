Feature: Ratings
  As an admin
  I want to modify ratings
  So that I can access ratings feature

  Scenario Outline: GET - As an admin I have to be able to get ratings by id products
    Given I set an endpoint for GET ratings
    When I request GET detail ratings product with input "<id>"
    Then I validate the status code for GET ratings product is <statusCode>
    And validate the data details and "<message>" for get ratings
    Examples:
      | id    | statusCode | message     |
      |       | 404        | not found   |
      | str   | 400        | error       |
      | 99999 | 200        | not existed |
      | 2000  | 200        | success     |

  Scenario Outline: POST - As an admin I have to be able to give ratings to products
    Given I set an endpoint for POST ratings
    When I request POST detail ratings product with "<status>", "<statusToken>", input "<id>" and "<count>"
    Then I validate the status code for POST ratings product is <statusCode>
    And validate the data details and "<message>" for post ratings
    Examples:
      | status         | statusToken | id    | count | statusCode | message      |
      | not authorized |             |       |       | 404        | not found    |
      | authorized     | null        |       |       | 404        | not found    |
      | authorized     | invalid     |       |       | 404        | not found    |
      | authorized     | valid       |       |       | 404        | not found    |
      | authorized     | valid       |       | 4     | 404        | not found    |
      | authorized     | valid       | 9999  | 4     | 500        | error        |
      | authorized     | valid       | 1050  | 6     | 500        | error        |
      | authorized     | valid       | 2000  |       | 200        | success      |
      | authorized     | valid       | 2000  | 5     | 200        | success      |