package fr.polytech.interfaces.discount;

import fr.polytech.entities.item.Discount;
import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.exceptions.payment.NegativeAmountException;
import fr.polytech.exceptions.store.StoreNotFoundException;

public interface DiscountModifier {

    /**
     * Create a discount
     * @param name discount name
     * @param storeId store id
     * @param pointPrice points which can be earned
     * @return the Discount
     * @throws NegativeAmountException threw if the amount is negative
     * @throws StoreNotFoundException threw is the store has not been found
     */
    Discount createDiscount(String name, Long storeId, int pointPrice) throws NegativeAmountException, StoreNotFoundException;

    /**
     * Modify the discounts points price
     * @param id the discount id
     * @param newPointPrice new amount of points
     * @return the updated Discount
     * @throws DiscountNotFoundException threw if the discount has not been found
     */
    Discount modifyPointPrice(Long id, int newPointPrice) throws DiscountNotFoundException;

    /**
     * Delete a discount
     * @param id the discount id
     * @return a boolean according to "does the discount exist?" (false means "has been deleted")
     * @throws DiscountNotFoundException threw if the discount has not been found
     */
    boolean deleteDiscount(Long id) throws DiscountNotFoundException;
}
