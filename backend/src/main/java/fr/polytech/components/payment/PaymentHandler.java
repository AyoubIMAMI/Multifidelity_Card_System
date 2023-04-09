package fr.polytech.components.payment;

import fr.polytech.entities.Store;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.item.Item;
import fr.polytech.exceptions.*;
import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.entities.item.Product;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.exceptions.payment.NegativeAmountException;
import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;
import fr.polytech.exceptions.store.StoreNotFoundException;
import fr.polytech.interfaces.customer.CustomerFinder;
import fr.polytech.interfaces.discount.DiscountExplorer;
import fr.polytech.interfaces.payment.*;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.interfaces.store.StoreFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
public class PaymentHandler implements IPayment {
    CustomerFinder customerFinder;
    PointPurchase pointPurchase;
    SettledPurchase settledPurchase;
    StoreFinder storeFinder;
    FidelityCardPurchase fidelityCardPurchase;
    DiscountExplorer discountExplorer;

    @Autowired
    public PaymentHandler(CustomerFinder customerFinder, PointPurchase pointPurchase, SettledPurchase settledPurchase, FidelityCardPurchase fidelityCardPurchase, StoreFinder storeFinder,DiscountExplorer discountExplorer) {
        this.customerFinder = customerFinder;
        this.pointPurchase = pointPurchase;
        this.settledPurchase = settledPurchase;
        this.fidelityCardPurchase = fidelityCardPurchase;
        this.storeFinder = storeFinder;
        this.discountExplorer=discountExplorer;
    }

    @Override
    @Transactional
    public Payment payWithFidelity(Long customerId, Long storeId, Set<Item> shoppingList) throws NotEnoughBalanceException, NoDiscountsFoundException, PaymentAlreadyExistsException, CustomerNotFoundException, StoreNotFoundException, OneDiscountDontExistException,NegativeAmountException {
        Customer customer = customerFinder.findCustomerById(customerId);
        Store store = storeFinder.findStoreByID(storeId);
        shoppingList.forEach(item -> item.getBuyable().setStore(store));
        checkDiscountAndPayWithPointPurchase(customer, shoppingList,storeId);
        customer = fidelityCardPurchase.buyWithFidelityCard(customer, shoppingList);
        return sendToSettledPayment(customer, store, shoppingList);
    }

    @Override
    @Transactional
    public Payment payedProcess(Long customerId, Long storeId, Set<Item> shoppingList) throws NotEnoughBalanceException, NoDiscountsFoundException, PaymentAlreadyExistsException, CustomerNotFoundException, StoreNotFoundException, OneDiscountDontExistException,NegativeAmountException {
        Customer customer = customerFinder.findCustomerById(customerId);
        Store store = storeFinder.findStoreByID(storeId);
        shoppingList.forEach(item -> item.getBuyable().setStore(store));
        checkDiscountAndPayWithPointPurchase(customer, shoppingList, storeId);
        return sendToSettledPayment(customer, store, shoppingList);
    }

    private Payment sendToSettledPayment(Customer customer, Store store, Set<Item> shoppingList) throws PaymentAlreadyExistsException, NegativeAmountException {
        checkNegativePrice(shoppingList);
        Payment payment = new Payment(customer, store, shoppingList);
        return settledPurchase.validatePurchase(payment);
    }

    private void checkDiscountAndPayWithPointPurchase(Customer customer, Set<Item> shoppingList, Long storeId) throws NoDiscountsFoundException, NotEnoughBalanceException, OneDiscountDontExistException, StoreNotFoundException {
        for (Item item: shoppingList) {
            if(item.getBuyable() instanceof Discount) {
                Discount discount;
                try {
                    discount=discountExplorer.findDiscountById(item.getBuyable().getId());
                } catch (DiscountNotFoundException e) {
                    throw new OneDiscountDontExistException();
                }
                if(!discount.getStore().getId().equals(storeId)) throw new StoreNotFoundException();
                pointPurchase.buyWithPoint(customer, shoppingList);
                }
        }
    }

    private void checkNegativePrice(Set<Item> shoppingList) throws NegativeAmountException {
        for (Item item: shoppingList) {
            if(item.getBuyable() instanceof Discount) {
                if(((Discount) item.getBuyable()).getPointPrice() <= 0) {
                    throw new NegativeAmountException(((Discount) item.getBuyable()).getPointPrice());
                };
            } else if(item.getBuyable() instanceof Product) {
                if(((Product) item.getBuyable()).getCashPrice() <= 0) {
                    throw new NegativeAmountException(((Product) item.getBuyable()).getCashPrice());
                };
            }
        }
    }
}
