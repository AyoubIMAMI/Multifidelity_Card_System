package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.MalformedBankInformationException;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.pojo.BankTransaction;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.FidelityAccount;

public interface BalanceModifier {
    void decreaseBalance(Customer customer, float amount) throws NotEnoughBalanceException;
    void rechargeBalance(Customer customer, BankTransaction bankTransaction, float amount) throws MalformedBankInformationException;
}
