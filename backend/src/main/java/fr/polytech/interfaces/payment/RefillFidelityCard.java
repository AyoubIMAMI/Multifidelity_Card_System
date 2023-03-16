package fr.polytech.interfaces.payment;

import fr.polytech.controllers.dto.PaymentDTO;
import fr.polytech.entities.Customer;
import fr.polytech.exceptions.payment.NegativeAmountException;
import fr.polytech.exceptions.payment.PaymentException;

import java.util.Date;

public interface RefillFidelityCard {
    Date refill(Customer customer, PaymentDTO transaction) throws NegativeAmountException, PaymentException;
}
