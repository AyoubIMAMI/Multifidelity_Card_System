package fr.polytech.components.payment;

import fr.polytech.exceptions.NegativeAmountException;
import fr.polytech.exceptions.paiment.PaymentException;
import fr.polytech.interfaces.payment.Bank;
import fr.polytech.interfaces.payment.RefillFidelityCard;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class RefillFidelityCardHandler implements RefillFidelityCard {

    @Autowired
    private Bank bank;

    @Override
    public Date refill(Customer customer, PaymentDTO transaction) throws NegativeAmountException, PaymentException {
        if(transaction.getAmount() <= 0)
            throw new NegativeAmountException();

        if(bank.pay(transaction))
            return new Date();
        throw new PaymentException();
    }
}
