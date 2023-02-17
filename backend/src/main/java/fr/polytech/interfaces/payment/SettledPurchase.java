package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;
import fr.polytech.exceptions.PurchaseFailedException;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.Payment;
import fr.polytech.pojo.structure.Store;

public interface SettledPurchase {
    void validatePurchase(Customer customer, Payment payment, Store store) throws PurchaseFailedException, PaymentAlreadyExistsException;
    void winPoint(Customer customer, Payment payment, Store store);
}
