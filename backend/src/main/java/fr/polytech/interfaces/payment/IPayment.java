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

    /**
     * Pay with the fidelity card
     * @param customerId customer ID
     * @param storeId store ID
     * @param shoppingList items
     * @return the Payment
     * @throws NotEnoughBalanceException threw if not enough balanced
     * @throws NoDiscountsFoundException threw if no discounts found
     * @throws PaymentAlreadyExistsException threw if the Payment already exist
     * @throws BadCredentialsException threw if there are wrong credentials
     * @throws CustomerNotFoundException threw if the customer is not found
     * @throws StoreNotFoundException threw if the store is not found
     * @throws OneDiscountDontExistException threw if a discount does not exist
     * @throws NegativeAmountException threw if the amount is negative
     */
    Payment payWithFidelity(Long customerId, Long storeId, Set<Item> shoppingList) throws NotEnoughBalanceException, NoDiscountsFoundException, PaymentAlreadyExistsException, BadCredentialsException, CustomerNotFoundException, StoreNotFoundException, OneDiscountDontExistException,NegativeAmountException;

    /**
     * Pay with points
     * @param customerId customer ID
     * @param storeId store ID
     * @param shoppingList items to purchase
     * @return the Payment
     * @throws NotEnoughBalanceException threw if not enough balanced
     * @throws NoDiscountsFoundException threw if no discounts found
     * @throws PaymentAlreadyExistsException threw if the Payment already exist
     * @throws BadCredentialsException threw if there are wrong credentials
     * @throws CustomerNotFoundException threw if the customer is not found
     * @throws StoreNotFoundException threw if the store is not found
     * @throws OneDiscountDontExistException threw if a discount does not exist
     * @throws NegativeAmountException threw if the amount is negative
     */
    Payment payedProcess(Long customerId, Long storeId, Set<Item> shoppingList) throws NotEnoughBalanceException, NoDiscountsFoundException, PaymentAlreadyExistsException, BadCredentialsException, CustomerNotFoundException, StoreNotFoundException, OneDiscountDontExistException,NegativeAmountException;
}
