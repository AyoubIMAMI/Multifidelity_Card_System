package fr.univcotedazur.simpletcfs.cli;

import fr.univcotedazur.simpletcfs.cli.model.CliCustomer;
import fr.univcotedazur.simpletcfs.cli.model.DiscountDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CliContext {

    private Map<Long, CliCustomer> customers;
    private Map<Long, DiscountDTO> discounts;

    public Map<Long, CliCustomer> getCustomers() {
        return customers;
    }
    public Map<Long, DiscountDTO>getDiscounts() {
        return discounts;
    }

    public CliContext() {
        customers = new HashMap<>();
        discounts = new HashMap<>();
    }

    @Override
    public String toString() {
        String customersString = "Customers" + customers.keySet().stream()
                .map(key -> key + "=" + customers.get(key))
                .collect(Collectors.joining(", ", "{", "}"));

        String discountsString = "Discounts" + discounts.keySet().stream()
                .map(key -> key + "=" + discounts.get(key))
                .collect(Collectors.joining(", ", "{", "}"));

        return customersString + "\n" + discountsString;
    }


}
