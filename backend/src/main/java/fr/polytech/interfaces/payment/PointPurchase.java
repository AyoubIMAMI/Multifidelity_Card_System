package fr.polytech.interfaces.payment;

import fr.polytech.entities.item.Item;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.entities.Customer;

import java.util.Set;

public interface PointPurchase {
    Customer buyWithPoint(Customer customer, Set<Item> shoppingList) throws NotEnoughBalanceException, NoDiscountsFoundException;
}
