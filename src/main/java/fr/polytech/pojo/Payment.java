package fr.polytech.pojo;

import fr.polytech.pojo.item.Product;

import java.util.Set;

public class Payment {
    private int id;
    private int clientId;
    private int storeId;
    private int employeeId;

    private Set<Product> shoppingList;

    private boolean isSettled;
}
