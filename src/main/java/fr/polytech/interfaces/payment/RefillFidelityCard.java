package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.MalformedBankInformationException;
import fr.polytech.pojo.BankTransaction;
import fr.polytech.pojo.Customer;

import java.util.Date;

public interface RefillFidelityCard {
    Date refill(Customer customer, BankTransaction transaction) throws MalformedBankInformationException;
}
