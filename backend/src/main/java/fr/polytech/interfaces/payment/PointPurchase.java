package fr.polytech.interfaces.payment;

import fr.polytech.entities.item.Item;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.entities.Customer;

import java.util.Set;

public interface PointPurchase {

    /**
     * Perform a purchase with points
     * @param customer involved
     * @param shoppingList items to purchase
     * @return the involved customer
     * @throws NotEnoughBalanceException threw if the balance is low
     * @throws NoDiscountsFoundException threw if the discounts are not been found
     */
    Customer buyWithPoint(Customer customer, Set<Item> shoppingList) throws NotEnoughBalanceException, NoDiscountsFoundException;
}
