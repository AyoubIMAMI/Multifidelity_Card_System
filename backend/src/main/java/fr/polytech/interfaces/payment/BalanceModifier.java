package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.payment.NegativeAmountException;
import fr.polytech.pojo.FidelityAccount;
import fr.polytech.pojo.PaymentDTO;

public interface BalanceModifier {
    void decreaseBalance(FidelityAccount fidelityAccount, double amount) throws NotEnoughBalanceException;
    void rechargeBalance(FidelityAccount fidelityAccount, PaymentDTO transaction) throws NegativeAmountException;
}
