package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.payment.PaymentException;
import fr.polytech.controllers.dto.PaymentDTO;

public interface Bank {
    boolean pay(PaymentDTO paymentDTO) throws PaymentException;
}
