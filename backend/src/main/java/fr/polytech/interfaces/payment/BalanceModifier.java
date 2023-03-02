package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.paiment.NegativeAmountException;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.entities.FidelityAccount;
import fr.polytech.entities.PaymentDTO;

public interface BalanceModifier {
    void decreaseBalance(FidelityAccount fidelityAccount, double amount) throws NotEnoughBalanceException;
    void rechargeBalance(FidelityAccount fidelityAccount, PaymentDTO transaction) throws NegativeAmountException;
}
