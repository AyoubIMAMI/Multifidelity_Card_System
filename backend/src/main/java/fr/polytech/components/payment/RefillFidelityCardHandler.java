package fr.polytech.components.payment;

import fr.polytech.controllers.dto.PaymentDTO;
import fr.polytech.entities.Customer;
import fr.polytech.exceptions.payment.NegativeAmountException;
import fr.polytech.exceptions.payment.PaymentException;
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

    public Date refill(Customer customer, PaymentDTO transaction) throws NegativeAmountException, PaymentException {
        if(transaction.getAmount() <= 0)
            throw new NegativeAmountException();

        if(!bank.pay(transaction))
            throw new PaymentException();

        Date bankTransactionDate = new Date();

        balanceModifier.rechargeBalance(customer, transaction);

        return bankTransactionDate;
    }
}
