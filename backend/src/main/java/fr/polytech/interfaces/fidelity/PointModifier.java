package fr.polytech.interfaces.fidelity;

import fr.polytech.entities.Customer;

public interface PointModifier {
    //points computed from the price
    void incrementPoints(Customer customer, float price);
    void decrementPoints(Customer customer, int points);
}
