package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.paiment.PaymentException;
import fr.polytech.pojo.BankTransaction;
import fr.polytech.pojo.Customer;

public interface Bank {
    boolean pay(Customer customer, BankTransaction transaction) throws PaymentException;
}