package fr.polytech.components.payment;

import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.interfaces.payment.FidelityCardPurchase;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.Payment;
import fr.polytech.pojo.structure.Store;

public class FidelityCardPurchaseHandler implements FidelityCardPurchase {
    @Override
    public void buyWithFidelityCard(Customer customer, Payment payment, Store store) throws NotEnoughBalanceException {

    }
}
