package fr.polytech.components.payment;

import fr.polytech.exceptions.MalformedBankInformationException;
import fr.polytech.interfaces.payment.RefillFidelityCard;
import fr.polytech.pojo.BankTransaction;
import fr.polytech.pojo.Customer;

public class RefillFidelityCardHandler implements RefillFidelityCard {


    @Override
    public void refill(Customer customer, BankTransaction transaction, float amount) throws MalformedBankInformationException {

    }
}
