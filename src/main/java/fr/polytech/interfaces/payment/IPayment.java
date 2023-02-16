package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.pojo.Payment;

public interface IPayment {
    void pay(Payment payment) throws NotEnoughBalanceException;
}
