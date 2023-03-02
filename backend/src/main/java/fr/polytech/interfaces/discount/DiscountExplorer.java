package fr.polytech.interfaces.discount;

import fr.polytech.exceptions.NotEnoughPermissionException;
import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.structure.Store;

import java.util.Set;

public interface DiscountExplorer {
    Discount findDiscountByName(Store store, String discountName) throws DiscountNotFoundException;
    Set<Discount> getAllDiscount() throws NotEnoughPermissionException;
}
