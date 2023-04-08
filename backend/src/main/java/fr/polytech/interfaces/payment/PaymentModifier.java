package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;
import fr.polytech.entities.Payment;

public interface PaymentModifier {

    /**
     * Save a Payment
     * @param payment to ave
     * @return the saved Payment
     * @throws PaymentAlreadyExistsException threw if the Payment already exist
     */
    Payment savePayment(Payment payment) throws PaymentAlreadyExistsException;
}
