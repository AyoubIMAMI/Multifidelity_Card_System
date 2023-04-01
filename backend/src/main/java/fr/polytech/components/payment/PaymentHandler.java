package fr.polytech.components.payment;

import fr.polytech.entities.Store;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.item.Item;
import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.PurchaseFailedException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;
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
    public PaymentHandler(CustomerFinder customerFinder, PointPurchase pointPurchase, SettledPurchase settledPurchase, FidelityCardPurchase fidelityCardPurchase) {
        this.customerFinder = customerFinder;
        this.pointPurchase = pointPurchase;
        this.settledPurchase = settledPurchase;
        this.fidelityCardPurchase = fidelityCardPurchase;
    }

    @Override
    public Payment payWithFidelity(Long customerId, Long storeId, Set<Item> shoppingList) throws NotEnoughBalanceException, PurchaseFailedException, NoDiscountsFoundException, PaymentAlreadyExistsException, BadCredentialsException, CustomerNotFoundException {
        Customer customer = customerFinder.findCustomerById(customerId);
        Store store = storeFinder.findStoreByID(storeId);
        checkDiscountAndPayWithPointPurchase(customer, shoppingList);
        fidelityCardPurchase.buyWithFidelityCard(customer, store, shoppingList);
        return sendToSettledPayment(customer, store, shoppingList);
    }

    @Override
    public Payment payedProcess(Long customerId, Long storeId, Set<Item> shoppingList) throws NotEnoughBalanceException, PurchaseFailedException, NoDiscountsFoundException, PaymentAlreadyExistsException, BadCredentialsException, CustomerNotFoundException {
        Customer customer = customerFinder.findCustomerById(customerId);
        Store store = storeFinder.findStoreByID(storeId);
        checkDiscountAndPayWithPointPurchase(customer, shoppingList);
        return sendToSettledPayment(customer, store, shoppingList);
    }

    private Payment sendToSettledPayment(Customer customer, Store store, Set<Item> shoppingList) throws PaymentAlreadyExistsException, PurchaseFailedException {
        Payment payment = new Payment(customer, store, shoppingList, true);
        settledPurchase.validatePurchase(payment);
        return payment;
    }

    private void checkDiscountAndPayWithPointPurchase(Customer customer, Set<Item> shoppingList) throws NoDiscountsFoundException, NotEnoughBalanceException {
        for (Item item: shoppingList) {
            if(item.getProduct() instanceof Discount) {
                pointPurchase.buyWithPoint(customer, shoppingList);
            }
        }
    }
}
