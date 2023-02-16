package fr.polytech.components.payment;

import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.interfaces.customer.CustomerFinder;
import fr.polytech.interfaces.discount.DiscountExplorer;
import fr.polytech.interfaces.payment.*;
import fr.polytech.interfaces.store.StoreFinder;
import fr.polytech.pojo.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public void pay(Payment payment) throws NotEnoughBalanceException {
        //Client will pay with is fidelity card
        if(!payment.isSettled()){
            fidelityCardPurchase.buyWithFidelityCard(payment.getCustomer(), payment,  payment.getStore());
        }

        //Check if discount item in the order
    }
}
