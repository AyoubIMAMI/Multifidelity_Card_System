package fr.polytech.interfaces.payment;

import fr.polytech.entities.Store;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;

public interface FidelityCardPurchase {
    void buyWithFidelityCard(Customer customer, Payment payment, Store store) throws NotEnoughBalanceException;
}
