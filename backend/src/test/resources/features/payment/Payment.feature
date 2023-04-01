Feature: Payment
Background:
  Given a user
  And a store

Scenario: A user wants to pay with his fidelityCard
  When he want to buy items
  And he has enough cash
  And he pay
  Then the payment works