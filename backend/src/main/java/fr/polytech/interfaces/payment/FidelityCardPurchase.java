package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.entities.structure.Store;

public interface FidelityCardPurchase {
    void buyWithFidelityCard(Customer customer, Payment payment, Store store) throws NotEnoughBalanceException;
}
