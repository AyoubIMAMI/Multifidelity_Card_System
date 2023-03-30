package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.payment.PaymentException;
import fr.polytech.connectors.externaldto.PaymentDTO;

public interface Bank {
    boolean pay(PaymentDTO paymentDTO) throws PaymentException;
}
