package fr.polytech.components.payment;

import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.interfaces.fidelity.PointModifier;
import fr.polytech.interfaces.payment.PointPurchase;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class PointPurchaseHandler implements PointPurchase {

    PointModifier pointModifier;

    @Autowired
    public PointPurchaseHandler(PointModifier pointModifier) {
        this.pointModifier = pointModifier;
    }

    @Override
    public void buyWithPoint(Customer customer, Set<Item> shoppingList) throws NotEnoughBalanceException, NoDiscountsFoundException {
        int pointsOnFidelityAccount = customer.getFidelityAccount().getPoints();
        int pointsRequired = computeRequiredPoints(shoppingList);

        if (pointsRequired == 0) {
            throw new NoDiscountsFoundException();
        }

        if (pointsOnFidelityAccount < pointsRequired) {
            throw new NotEnoughBalanceException();
        }

        pointModifier.decrementPoints(customer, pointsRequired);
    }

    private int computeRequiredPoints(Set<Item> shoppingList) {
        int points = shoppingList.stream()
                .filter(x -> x.getProduct() instanceof Discount)
                .map(x -> x.getQuantity() * ((Discount) x.getProduct()).getPointPrice())
                .reduce(Integer::sum).orElse(0);

        return points;
    }
}
