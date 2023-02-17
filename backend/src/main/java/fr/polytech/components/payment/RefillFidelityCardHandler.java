package fr.polytech.components.payment;

import fr.polytech.exceptions.MalformedBankInformationException;
import fr.polytech.exceptions.paiment.PaymentException;
import fr.polytech.interfaces.payment.Bank;
import fr.polytech.interfaces.payment.RefillFidelityCard;
import fr.polytech.pojo.BankTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RefillFidelityCardHandler implements RefillFidelityCard {

    @Autowired
    private Bank bank;

    @Override
    public Date refill(BankTransaction transaction) throws MalformedBankInformationException, PaymentException {
        if(transaction.getAmount() <= 0)
            throw new MalformedBankInformationException();

        if(bank.pay(transaction))
            return new Date();
        throw new PaymentException();
    }
}
