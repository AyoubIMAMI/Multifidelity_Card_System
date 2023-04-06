package fr.polytech.interfaces.discount;

import fr.polytech.entities.item.Discount;
import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;

import java.util.List;

public interface DiscountExplorer {
    Discount findDiscountById(Long id) throws DiscountNotFoundException;

    Discount findDiscountByName(String name) throws DiscountNotFoundException;

    List<Discount> findDiscountsByStore(Long storeId) throws DiscountNotFoundException;

    List<Discount> findAllDiscounts() throws NoDiscountsFoundException ;

}
