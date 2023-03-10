package fr.polytech.interfaces.payment;

import fr.polytech.controllers.dto.PaymentDTO;
import fr.polytech.entities.Customer;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.payment.NegativeAmountException;

public interface BalanceModifier {
    void decreaseBalance(Customer customer, double amount) throws NotEnoughBalanceException;
    void rechargeBalance(Customer customer, PaymentDTO transaction) throws NegativeAmountException;
}
