package fr.polytech.interfaces.payment;

import fr.polytech.entities.Store;
import fr.polytech.exceptions.PurchaseFailedException;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;

public interface SettledPurchase {
    void validatePurchase(Payment payment) throws PurchaseFailedException, PaymentAlreadyExistsException;
    void winPoint(Payment payment);
}
