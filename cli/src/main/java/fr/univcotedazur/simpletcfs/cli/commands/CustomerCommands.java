package fr.univcotedazur.simpletcfs.cli.commands;

import fr.univcotedazur.simpletcfs.cli.CliContext;
import fr.univcotedazur.simpletcfs.cli.model.CliCustomer;
import fr.univcotedazur.simpletcfs.cli.model.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@ShellComponent
public class CustomerCommands {

    public static final String BASE_URI = "/customers";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private CliContext cliContext;

    @ShellMethod("Register a customer in the CoD backend (register CUSTOMER_NAME CUSTOMER_EMAIL CUSTOMER_PASSWORD)")
    public CliCustomer register(String name, String email, String password) {
        CliCustomer res = restTemplate.postForObject(BASE_URI + "/registration", new CliCustomer(name, email, password), CliCustomer.class);
        cliContext.getCustomers().put(res.getName(), res);
        return res;
    }
    @ShellMethod("Login a customer in the CoD backend (login CUSTOMER_EMAIL CUSTOMER_PASSWORD)")
    public Long login(String email, String password) {
        Long res = restTemplate.postForObject(BASE_URI + "/login", new CliCustomer("loginUser", email, password), Long.class);
        return res;
    }

    //TODO Proke l'exception PaymentInBankException
    @ShellMethod("Refill the account of a customer in the CoD backend with his id (login CUSTOMER_EMAIL CUSTOMER_PASSWORD)")
    public String refill( Long customerId, String creditCard, int amount) {
        String res = restTemplate.postForObject(BASE_URI + "/refill/"+customerId, new PaymentDTO(creditCard, amount), String.class);
        return res;
    }

    @ShellMethod("List all customers")
    public String customers() {
        return cliContext.getCustomers().toString();
    }

}