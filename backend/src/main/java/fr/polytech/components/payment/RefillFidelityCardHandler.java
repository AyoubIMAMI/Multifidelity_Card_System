package fr.polytech.components.payment;

import fr.polytech.exceptions.NegativeAmountException;
import fr.polytech.exceptions.payment.PaymentException;
import fr.polytech.interfaces.payment.BalanceModifier;
import fr.polytech.interfaces.payment.Bank;
import fr.polytech.interfaces.payment.RefillFidelityCard;
import fr.polytech.pojo.FidelityAccount;
import fr.polytech.pojo.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RefillFidelityCardHandler implements RefillFidelityCard {

    private Bank bank;
    private BalanceModifier balanceModifier;

    @Autowired
    public void RefillFidelityCard(Bank bank, BalanceModifier balanceModifier){
        this.bank = bank;
        this.balanceModifier = balanceModifier;
    }

    @Override
    public Date refill(FidelityAccount fidelityAccount, PaymentDTO transaction) throws NegativeAmountException, PaymentException {
        if(transaction.getAmount() <= 0)
            throw new NegativeAmountException();

        if(!bank.pay(transaction))
            throw new PaymentException();

        return new Date();
    }
}
