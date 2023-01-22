package pojo;

import pojo.item.Product;

import java.util.Set;

public class Payment {
    private int clientId;
    private int storeId;

    private Set<Product> shoppingList;

    private boolean isSettled;
}
