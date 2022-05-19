Feature: Transactions
  As an admin
  I want to modify transaction
  So that I can access transaction feature

  Scenario Outline: GET - As an admin I have to be able to get all transactions
    Given I set an endpoint for GET all transactions
    When I request GET detail transaction with "<status>" and "<statusToken>"
    Then I validate the status code for GET all transactions is <statusCode>
    And validate the "<message>" data details of transaction
    Examples:
      | status         | statusToken   | statusCode | message       |
      | not authorized |               | 401        | unauthorized  |
      | authorized     | null          | 401        | unauthorized  |
      | authorized     | invalid token | 401        | unauthorized  |
      | authorized     | valid token   | 200        | success       |

  Scenario Outline: POST - As an admin I have to be able to create new transaction
    Given I set an endpoint for POST new transaction
    When I request POST detail transaction with "<status>", "<statusToken>", input "<productId>" and "<quantity>"
    Then I validate the status code for POST new transaction is <statusCode>
    And validate the data details and "<message>" after create new transaction
    Examples:
      | status         | statusToken | productId | quantity | statusCode | message      |
      | not authorized |             |           |          | 401        | unauthorized |
      | authorized     | null        |           |          | 401        | unauthorized |
      | authorized     | invalid     |           |          | 401        | unauthorized |
      | authorized     | valid       |           |          | 200        | not found    |
      | authorized     | valid       |           | 100      | 200        | not found    |
      | authorized     | valid       | 999       |          | 200        | not found    |
      | authorized     | valid       | 1         | 100      | 200        | not found    |
      | authorized     | valid       | 2000      | 100      | 200        | success      |