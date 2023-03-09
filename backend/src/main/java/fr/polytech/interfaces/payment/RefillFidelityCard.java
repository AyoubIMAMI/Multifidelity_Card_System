package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.payment.NegativeAmountException;
import fr.polytech.exceptions.payment.PaymentException;
import fr.polytech.pojo.FidelityAccount;
import fr.polytech.controllers.dto.PaymentDTO;

import java.util.Date;

public interface RefillFidelityCard {
    Date refill(FidelityAccount customer, PaymentDTO transaction) throws NegativeAmountException, PaymentException;
}
