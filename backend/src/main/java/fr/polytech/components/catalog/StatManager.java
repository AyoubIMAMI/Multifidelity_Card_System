package fr.polytech.components.catalog;

import java.util.Date;
import java.util.List;

import fr.polytech.entities.Payment;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.polytech.exceptions.IllegalDateException;
import fr.polytech.interfaces.catalog.StatsExplorer;
import fr.polytech.repository.PaymentRepository;

import javax.transaction.Transactional;

@Transactional
@Component
public class StatManager implements StatsExplorer {
    private final PaymentRepository paymentRepository;

    @Autowired
    public StatManager(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public double getOperationCost() {
        return getTotalPointUsed() / (double) 10;
    }

    @Override
    public double getOperationCost(Date date) throws IllegalDateException {
        if(date.after(new Date()))
            throw new IllegalDateException();

        return 0;
    }

    @Override
    public int getTotalPointUsed() {
        List<Payment> payments = paymentRepository.findAll();
        return countNumberOfPoints(payments);
    }

    @Override
    public int getTotalPointUsed(Date date) throws IllegalDateException {
        if(date.after(new Date()))
            throw new IllegalDateException();

        return 0;
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
                if(item.getBuyable() instanceof Discount) {
                    givenPoints += ((Discount) item.getBuyable()).getPointPrice() * item.getQuantity();
                    System.out.println(item.getBuyable().getName() + " is a discount");
                }
                    
        return givenPoints;
    }
}
