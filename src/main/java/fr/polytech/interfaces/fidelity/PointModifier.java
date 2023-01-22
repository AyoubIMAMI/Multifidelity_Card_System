package fr.polytech.interfaces.fidelity;

import fr.polytech.pojo.Customer;

public interface PointModifier {
    void incrementPoints(Customer customer, int price);
    void decrementPoints(Customer customer, int points);
}
