package fr.polytech.components.store;

import java.util.Date;
import java.util.List;

import fr.polytech.entities.Payment;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.item.Item;
import fr.polytech.interfaces.payment.PaymentExplorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.polytech.exceptions.IllegalDateException;
import fr.polytech.interfaces.store.StatsExplorer;
import fr.polytech.repository.PaymentRepository;

@Component
@Transactional
public class StatManager implements StatsExplorer {
    PaymentExplorer paymentExplorer;

    @Autowired
    public StatManager(PaymentExplorer paymentExplorer) {
        this.paymentExplorer = paymentExplorer;
    }

    @Override
    public double getOperationCost() {
        return getUsedPoints() / (double) 10;
    }

    @Override
    public double getOperationCost(Date date) throws IllegalDateException {
        if(date.after(new Date()))
            throw new IllegalDateException(date);

        return getUsedPoints(date) / (double) 10;
    }

    @Override
    public int getUsedPoints() {
        return countNumberOfPoints(paymentExplorer.findAllPayments());
    }

    @Override
    public int getUsedPoints(Date date) throws IllegalDateException {
        if(date.after(new Date()))
            throw new IllegalDateException(date);

        return countNumberOfPoints(paymentExplorer.findAllByTransactionDateAfter(date));
    }

    /**
     * Count the number of points used for a given list of payment
     * @param payments The list of payments to count the total points used.
     * @return The number of used points.
     */
    private int countNumberOfPoints(List<Payment> payments) {
        int givenPoints = 0;
        for(Payment payment : payments)
            for(Item item : payment.getShoppingList())
                if(item.getBuyable() instanceof Discount)
                    givenPoints += ((Discount) item.getBuyable()).getPointPrice() * item.getQuantity();
        return givenPoints;
    }
}
