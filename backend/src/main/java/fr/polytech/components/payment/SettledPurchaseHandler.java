package fr.polytech.components.payment;

import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;
import fr.polytech.interfaces.fidelity.FidelityExplorer;
import fr.polytech.interfaces.fidelity.PointModifier;
import fr.polytech.interfaces.payment.PaymentModifier;
import fr.polytech.interfaces.payment.SettledPurchase;
import fr.polytech.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SettledPurchaseHandler implements SettledPurchase {
    private PointModifier pointModifier;
    private PaymentModifier paymentModifier;
    private FidelityExplorer fidelityExplorer;

    @Autowired
    public SettledPurchaseHandler(PointModifier pointModifier, PaymentModifier paymentModifier,FidelityExplorer fidelityExplorer) {
        this.pointModifier = pointModifier;
        this.paymentModifier = paymentModifier;
        this.fidelityExplorer = fidelityExplorer;
    }

    @Override
    public Payment validatePurchase(Payment payment) throws PaymentAlreadyExistsException {
        winPoint(payment);
        Payment newPayment = paymentModifier.savePayment(payment);
        fidelityExplorer.checkIfPossibleToBecomeVfp(payment.getCustomer());
        System.out.println("Payment returned correctly");
        return newPayment;
    }
    //TODO y'a t'il besoin de la mettre dans l'interface alors qu'on l'utilise juste dans cette classe?
    @Override
    public void winPoint(Payment payment) {
        System.out.println("Adding fidelity points");
        pointModifier.incrementPoints(payment.getCustomer(), payment.getAmount());
    }
}
