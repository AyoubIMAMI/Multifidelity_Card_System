package fr.polytech.interfaces.payment;

import fr.polytech.entities.Payment;
import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;

public interface SettledPurchase {

    /**
     * Validate a purchase
     * @param payment to validate
     * @return the Payment
     * @throws PaymentAlreadyExistsException threw if the Payment already exist
     */
    Payment validatePurchase(Payment payment) throws PaymentAlreadyExistsException;

    /**
     * Earn points
     * @param payment Payment from which the points are earned
     */
    void winPoint(Payment payment);
}
