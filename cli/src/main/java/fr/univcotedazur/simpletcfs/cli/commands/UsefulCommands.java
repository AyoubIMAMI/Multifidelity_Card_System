package fr.univcotedazur.simpletcfs.cli.commands;

import fr.univcotedazur.simpletcfs.cli.CliContext;
import fr.univcotedazur.simpletcfs.cli.model.CliDiscount;
import fr.univcotedazur.simpletcfs.cli.model.CliItem;
import fr.univcotedazur.simpletcfs.cli.model.CliProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class UsefulCommands {

    @Autowired
    private CliContext cliContext;

    @ShellMethod("Print message (print MESSAGE)")
    public String print(String message) {
        return "\n" + "\u001B[34m" + message + "\u001B[0m";
    }

    @ShellMethod("Clear context (clear-ctx)")
    public void clearCtx() {
        cliContext.clearAll();
    }

    @ShellMethod("Display items in cart")
    public String cart() {
        StringBuilder cartStr = new StringBuilder("\nItems in cart:\n");
        for (CliItem item : cliContext.getCart()) {
            cartStr.append(item).append("\n");
        }
        return cartStr.toString();
    }

    @ShellMethod("Clear items in cart")
    public String clearCart() {
        StringBuilder cartStr = new StringBuilder("Items in cart:\n");
        for (CliItem item : cliContext.getCart()) {
            cartStr.append(item).append("\n");
        }
        return cartStr.toString();
    }

    @ShellMethod("Add a Product Item to cart (add-product QUANTITY PRODUCT_NAME STORE_ID CASH_PRICE")
    public CliItem addProduct(int quantity, String productName, Long storeId, double cashPrice) {
        CliItem item = new CliItem(quantity, new CliProduct(productName, storeId, cashPrice));
        cliContext.getCart().add(item);
        return item;
    }

    @ShellMethod("Add a Discount Item to cart (add-product QUANTITY PRODUCT_NAME STORE_ID POINT_PRICE")
    public CliItem addDiscount(int quantity, String productName, Long storeId, int pointPrice) {
        CliItem item = new CliItem(quantity, new CliDiscount(productName, storeId, pointPrice));
        cliContext.getCart().add(item);
        return item;
    }

    @ShellMethod("Add a Discount Item to cart by Discount Id (add-discount-by-id QUANTITY DISCOUNT_ID")
    public CliItem addDiscountById(int quantity, Long discountId) {
        CliItem item = new CliItem(quantity, cliContext.getDiscounts().get(discountId));
        cliContext.getCart().add(item);
        return item;
    }
}
