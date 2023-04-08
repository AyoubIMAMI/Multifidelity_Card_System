package fr.polytech.components.payment;

import fr.polytech.entities.Customer;
import fr.polytech.exceptions.payment.NegativeAmountException;
import fr.polytech.exceptions.payment.PaymentInBankException;
import fr.polytech.interfaces.payment.BalanceModifier;
import fr.polytech.interfaces.payment.Bank;
import fr.polytech.interfaces.payment.RefillFidelityCard;
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

    public Date refill(Customer customer, String creditCard, double amount) throws NegativeAmountException, PaymentInBankException {

        if(amount <= 0)
            throw new NegativeAmountException(amount);

        if(!bank.refill(creditCard,amount))
            throw new PaymentInBankException(amount);

        Date bankTransactionDate = new Date();

        balanceModifier.rechargeBalance(customer, creditCard,amount);

        return bankTransactionDate;
    }
}
