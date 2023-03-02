package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.PurchaseFailedException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;
import fr.polytech.pojo.Payment;

public interface IPayment {
    void pay(Payment payment) throws NotEnoughBalanceException, PurchaseFailedException, NoDiscountsFoundException, PaymentAlreadyExistsException;
}
