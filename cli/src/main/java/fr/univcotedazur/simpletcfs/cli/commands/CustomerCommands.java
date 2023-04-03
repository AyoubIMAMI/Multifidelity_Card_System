package fr.univcotedazur.simpletcfs.cli.commands;

import fr.univcotedazur.simpletcfs.cli.CliContext;
import fr.univcotedazur.simpletcfs.cli.model.CliCustomer;
import fr.univcotedazur.simpletcfs.cli.model.CliBankTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.client.RestTemplate;

@ShellComponent
public class CustomerCommands {

    public static final String BASE_URI = "/customers";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private CliContext cliContext;

    @ShellMethod("Register a customer in the backend (register-customer CUSTOMER_NAME CUSTOMER_EMAIL CUSTOMER_PASSWORD)")
    public CliCustomer registerCustomer(String name, String email, String password) {
        CliCustomer res = restTemplate.postForObject(BASE_URI + "/registration", new CliCustomer(name, email, password), CliCustomer.class);
        cliContext.getCustomers().put(res.getId(), res);
        return res;
    }
    @ShellMethod("Login a customer in the backend (login-customer CUSTOMER_EMAIL CUSTOMER_PASSWORD)")
    public Long loginCustomer(String email, String password) {
        Long res = restTemplate.postForObject(BASE_URI + "/login", new CliCustomer("loginUser", email, password), Long.class);
        return res;
    }

    //TODO Proke l'exception PaymentInBankException
    @ShellMethod("Refill the account of a customer in the backend with his id (refill-customer CUSTOMER_EMAIL CUSTOMER_PASSWORD)")
    public String refillCustomer(Long customerId, String creditCard, int amount) {
        String result = restTemplate.postForObject(BASE_URI + "/refill/" + customerId, new CliBankTransaction(creditCard, amount), String.class);
        return result;
    }

    @ShellMethod("List all customers")
    public String customers() {
        return cliContext.getCustomers().toString();
    }

}