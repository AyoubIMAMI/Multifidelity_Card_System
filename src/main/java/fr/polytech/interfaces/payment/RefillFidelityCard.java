package fr.polytech.interfaces.payment;

import fr.polytech.pojo.Customer;

public interface RefillFidelityCard {
    void refill(Customer customer, float amount);
}
