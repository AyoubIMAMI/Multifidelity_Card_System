package fr.polytech.interfaces.payment;

import fr.polytech.entities.Payment;
import fr.polytech.entities.item.Item;
import fr.polytech.exceptions.*;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;
import fr.polytech.exceptions.store.StoreNotFoundException;

import java.util.Set;

public interface IPayment {
    Payment payWithFidelity(Long customerId, Long storeId, Set<Item> shoppingList) throws NotEnoughBalanceException, PurchaseFailedException, NoDiscountsFoundException, PaymentAlreadyExistsException, BadCredentialsException, CustomerNotFoundException, StoreNotFoundException, OneDiscountDontExistException;

    Payment payedProcess(Long customerId, Long storeId, Set<Item> shoppingList) throws NotEnoughBalanceException, PurchaseFailedException, NoDiscountsFoundException, PaymentAlreadyExistsException, BadCredentialsException, CustomerNotFoundException, StoreNotFoundException, OneDiscountDontExistException;
}
