package fr.univcotedazur.simpletcfs.cli.commands;

import fr.univcotedazur.simpletcfs.cli.CliContext;
import fr.univcotedazur.simpletcfs.cli.model.CliDiscount;
import fr.univcotedazur.simpletcfs.cli.model.CliItem;
import fr.univcotedazur.simpletcfs.cli.model.CliPayment;
import fr.univcotedazur.simpletcfs.cli.model.CliProduct;
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
        Set<CliItem> shoppingList = new HashSet<CliItem>(){{
            add(new CliItem(1, new CliProduct("Biscuit", 123L, 2)));
            add(new CliItem(3, new CliProduct("Lait", 456L, 4)));
            add(new CliItem(4, new CliProduct("Pomme", 789L, 6)));
        }};
        CliPayment res = restTemplate.postForObject(BASE_URI + "/store/" + storeId + "/customer/" + customerId + "/settled", shoppingList, CliPayment.class);
        cliContext.getPayments().put(res.getId(), res);
        return res;
    }
}
