Feature: User is checking overall statistics
  Background:
    Given a store named "Store Cool" with Siret "10-11-01" and password "password"
    And a store named "Poly Store" with Siret "16-2214-41" and password "password"
    And a customer "Estoult" with mail "kilian.bonnet@estoult.fr" and password "password"
    And a customer "Glio" with mail "glio@gmail.com" and password "password"

  Scenario: Glio buy a "Salade Wifi" with 45 points.
    When the customer "Glio" purchase "Salade wifi" with 45 points in the store "Store Cool" in 2023
    And the customer "Glio" purchase "Ravioli bad de gamme" with 3 euros in the store "Store Cool" in 2018
    And the customer "Glio" purchase "Steak végétarien" with 7 euros in the store "Poly Store" in 2002
    Then the total point used was 45 since the beginning

  Scenario: Same purchase, but different Customer
    When the customer "Glio" purchase "Salade wifi" with 45 points in the store "Store Cool" in 2023
    And the customer "Estoult" purchase "Pomme de terre Bluetooth" with 27 euros in the store "Poly Store" in 1997
    And the customer "Glio" purchase "Pomme de terre Bluetooth" with 27 euros in the store "Poly Store" in 2021
    Then the total cost of the operation is up to 4.50 euros since the beginning

  Scenario: Get stat of 2020+ Payment (points)
    When the customer "Glio" purchase "Salade wifi" with 45 points in the store "Store Cool" in 2023
    And the customer "Estoult" purchase "Salade wifi" with 45 points in the store "Store Cool" in 2018
    And the customer "Glio" purchase "Steak végétarien" with 7 euros in the store "Poly Store" in 2022
    Then the total point used was 45 since 2020

  Scenario: Get stat of 2018+ Payment (cost)
    When the customer "Glio" purchase "Salade wifi" with 45 points in the store "Store Cool" in 2023
    And the customer "Glio" purchase "Steak végétarien" with 7 euros in the store "Poly Store" in 2022
    And the customer "Estoult" purchase "Salade wifi" with 45 points in the store "Store Cool" in 2015
    Then the total cost of the operation is up to 4.50 euros since the 2018

  Scenario: Bad query date
    When the customer "Glio" purchase "Salade wifi" with 45 points in the store "Store Cool" in 2023
    And the customer "Estoult" purchase "Pomme de terre Bluetooth" with 27 euros in the store "Poly Store" in 1997
    And the customer "Glio" purchase "Pomme de terre Bluetooth" with 27 euros in the store "Poly Store" in 2021
    And the customer "Glio" purchase "Steak végétarien" with 7 euros in the store "Poly Store" in 2022
    And the customer "Estoult" purchase "Salade wifi" with 45 points in the store "Store Cool" in 2015

    Then the user can not query the total cost of the operation from 2026 (IllegalDateException)
