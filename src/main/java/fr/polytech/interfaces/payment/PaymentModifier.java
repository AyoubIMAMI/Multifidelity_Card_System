package fr.polytech.interfaces.payment;

import fr.polytech.pojo.Payment;

public interface PaymentModifier {
    void savePayment(Payment payment);
}
