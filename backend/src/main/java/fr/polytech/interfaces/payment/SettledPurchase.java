package fr.polytech.interfaces.payment;

import fr.polytech.entities.Payment;
import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;

public interface SettledPurchase {
    Payment validatePurchase(Payment payment) throws PaymentAlreadyExistsException;
    void winPoint(Payment payment);
}
