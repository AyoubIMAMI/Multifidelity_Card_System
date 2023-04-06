package fr.univcotedazur.simpletcfs.cli.commands;

import fr.univcotedazur.simpletcfs.cli.CliContext;
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
        if(message.charAt(0) == '!') {
            return "\n" + "\u001B[31m" + "      " + message.substring(1) + "\u001B[0m";
        } else {
            return "\n" + "\u001B[34m" + message + "\u001B[0m";
        }
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
    public void clearCart() {
        cliContext.getCart().clear();
    }

    @ShellMethod("Add a Product Item to cart (add-product QUANTITY PRODUCT_NAME STORE_ID CASH_PRICE")
    public CliItem addProduct(int quantity, String productName, Long storeId, double cashPrice) {
        CliItem item = new CliItem(quantity, new CliProduct(productName, storeId, cashPrice));
        cliContext.getCart().add(item);
        return item;
    }

    @ShellMethod("Add a Discount Item to cart by Discount Id (add-discount-by-id QUANTITY DISCOUNT_ID")
    public CliItem addDiscount(int quantity, Long discountId) {
        CliItem item = new CliItem(quantity, cliContext.getDiscounts().get(discountId));
        cliContext.getCart().add(item);
        return item;
    }
}
