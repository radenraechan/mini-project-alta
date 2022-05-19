Feature: Buy Product

  Scenario Outline: I have to be able to buy some product
    Given I am on product page
    When I click <quantity> buy button on <id> product
    Then validate the "<message>" and <quantity> of cart icon
    Examples:
      | quantity | id   | message |
      | 0        | 0    | failed  |
      | 0        | 4    | failed  |
      | 4        | 0    | failed  |
      | 1        | 3    | success |
      | 2        | 2    | success |
      | 3        | 1    | success |