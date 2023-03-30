package fr.univcotedazur.simpletcfs.cli;

import fr.univcotedazur.simpletcfs.cli.model.CliCustomer;
import fr.univcotedazur.simpletcfs.cli.model.DiscountDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CliContext {

    private Map<String, CliCustomer> customers;
    private Map<String, DiscountDTO> discounts;


    public Map<String, CliCustomer> getCustomers() {
        return customers;
    }
    public Map<String, DiscountDTO>getDiscounts() {return discounts;}

    public CliContext() {
        customers = new HashMap<>();
        discounts = new HashMap<>();
    }

    @Override
    public String toString() {
        return customers.keySet().stream()
                .map(key -> key + "=" + customers.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
    }


}
