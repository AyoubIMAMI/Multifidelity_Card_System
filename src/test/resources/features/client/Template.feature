//Feature: Subscribe to the fidelity program

//  Background:
//    Given a user

//  Scenario: He subscribe to the program
//    Given a user with no fidelityAccount
//    When  he subscribe to the fidelity program as "Benoit" with "Benoit@gmail.cube" mail and this password "JeSuisB0"
//    Then he take advantage of our loyalty program

//  Scenario: He subscribe to the program but the account already exist
//    Given the user as subscribe to a fidelity account
//    When  he subscribe to the fidelity program as "Benoit" with "Benoit@gmail.cube" mail and this password "JeSuisB0"
//    Then he can't subscribe an other time to the fidelity program