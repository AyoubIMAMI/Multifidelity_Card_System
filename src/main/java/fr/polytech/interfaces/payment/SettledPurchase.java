package fr.polytech.interfaces.payment;

import fr.polytech.pojo.Customer;
import fr.polytech.pojo.Payment;
import fr.polytech.pojo.structure.Store;

public interface SettledPurchase {
    void validatePurchase(Customer customer, Payment payment, Store store);
}
