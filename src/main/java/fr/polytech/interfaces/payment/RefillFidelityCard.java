package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.MalformedBankInformationException;
import fr.polytech.pojo.BankTransaction;
import fr.polytech.pojo.Customer;

public interface RefillFidelityCard {
    void refill(Customer customer, BankTransaction transaction, float amount) throws MalformedBankInformationException;
}
