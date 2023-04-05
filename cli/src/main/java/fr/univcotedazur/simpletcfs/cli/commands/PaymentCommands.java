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
        for (Map.Entry<Long, CliPayment> entry : cliContext.getPayments().entrySet()) {
            payments.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }
        return payments.toString();
    }

    @ShellMethod("Do a settled payment (settled-payment CUSTOMER_ID STORE_ID)")
    public CliPayment settledPayment(Long customerId, Long storeId) {
        CliPayment res = restTemplate.postForObject(BASE_URI + "/store/" + storeId + "/customer/" + customerId + "/settled", cliContext.getCart(), CliPayment.class);
        cliContext.getPayments().put(res.getId(), res);
        cliContext.getCart().clear();
        cliContext.getCustomers().put(res.getCustomer().getId(), res.getCustomer());
        return res;
    }

    @ShellMethod("Do a payment with balance on fidelity account (fidelity-payment CUSTOMER_ID STORE_ID)")
    public CliPayment fidelityPayment(Long customerId, Long storeId) {
        CliPayment res = restTemplate.postForObject(BASE_URI + "/store/" + storeId + "/customer/" + customerId + "/fidelity", cliContext.getCart(), CliPayment.class);
        cliContext.getPayments().put(res.getId(), res);
        cliContext.getCart().clear();
        cliContext.getCustomers().put(res.getCustomer().getId(), res.getCustomer());
        return res;
    }
}
