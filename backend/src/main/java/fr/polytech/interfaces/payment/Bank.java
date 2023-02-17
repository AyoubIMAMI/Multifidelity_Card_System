package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.paiment.PaymentException;
import fr.polytech.pojo.BankTransaction;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.PaymentDTO;

public interface Bank {
    boolean pay(PaymentDTO paymentDTO) throws PaymentException;
}