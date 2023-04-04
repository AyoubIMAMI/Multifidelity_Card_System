package fr.univcotedazur.simpletcfs.cli.commands;

import fr.univcotedazur.simpletcfs.cli.CliContext;
import fr.univcotedazur.simpletcfs.cli.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@ShellComponent
public class PaymentCommands {

    public static final String BASE_URI ="/pay";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private CliContext cliContext;

    @ShellMethod("List all payments")
    public String payments() {
        StringBuilder payments = new StringBuilder("List of payments:\n");
        for (Map.Entry<Long, Payment> entry : cliContext.getPayments().entrySet()) {
            payments.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }
        return payments.toString();
    }

    @ShellMethod("Display items in cart")
    public String cart() {
        StringBuilder cartStr = new StringBuilder("Items in cart:\n");
        for (Item item : cliContext.getCart()) {
            cartStr.append(item).append("\n");
        }
        return cartStr.toString();
    }

    @ShellMethod("Clear items in cart")
    public String clearCart() {
        StringBuilder cartStr = new StringBuilder("Items in cart:\n");
        for (Item item : cliContext.getCart()) {
            cartStr.append(item).append("\n");
        }
        return cartStr.toString();
    }

    @ShellMethod("Add a Product Item to cart (add-product QUANTITY PRODUCT_NAME STORE_ID CASH_PRICE")
    public Item addProduct(int quantity, String productName, Long storeId, double cashPrice) {
        Item item = new Item(quantity, new Product(productName, storeId, cashPrice));
        cliContext.getCart().add(item);
        return item;
    }

    @ShellMethod("Add a Discount Item to cart (add-product QUANTITY PRODUCT_NAME STORE_ID CASH_PRICE POINT_PRICE")
    public Item addDiscount(int quantity, String productName, Long storeId, int pointPrice) {
        Item item = new Item(quantity, new Discount(productName, storeId, pointPrice));
        cliContext.getCart().add(item);
        return item;
    }

    @ShellMethod("Add a Discount Item to cart by Discount Id (add-discount-by-id QUANTITY DISCOUNT_ID")
    public Item addDiscountById(int quantity, Long discountId) {
        Item item = new Item(quantity, cliContext.getDiscounts().get(discountId));
        cliContext.getCart().add(item);
        return item;
    }

    @ShellMethod("Do a settled payment (settled-payment CUSTOMER_ID STORE_ID)")
    public Payment settledPayment(Long customerId, Long storeId) {
        Payment res = restTemplate.postForObject(BASE_URI + "/store/" + storeId + "/customer/" + customerId + "/settled", cliContext.getCart(), Payment.class);
        cliContext.getPayments().put(res.getId(), res);
        cliContext.getCart().clear();
        cliContext.getCustomers().put(res.getCustomer().getId(),res.getCustomer());
        return res;
    }

    @ShellMethod("Do a payment with balance on fidelity account (fidelity-payment CUSTOMER_ID STORE_ID)")
    public Payment fidelityPayment(Long customerId, Long storeId) {
        Payment res = restTemplate.postForObject(BASE_URI + "/store/" + storeId + "/customer/" + customerId + "/fidelity", cliContext.getCart(), Payment.class);
        cliContext.getPayments().put(res.getId(), res);
        cliContext.getCart().clear();
        cliContext.getCustomers().put(res.getCustomer().getId(),res.getCustomer());
        return res;
    }
}
