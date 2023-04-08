package fr.polytech.interfaces.discount;

import fr.polytech.entities.item.Discount;
import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.exceptions.store.StoreNotFoundException;

import java.util.List;

public interface DiscountExplorer {

    /**
     * Find a discount by its ID
     * @param id the store ID
     * @return the Discount
     * @throws DiscountNotFoundException threw if the discount has not been found
     */
    Discount findDiscountById(Long id) throws DiscountNotFoundException;

    /**
     * Find a discount by its name
     * @param name the discount name
     * @return the Discount
     * @throws DiscountNotFoundException threw if the discount has not been found
     */
    Discount findDiscountByName(String name) throws DiscountNotFoundException;

    /**
     * Find discounts by their store
     * @param storeID the store ID
     * @return the Discount list
     * @throws DiscountNotFoundException threw if the discount has not been found
     * @throws StoreNotFoundException threw if the store has not been found
     */
    List<Discount> findDiscountsByStore(Long storeID) throws DiscountNotFoundException, StoreNotFoundException;

    /**
     * Find all discounts
     * @return the Discount list
     * @throws NoDiscountsFoundException threw if there are no discounts found
     */
    List<Discount> findAllDiscounts() throws NoDiscountsFoundException ;

}
