package fr.polytech.interfaces.payment;

import fr.polytech.entities.Payment;
import fr.polytech.entities.item.Item;
import fr.polytech.exceptions.*;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.exceptions.payment.NegativeAmountException;
import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;
import fr.polytech.exceptions.store.StoreNotFoundException;

import java.util.Set;

public interface IPayment {
    Payment payWithFidelity(Long customerId, Long storeId, Set<Item> shoppingList) throws NotEnoughBalanceException, NoDiscountsFoundException, PaymentAlreadyExistsException, BadCredentialsException, CustomerNotFoundException, StoreNotFoundException, OneDiscountDontExistException,NegativeAmountException;

    Payment payedProcess(Long customerId, Long storeId, Set<Item> shoppingList) throws NotEnoughBalanceException, NoDiscountsFoundException, PaymentAlreadyExistsException, BadCredentialsException, CustomerNotFoundException, StoreNotFoundException, OneDiscountDontExistException,NegativeAmountException;
}
