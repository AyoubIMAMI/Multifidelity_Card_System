package fr.polytech.interfaces.payment;

import fr.polytech.entities.Payment;
import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.PurchaseFailedException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;

public interface IPayment {
    void pay(Payment payment) throws NotEnoughBalanceException, PurchaseFailedException, NoDiscountsFoundException, PaymentAlreadyExistsException, BadCredentialsException;
}
