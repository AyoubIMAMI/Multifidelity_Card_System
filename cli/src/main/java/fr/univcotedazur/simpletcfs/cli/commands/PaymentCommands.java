package fr.univcotedazur.simpletcfs.cli.commands;

import fr.univcotedazur.simpletcfs.cli.CliContext;
import fr.univcotedazur.simpletcfs.cli.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@ShellComponent
public class PaymentCommands {

    public static final String BASE_URI ="/pay";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private CliContext cliContext;

    @ShellMethod("Do a settled payment (settled-payment CUSTOMER_ID STORE_ID)")
    public CliPayment settledPayment(Long customerId, Long storeId) {
        CliPayment res = restTemplate.postForObject(BASE_URI + "/store/" + storeId + "/customer/" + customerId + "/settled", cliContext.getCart(), CliPayment.class, CliCustomer.class);
        cliContext.getPayments().put(res.getId(), res);
        return res;
    }

    @ShellMethod("Add a Product Item to cart (add-product QUANTITY PRODUCT_NAME STORE_ID CASH_PRICE")
    public CliItem addProduct(int quantity, String productName, Long storeId, double cashPrice) {
        CliItem item = new CliItem(quantity, new CliProduct(productName, storeId, cashPrice));
        cliContext.getCart().add(item);
        return item;
    }
}
