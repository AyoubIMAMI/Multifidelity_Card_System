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

import java.util.Set;

@Component
public class PointPurchaseHandler implements PointPurchase {

    PointModifier pointModifier;

    @Autowired
    public PointPurchaseHandler(PointModifier pointModifier) {
        this.pointModifier = pointModifier;
    }

    @Override
    public void buyWithPoint(Customer customer, Payment payment) throws NotEnoughBalanceException, NoDiscountsFoundException {
        int pointsOnFidelityAccount = customer.getFidelityAccount().getPoints();
        int pointsRequired = computeRequiredPoints(payment);

        if (pointsRequired == 0) {
            throw new NoDiscountsFoundException();
        }

        if (pointsOnFidelityAccount < pointsRequired) {
            throw new NotEnoughBalanceException();
        }

        pointModifier.decrementPoints(customer.getFidelityAccount(), pointsRequired);
    }

    private int computeRequiredPoints(Payment payment) {
        Set<Item> shoppingList = payment.getShoppingList();
        int points = shoppingList.stream()
                .filter(x -> x.getProduct() instanceof Discount)
                .map(x -> x.getQuantity() * ((Discount) x.getProduct()).getPointPrice())
                .reduce(Integer::sum).orElse(0);

        return points;
    }
}
