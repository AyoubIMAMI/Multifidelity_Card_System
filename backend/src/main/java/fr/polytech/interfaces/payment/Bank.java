package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.payment.PaymentException;
import fr.polytech.pojo.PaymentDTO;

public interface Bank {
    boolean pay(PaymentDTO paymentDTO) throws PaymentException;
}
