package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.payment.PaymentInBankException;
import fr.polytech.connectors.externaldto.BankTransactionDTO;

public interface Bank {
    boolean refill(BankTransactionDTO bankTransactionDTO) throws PaymentInBankException;
}
