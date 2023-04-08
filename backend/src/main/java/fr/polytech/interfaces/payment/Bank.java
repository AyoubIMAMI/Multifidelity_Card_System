package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.payment.PaymentInBankException;

public interface Bank {
    boolean refill(String creditCard,double amount) throws PaymentInBankException;
}
