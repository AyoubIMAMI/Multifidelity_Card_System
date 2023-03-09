package fr.polytech.interfaces.payment;

import fr.polytech.entities.Customer;
import fr.polytech.exceptions.paiment.NegativeAmountException;
import fr.polytech.exceptions.paiment.PaymentException;
import fr.polytech.entities.FidelityAccount;
import fr.polytech.entities.PaymentDTO;

import java.util.Date;

public interface RefillFidelityCard {
    Date refill(Customer customer, PaymentDTO transaction) throws NegativeAmountException, PaymentException;
}
