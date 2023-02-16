package fr.polytech.components.payment;

import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.NotEnoughPermissionException;
import fr.polytech.exceptions.PaymentAlreadyExistsException;
import fr.polytech.exceptions.PurchaseFailedException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.interfaces.customer.CustomerFinder;
import fr.polytech.interfaces.discount.DiscountExplorer;
import fr.polytech.interfaces.payment.*;
import fr.polytech.interfaces.store.StoreFinder;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.Payment;
import fr.polytech.pojo.item.Discount;
import fr.polytech.pojo.item.Item;
import fr.polytech.pojo.structure.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PaymentHandler implements IPayment {
    CustomerFinder customerFinder;
    PointPurchase pointPurchase;
    SettledPurchase settledPurchase;
    FidelityCardPurchase fidelityCardPurchase;
    StoreFinder storeFinder;
    DiscountExplorer discountExplorer;

    @Autowired
    public PaymentHandler(CustomerFinder customerFinder, PointPurchase pointPurchase, SettledPurchase settledPurchase, FidelityCardPurchase fidelityCardPurchase, StoreFinder storeFinder, DiscountExplorer discountExplorer) {
        this.customerFinder = customerFinder;
        this.pointPurchase = pointPurchase;
        this.settledPurchase = settledPurchase;
        this.fidelityCardPurchase = fidelityCardPurchase;
        this.storeFinder = storeFinder;
        this.discountExplorer = discountExplorer;
    }

    @Override
    public void pay(Payment payment) throws NotEnoughBalanceException, PurchaseFailedException, NotEnoughPermissionException, PaymentAlreadyExistsException {
        //Client will pay with is fidelity card
        Customer customer = payment.getCustomer();
        Store store = payment.getStore();
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
    }
}
