package fr.polytech.components.discount;

import fr.polytech.interfaces.discount.DiscountExplorer;
import fr.polytech.interfaces.discount.DiscountModifier;
import fr.polytech.pojo.item.Discount;
import fr.polytech.pojo.structure.Store;

public class DiscountManager implements DiscountExplorer, DiscountModifier {
    @Override
    public Discount findDiscountByName(Store store, String discountName) {
        return null;
    }
}
