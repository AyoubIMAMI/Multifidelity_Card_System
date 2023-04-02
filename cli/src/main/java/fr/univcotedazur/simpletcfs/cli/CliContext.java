package fr.univcotedazur.simpletcfs.cli;

import fr.univcotedazur.simpletcfs.cli.model.CliCustomer;
import fr.univcotedazur.simpletcfs.cli.model.CliDiscount;
import fr.univcotedazur.simpletcfs.cli.model.CliPayment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CliContext {

    private Map<Long, CliCustomer> customers;
    private Map<Long, CliDiscount> discounts;
    private Map<Long, CliPayment> payments;

    public Map<Long, CliCustomer> getCustomers() {
        return customers;
    }
    public Map<Long, CliDiscount>getDiscounts() {
        return discounts;
    }

    public Map<Long, CliPayment> getPayments() {
        return payments;
    }

    public CliContext() {
        customers = new HashMap<>();
        discounts = new HashMap<>();
        payments = new HashMap<>();
    }

    @Override
    public String toString() {
        String customersString = "Customers" + customers.keySet().stream()
                .map(key -> key + "=" + customers.get(key))
                .collect(Collectors.joining(", ", "{", "}"));

        String discountsString = "Discounts" + discounts.keySet().stream()
                .map(key -> key + "=" + discounts.get(key))
                .collect(Collectors.joining(", ", "{", "}"));

        String paymentsString = "Payments" + payments.keySet().stream()
                .map(key -> key + "=" + payments.get(key))
                .collect(Collectors.joining(", ", "{", "}"));

        return customersString + "\n" + discountsString + "\n" + paymentsString;
    }


}
