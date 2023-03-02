package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.PaymentAlreadyExistsException;
import fr.polytech.exceptions.PurchaseFailedException;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.entities.structure.Store;

public interface SettledPurchase {
    void validatePurchase(Customer customer, Payment payment, Store store) throws PurchaseFailedException, PaymentAlreadyExistsException;
    void winPoint(Customer customer, Payment payment, Store store);
}
