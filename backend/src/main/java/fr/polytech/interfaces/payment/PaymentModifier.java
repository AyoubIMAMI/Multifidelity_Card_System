package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;
import fr.polytech.entities.Payment;

public interface PaymentModifier {
    void savePayment(Payment payment) throws PaymentAlreadyExistsException;
}
