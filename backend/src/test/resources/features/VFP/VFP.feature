Feature: VFP
  Background:
    Given an advantage parking
    And a user with VFP account


  Scenario: VFP wants a free ticket
    When a user set his plate
    When we try to use the parking advantage
    Then we use it