package fr.polytech.pojo;

import ch.qos.logback.core.net.server.Client;
import fr.polytech.pojo.item.Product;
import fr.polytech.pojo.structure.Store;

import java.util.Set;

public class Payment {
    private int id;
    private Client client;
    private Store store;
    private int employeeId;

    private Set<Product> shoppingList;

    private boolean isSettled;
}
