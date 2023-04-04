package fr.univcotedazur.simpletcfs.cli.commands;

import fr.univcotedazur.simpletcfs.cli.CliContext;
import fr.univcotedazur.simpletcfs.cli.model.*;
import fr.univcotedazur.simpletcfs.cli.model.test.ItemTest;
import fr.univcotedazur.simpletcfs.cli.model.test.Test1;
import fr.univcotedazur.simpletcfs.cli.model.test.Test2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        for (Map.Entry<Long, CliPayment> entry : cliContext.getPayments().entrySet()) {
            payments.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }
        return payments.toString();
    }

    @ShellMethod("Display items in cart")
    public String cart() {
        StringBuilder cartStr = new StringBuilder("Items in cart:\n");
        for (CliItem item : cliContext.getCart()) {
            cartStr.append(item).append("\n");
        }
        return cartStr.toString();
    }

    @ShellMethod("Add a Product Item to cart (add-product QUANTITY PRODUCT_NAME STORE_ID CASH_PRICE")
    public CliItem addProduct(int quantity, String productName, Long storeId, double cashPrice) {
        CliItem item = new CliItem(quantity, new CliProduct(productName, storeId, cashPrice));
        cliContext.getCart().add(item);
        return item;
    }

    @ShellMethod("Add a Discount Item to cart (add-product QUANTITY PRODUCT_NAME STORE_ID CASH_PRICE POINT_PRICE")
    public CliItem addDiscount(int quantity, String productName, Long storeId, double cashPrice, int pointPrice) {
        CliItem item = new CliItem(quantity, new CliDiscount(productName, storeId, cashPrice, pointPrice));
        cliContext.getCart().add(item);
        return item;
    }

    @ShellMethod("Add a Discount Item to cart by Discount Id (add-discount-by-id QUANTITY DISCOUNT_ID")
    public CliItem addDiscountById(int quantity, Long discountId) {
        CliItem item = new CliItem(quantity, cliContext.getDiscounts().get(discountId));
        cliContext.getCart().add(item);
        return item;
    }

    @ShellMethod("Do a settled payment (settled-payment CUSTOMER_ID STORE_ID)")
    public CliPayment settledPayment(Long customerId, Long storeId) {
        CliPayment res = restTemplate.postForObject(BASE_URI + "/store/" + storeId + "/customer/" + customerId + "/settled", cliContext.getCart(), CliPayment.class);
        cliContext.getPayments().put(res.getId(), res);
        cliContext.getCart().clear();
        cliContext.getCustomers().put(res.getCustomer().getId(),res.getCustomer());
        return res;
    }

    @ShellMethod("Do a payment with balance on fidelity account (fidelity-payment CUSTOMER_ID STORE_ID)")
    public CliPayment fidelityPayment(Long customerId, Long storeId) {
        CliPayment res = restTemplate.postForObject(BASE_URI + "/store/" + storeId + "/customer/" + customerId + "/fidelity", cliContext.getCart(), CliPayment.class);
        cliContext.getPayments().put(res.getId(), res);
        cliContext.getCart().clear();
        cliContext.getCustomers().put(res.getCustomer().getId(),res.getCustomer());
        return res;
    }

    @ShellMethod("test")
    public CliPayment test() {
        Set<ItemTest> items = new HashSet<>(){{
            add(new ItemTest(1, new Test1("n", 2L, 5)));
            add(new ItemTest(1, new Test2("n", 2L, 10)));
        }};
        CliPayment res = restTemplate.postForObject(BASE_URI + "/test", items, CliPayment.class);
        return res;
    }
}
