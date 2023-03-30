package fr.polytech.interfaces.payment;

import fr.polytech.entities.Store;
import fr.polytech.entities.item.Item;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;

import java.util.Set;

public interface FidelityCardPurchase {
    void buyWithFidelityCard(Customer customer, Store store, Set<Item> shoppingList) throws NotEnoughBalanceException;
}
