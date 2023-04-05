Feature: Register and login

  Background:
    Given a user named "Benoit" with "Benoit@gmail.cube" mail and this password "JeSuisB0"


  Scenario: A user wants to create an account and login to get his id
    When the user registers
    Then he can login and get his ID with "Benoit@gmail.cube" mail and this password "JeSuisB0"

  Scenario: A user wants to create an account and login to get his id but he connect with the wrong ID
    When the user registers
    Then he can't login and get his ID with "Benoit@gmail.cube" mail and this password "wrongPassword"

  Scenario: A user wants to create an account and login to get his id but he connect with the wrong email
    When the user registers
    Then he can't login and get his ID with "otherEmail@gmail.cube" mail and this password "JeSuisB0"

  Scenario: A user register 2 times to our website with the same email
    When the user registers
    And an other user registers named "Benoit" with "Benoit@gmail.cube" mail and this password "Autre"
    Then he trigger a UserAlreadyExistingException
