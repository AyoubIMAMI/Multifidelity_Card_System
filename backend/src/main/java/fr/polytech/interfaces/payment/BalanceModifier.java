package fr.polytech.interfaces.payment;

import fr.polytech.entities.Customer;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.payment.NegativeAmountException;

public interface BalanceModifier {

    /**
     * Decrease the customer balance
     * @param customer involved
     * @param amount to decreased
     * @return the involved customer
     * @throws NotEnoughBalanceException threw if the amount to decrease is higher than the balance
     */
    Customer decreaseBalance(Customer customer, double amount) throws NotEnoughBalanceException;

    /**
     * Increase the customer balance
     * @param customer involved
     * @param creditCard from which the balance is increased
     * @param amount to increase
     * @return the involved customer
     * @throws NegativeAmountException threw if the amount is negative
     */
    Customer rechargeBalance(Customer customer, String creditCard, double amount) throws NegativeAmountException;
}
