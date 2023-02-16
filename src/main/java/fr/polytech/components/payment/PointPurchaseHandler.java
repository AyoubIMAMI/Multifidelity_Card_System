package fr.polytech.components.payment;

import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.interfaces.fidelity.FidelityExplorer;
import fr.polytech.interfaces.fidelity.PointModifier;
import fr.polytech.interfaces.payment.PointPurchase;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.Payment;
import fr.polytech.pojo.structure.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PointPurchaseHandler implements PointPurchase {

    PointModifier pointModifier;

    @Autowired
    public PointPurchaseHandler(PointModifier pointModifier) {
        this.pointModifier = pointModifier;
    }

    @Override
    public void buyWithPoint(Customer customer, Payment payment, Store store) throws NotEnoughBalanceException {
        int pointOnFidelityAccount = customer.getFidelityAccount().getPoints();
    }
}
