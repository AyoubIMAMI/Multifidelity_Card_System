Feature: VFP
  Background:
    Given an advantage parking
    And a user with VFP account


  Scenario: VFP wants a free ticket
    When a user set his plate
    And we try to use the parking advantage
    Then we use it
    And the date is set in the database

    Scenario: VFP try to use a Advantage that doesn't exist
      When a user set his plate
      And he tries to use a not valid AdvantageID
      Then it fails