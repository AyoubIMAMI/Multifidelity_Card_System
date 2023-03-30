package fr.polytech.components.payment;

import fr.polytech.entities.Store;
import fr.polytech.entities.item.Item;
import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.PurchaseFailedException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;
import fr.polytech.exceptions.store.StoreNotFoundException;
import fr.polytech.interfaces.customer.CustomerFinder;
import fr.polytech.interfaces.payment.*;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.interfaces.store.StoreFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PaymentHandler implements IPayment {
    CustomerFinder customerFinder;
    PointPurchase pointPurchase;
    SettledPurchase settledPurchase;
    StoreFinder storeFinder;
    FidelityCardPurchase fidelityCardPurchase;

    @Autowired
    public PaymentHandler(CustomerFinder customerFinder, PointPurchase pointPurchase, SettledPurchase settledPurchase,FidelityCardPurchase fidelityCardPurchase) {
        this.customerFinder = customerFinder;
        this.pointPurchase = pointPurchase;
        this.settledPurchase = settledPurchase;
        this.fidelityCardPurchase=fidelityCardPurchase;
    }

    @Override
    public void payWithFidelity(Long customerId, Long storeId, Set<Item> shoppingList) throws NotEnoughBalanceException, PurchaseFailedException, NoDiscountsFoundException, PaymentAlreadyExistsException, BadCredentialsException, CustomerNotFoundException {
        Customer customer=customerFinder.findCustomerById(customerId);
        Store store= storeFinder.findStoreByID(storeId);
        fidelityCardPurchase.buyWithFidelityCard(customer,store,shoppingList);
        payedProcess(customerId, storeId, shoppingList);
    }

    @Override
    public void payedProcess(Long customerId, Long storeId, Set<Item> shoppingList) throws NotEnoughBalanceException, PurchaseFailedException, NoDiscountsFoundException, PaymentAlreadyExistsException, BadCredentialsException, CustomerNotFoundException {
        Customer customer = customerFinder.findCustomerById(customerId);
        Store store = storeFinder.findStoreByID(storeId);
        Payment payment = new Payment(customer, store, shoppingList, true);
    }

    /**@Override
    public void pay(Payment payment) throws NotEnoughBalanceException, PurchaseFailedException, PaymentAlreadyExistsException, BadCredentialsException {
        //Client will pay with is fidelity card
        Customer customer = payment.getCustomer();
        Store store = payment.getStore();
        try {
            storeFinder.findStore(store.getName(),store.getPassword());
        } catch (BadCredentialsException e) {
            throw e;
        }
        if(!payment.isSettled()){
            fidelityCardPurchase.buyWithFidelityCard(customer, payment, store);
        }
        //updatePoint
        settledPurchase.winPoint(customer, payment, store);
        //check if purchase contain discount item
        try {
            pointPurchase.buyWithPoint(customer, payment);
        } catch (NoDiscountsFoundException e) {
            System.out.println(e);
        }
        //validatePurchase
        settledPurchase.validatePurchase(customer, payment, store);
    }*/
}
