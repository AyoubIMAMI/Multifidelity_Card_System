package fr.polytech.interfaces.fidelity;

import fr.polytech.entities.Customer;

public interface PointModifier {
    //points computed from the price
    Customer incrementPoints(Customer customer, float price);
    Customer decrementPoints(Customer customer, int points);
}
