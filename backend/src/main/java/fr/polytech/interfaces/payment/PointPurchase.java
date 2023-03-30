package fr.polytech.interfaces.payment;

import fr.polytech.entities.item.Item;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;

import java.util.Set;

public interface PointPurchase {
    void buyWithPoint(Customer customer, Set<Item> shoppingList) throws NotEnoughBalanceException, NoDiscountsFoundException;
}
