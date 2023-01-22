package fr.polytech.interfaces.store;

import fr.polytech.pojo.item.Discount;
import fr.polytech.pojo.structure.Store;

public interface DiscountExplorer {
    Discount findDiscountByName(Store store, String discountName);
}
