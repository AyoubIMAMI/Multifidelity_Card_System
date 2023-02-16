package fr.polytech.pojo;

import ch.qos.logback.core.net.server.Client;
import fr.polytech.components.customer.CustomerFidelityManager;
import fr.polytech.pojo.item.Product;
import fr.polytech.pojo.structure.Employee;
import fr.polytech.pojo.structure.Store;

import java.util.Set;

public class Payment {
    private int id;
    private Customer customer;
    private Store store;
    private Employee employee;
    private Set<Product> shoppingList;
    private boolean isSettled;

    public boolean isSettled() {
        return isSettled;
    }
}
