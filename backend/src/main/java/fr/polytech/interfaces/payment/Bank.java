package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.paiment.PaymentException;
import fr.polytech.entities.PaymentDTO;

public interface Bank {
    boolean pay(PaymentDTO paymentDTO) throws PaymentException;
}