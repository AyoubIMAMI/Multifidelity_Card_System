package fr.polytech.components.payment;

import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.interfaces.payment.IPayment;
import fr.polytech.pojo.Payment;

public class PaymentHandler implements IPayment {
    @Override
    public void pay(Payment payment) throws NotEnoughBalanceException {

    }
}
