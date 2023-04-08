package fr.polytech.interfaces.discount;

import fr.polytech.entities.Store;
import fr.polytech.entities.item.Discount;
import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.exceptions.store.StoreNotFoundException;

import java.util.List;

public interface DiscountExplorer {
    Discount findDiscountById(Long id) throws DiscountNotFoundException;

    Discount findDiscountByName(String name) throws DiscountNotFoundException;

    List<Discount> findDiscountsByStore(Long storeID) throws DiscountNotFoundException, StoreNotFoundException;

    List<Discount> findAllDiscounts() throws NoDiscountsFoundException ;

}
