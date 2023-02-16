package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.Payment;
import fr.polytech.pojo.structure.Store;

public interface PointPurchase {
    void buyWithPoint(Customer customer, Payment payment) throws NotEnoughBalanceException, NoDiscountsFoundException;
}
