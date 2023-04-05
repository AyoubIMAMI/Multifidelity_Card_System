package fr.univcotedazur.simpletcfs.cli;

import fr.univcotedazur.simpletcfs.cli.model.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CliContext {

    private Map<Long, CliCustomer> customers;
    private Map<Long, CliDiscount> discounts;
    private Map<Long, CliPayment> payments;
    private Map<Long, CliStore> stores;
    private Set<CliItem> cart;

    public Map<Long, CliCustomer> getCustomers() {
        return customers;
    }
    public Map<Long, CliDiscount>getDiscounts() {
        return discounts;
    }
    public Map<Long, CliPayment> getPayments() {
        return payments;
    }
    public Map<Long, CliStore> getStores() {
        return stores;
    }
    public Set<CliItem> getCart() {
        return cart;
    }

    public CliContext() {
        customers = new HashMap<>();
        discounts = new HashMap<>();
        payments = new HashMap<>();
        stores = new HashMap<>();
        cart = new HashSet<>();
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

        String storesString = "Stores" + stores.keySet().stream()
                .map(key -> key + "=" + stores.get(key))
                .collect(Collectors.joining(", ", "{", "}"));

        return customersString + "\n" + discountsString + "\n" + paymentsString + "\n" + storesString;
    }

    public void clearAll() {
        customers.clear();
        discounts.clear();
        payments.clear();
        stores.clear();
        cart.clear();
    }
}
