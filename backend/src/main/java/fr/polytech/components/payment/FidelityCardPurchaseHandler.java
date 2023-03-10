package fr.polytech.components.payment;

import fr.polytech.entities.Store;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.interfaces.payment.FidelityCardPurchase;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;

public class FidelityCardPurchaseHandler implements FidelityCardPurchase {
    @Override
    public void buyWithFidelityCard(Customer customer, Payment payment, Store store) throws NotEnoughBalanceException {

    }
}
