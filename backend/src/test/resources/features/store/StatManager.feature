Feature: User is checking overall statistics
  Background:
    Given a store named "Store Cool" with Siret "10-11-01" and password "password"
    And a store named "Poly Store" with Siret "16-2214-41" and password "password"
    And a customer "Estoult" with mail "kilian.bonnet@estoult.fr" and password "password"
    And a customer "Glio" with mail "glio@gmail.com" and password "password"

  Scenario: Glio buy a "Salade Wifi" with 45 points.
    When the customer "Glio" purchase "Salade wifi" with 45 points in the store "Store Cool"
    And the customer "Glio" purchase "Ravioli bad de gamme" with 3 euros in the store "Store Cool"
    And the customer "Glio" purchase "Steak végétarien" with 7 euros in the store "Poly Store"
    Then the total point used was 45 since the beginning

  Scenario: Same purchase, but different Customer
    When the customer "Glio" purchase "Salade wifi" with 45 points in the store "Store Cool"
    And the customer "Estoult" purchase "Pomme de terre Bluetooth" with 27 euros in the store "Poly Store"
    And the customer "Glio" purchase "Pomme de terre Bluetooth" with 27 euros in the store "Poly Store"
    Then the total cost of the operation is up to 4.50 euros since the beginning

