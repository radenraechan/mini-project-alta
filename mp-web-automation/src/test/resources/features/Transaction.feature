Feature: Transaction
  As a user
  I want to do transaction
  So that I can see list of transaction

  Background:
    Given I am on product page
    When I click buy button of product
    And click cart button

  Scenario: I have to be able to get detail transaction
    And click add button
    And click pay button
    And I have logged in
    Then I will direct to transaction page
#    And I can see detail of my transaction
    And validate on transaction page