package fr.polytech.interfaces.Payment;

import fr.polytech.exceptions.NotEnoughBalanceException;

public interface IPayment {
    void Pay(fr.polytech.pojo.Payment payment) throws NotEnoughBalanceException;
}
