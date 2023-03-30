package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.payment.PaymentInBankException;
import fr.polytech.connectors.externaldto.PaymentDTO;

public interface Bank {
    boolean refill(PaymentDTO paymentDTO) throws PaymentInBankException;
}
