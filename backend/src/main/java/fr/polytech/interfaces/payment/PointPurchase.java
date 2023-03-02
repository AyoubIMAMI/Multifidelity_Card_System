package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;

public interface PointPurchase {
    void buyWithPoint(Customer customer, Payment payment) throws NotEnoughBalanceException, NoDiscountsFoundException;
}
