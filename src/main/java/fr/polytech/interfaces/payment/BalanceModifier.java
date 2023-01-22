package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.MalformedBankInformationException;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.pojo.BankTransaction;
import fr.polytech.pojo.FidelityAccount;

public interface BalanceModifier {
    void decreaseBalance(FidelityAccount fidelityAccount, float amount) throws NotEnoughBalanceException;
    void rechargeBalance(FidelityAccount fidelityAccount, BankTransaction bankTransaction, float amount) throws MalformedBankInformationException;
}
