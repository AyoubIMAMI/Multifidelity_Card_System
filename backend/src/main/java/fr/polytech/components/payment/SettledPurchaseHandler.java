package fr.polytech.components.payment;

import fr.polytech.entities.Store;
import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;
import fr.polytech.interfaces.fidelity.PointModifier;
import fr.polytech.interfaces.payment.PaymentModifier;
import fr.polytech.interfaces.payment.SettledPurchase;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SettledPurchaseHandler implements SettledPurchase {
    PointModifier pointModifier;
    PaymentModifier paymentModifier;

    @Autowired
    public SettledPurchaseHandler(PointModifier pointModifier, PaymentModifier paymentModifier) {
        this.pointModifier = pointModifier;
        this.paymentModifier = paymentModifier;
    }

    @Override
    public void validatePurchase(Customer customer, Payment payment, Store store) throws PaymentAlreadyExistsException {
        paymentModifier.savePayment(payment);
    }

    @Override
    public void winPoint(Customer customer, Payment payment, Store store) {
        pointModifier.incrementPoints(customer, payment.getAmount());
    }
}
