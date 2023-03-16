package fr.polytech.components.payment;

import fr.polytech.entities.Store;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.PurchaseFailedException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;
import fr.polytech.interfaces.customer.CustomerFinder;
import fr.polytech.interfaces.payment.*;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentHandler implements IPayment {
    CustomerFinder customerFinder;
    PointPurchase pointPurchase;
    SettledPurchase settledPurchase;

    @Autowired
    public PaymentHandler(CustomerFinder customerFinder, PointPurchase pointPurchase, SettledPurchase settledPurchase) {
        this.customerFinder = customerFinder;
        this.pointPurchase = pointPurchase;
        this.settledPurchase = settledPurchase;
    }

    @Override
    public void pay(Payment payment) throws NotEnoughBalanceException, PurchaseFailedException, PaymentAlreadyExistsException {
        //Client will pay with is fidelity card
        Customer customer = payment.getCustomer();
        Store store = payment.getStore();
        /*
        if(!payment.isSettled()){
            fidelityCardPurchase.buyWithFidelityCard(customer, payment, store);
        }
         */
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
    }
}
