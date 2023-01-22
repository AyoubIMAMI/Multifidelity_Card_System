package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.NotEnoughBalanceException;

public interface IPayment {
    void Pay(fr.polytech.pojo.Payment payment) throws NotEnoughBalanceException;
}
