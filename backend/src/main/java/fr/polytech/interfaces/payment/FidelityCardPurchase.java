package fr.polytech.interfaces.payment;

import fr.polytech.entities.item.Item;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.entities.Customer;

import java.util.Set;

public interface FidelityCardPurchase {

    /**
     * Use the fidelity card to perform a purchase
     * @param customer involved in the purchase
     * @param shoppingList items to purchase
     * @return involved customer
     * @throws NotEnoughBalanceException threw if the balance is not enough to perform the purchase
     */
    Customer buyWithFidelityCard(Customer customer, Set<Item> shoppingList) throws NotEnoughBalanceException;
}
