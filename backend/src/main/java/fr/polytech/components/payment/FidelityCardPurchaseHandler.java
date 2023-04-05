package fr.polytech.components.payment;

import fr.polytech.entities.Store;
import fr.polytech.entities.item.Item;
import fr.polytech.entities.item.Product;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.interfaces.payment.BalanceModifier;
import fr.polytech.interfaces.payment.FidelityCardPurchase;
import fr.polytech.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
@Component
public class FidelityCardPurchaseHandler implements FidelityCardPurchase {
    BalanceModifier balanceModifier;
    @Autowired
    public FidelityCardPurchaseHandler(BalanceModifier balanceModifier) {
        this.balanceModifier= balanceModifier;
    }
    @Override
    public Customer buyWithFidelityCard(Customer customer, Store store, Set<Item> shoppingList) throws NotEnoughBalanceException {
        double cashNeeded = computeRequiredCash(shoppingList);
        return balanceModifier.decreaseBalance(customer, cashNeeded);
    }
    private double computeRequiredCash(Set<Item> shoppingList) {

        return shoppingList.stream()
                .filter(x -> (x.getBuyable() instanceof Product))
                .map(x -> x.getQuantity() * ((Product) x.getBuyable()).getCashPrice())
                .reduce(Double::sum).orElse(0.0);
    }
}
