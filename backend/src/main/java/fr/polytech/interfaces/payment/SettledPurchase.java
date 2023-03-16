package fr.polytech.interfaces.payment;

import fr.polytech.entities.Store;
import fr.polytech.exceptions.PurchaseFailedException;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;

public interface SettledPurchase {
    void validatePurchase(Customer customer, Payment payment, Store store) throws PurchaseFailedException, PaymentAlreadyExistsException;
    void winPoint(Customer customer, Payment payment, Store store);
}
