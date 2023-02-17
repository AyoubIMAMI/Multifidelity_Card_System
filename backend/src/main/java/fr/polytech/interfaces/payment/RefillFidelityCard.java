package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.NegativeAmountException;
import fr.polytech.exceptions.paiment.PaymentException;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.PaymentDTO;

import java.util.Date;

public interface RefillFidelityCard {
    Date refill(Customer customer, PaymentDTO transaction) throws NegativeAmountException, PaymentException;
}
