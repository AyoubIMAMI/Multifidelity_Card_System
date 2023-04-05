package fr.univcotedazur.simpletcfs.cli.commands;

import fr.univcotedazur.simpletcfs.cli.CliContext;
import fr.univcotedazur.simpletcfs.cli.model.Customer;
import fr.univcotedazur.simpletcfs.cli.model.BankTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@ShellComponent
public class CustomerCommands {

    public static final String BASE_URI = "/customers";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private CliContext cliContext;

    @ShellMethod("List all customers")
    public String customers() {
        StringBuilder customers = new StringBuilder("List of customers:\n");
        for (Map.Entry<Long, Customer> entry : cliContext.getCustomers().entrySet()) {
            customers.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }
        return customers.toString();
    }

    @ShellMethod("Register a customer in the backend (register-customer CUSTOMER_NAME CUSTOMER_EMAIL CUSTOMER_PASSWORD)")
    public Customer registerCustomer(String name, String email, String password) {
        Customer res = restTemplate.postForObject(BASE_URI + "/registration", new Customer(name, email, password), Customer.class);
        cliContext.getCustomers().put(res.getId(), res);
        return res;
    }
    @ShellMethod("Login a customer in the backend (login-customer CUSTOMER_EMAIL CUSTOMER_PASSWORD)")
    public Long loginCustomer(String email, String password) {
        Long res = restTemplate.postForObject(BASE_URI + "/login", new Customer("loginUser", email, password), Long.class);
        return res;
    }

    @ShellMethod("Refill the account of a customer in the backend with his id (refill-customer CUSTOMER_ID CREDIT_CARD AMOUNT)")
    public String refillCustomer(Long customerId, String creditCard, int amount) {
        String result = restTemplate.postForObject(BASE_URI + "/refill/" + customerId, new BankTransaction(creditCard, amount), String.class);
        cliContext.getCustomers().get(customerId).getFidelityAccount().setBalance(amount);
        return result;
    }
}