package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.payment.PaymentInBankException;

public interface Bank {

    /**
     * Refill the balance from the Bank
     * @param creditCard used to refill the customer balance
     * @param amount to refill
     * @return true if the Bank accept the transaction/payment
     * @throws PaymentInBankException threw if the transaction/payment has been refused
     */
    boolean refill(String creditCard,double amount) throws PaymentInBankException;
}
