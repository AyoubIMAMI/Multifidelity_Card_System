Feature: Refill

  Background:
    Given a user
    And a empty fidelity card

  Scenario: A user refill his account
    When the user refill his account with 100 with the credit-card "896983"
    Then the fidelity card contain 100


  Scenario: A user refill his account with a negative amount
    When the user refill his account with -10 with the credit-card "896983"
    Then the system has refuse the payment

  Scenario: A user refill his account with a null amount
    When the user refill his account with -0 with the credit-card "896983"
    Then the system has refuse the payment