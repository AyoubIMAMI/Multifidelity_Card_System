package fr.polytech.components.catalog;

import java.util.Date;
import java.util.List;

import fr.polytech.entities.Payment;
import fr.polytech.entities.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.polytech.exceptions.IllegalDateException;
import fr.polytech.interfaces.catalog.StatsExplorer;
import fr.polytech.repository.PaymentRepository;

@Component
public class StatManager implements StatsExplorer {
    private PaymentRepository paymentRepository;

    @Autowired
    public StatManager(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public double getOperationCost() {
        List<Payment> payments = paymentRepository.findAll();
        double totalCost = 0;
        for(Payment payment : payments)
            for(Item item : payment.getShoppingList())
                totalCost += item.getQuantity() * item.getProduct().getCashPrice();
        return totalCost;
    }

    @Override
    public double getOperationCost(Date date) throws IllegalDateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOperationCost'");
    }

}
