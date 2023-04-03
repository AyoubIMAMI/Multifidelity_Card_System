package fr.polytech.components.payment;

import fr.polytech.entities.Store;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.item.Item;
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
    public void buyWithFidelityCard(Customer customer, Store store, Set<Item> shoppingList) throws NotEnoughBalanceException {
        double cashNeeded = computeRequiredCash(shoppingList);
        balanceModifier.decreaseBalance(customer,cashNeeded);
    }
    private double computeRequiredCash(Set<Item> shoppingList) {

        return shoppingList.stream()
                .filter(x -> !(x.getProduct() instanceof Discount))
                .map(x -> x.getQuantity() * x.getProduct().getCashPrice())
                .reduce(Double::sum).orElse(0.0);
    }
}
