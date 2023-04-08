package fr.polytech.interfaces.payment;

import fr.polytech.entities.Customer;
import fr.polytech.exceptions.payment.NegativeAmountException;
import fr.polytech.exceptions.payment.PaymentInBankException;

import java.util.Date;

public interface RefillFidelityCard {

    /**
     * Refill the fidelity card
     * @param customer involved
     * @param creditCard customer credit card
     * @param amount to add
     * @return the Date of the transaction
     * @throws NegativeAmountException threw if the amount is negative
     * @throws PaymentInBankException threw if Payment in Bank has been refused
     */
    Date refill(Customer customer, String creditCard, double amount ) throws NegativeAmountException, PaymentInBankException;
}
