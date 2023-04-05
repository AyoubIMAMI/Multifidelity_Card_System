Feature: Payment
Background:
  Given a user
  And a store

Scenario: A user wants to pay with his fidelityCard and his points
  When he want to buy an item names "Coffee", it cost 5, in quantity 8
  When he want to buy an discount names "croissant", it cost 8 and 25 points, in quantity 2
  And he has enough cash
  And he pay
  Then the payment works

Scenario: A user wants to pay with his fidelityCard
  When he want to buy an item names "Coffee", it cost 5, in quantity 12
  And he has enough cash
  And he pay
  Then the payment works

Scenario: A user wants to pay with his points
  When he want to buy an discount names "croissant", it cost 8 and 25 points, in quantity 2
  And he has enough cash
  And he pay
  Then the payment works


Scenario: A user wants to pay with his fidelityCard but he hasn't enought money
  When he want to buy an item names "Coffee", it cost 5, in quantity 12
  And he has not enough cash
  And he pay
  Then the payment doesn't work