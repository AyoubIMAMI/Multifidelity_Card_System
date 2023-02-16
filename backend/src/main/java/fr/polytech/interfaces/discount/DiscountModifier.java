package fr.polytech.interfaces.discount;

import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.pojo.item.Discount;

public interface DiscountModifier {
    void modifyPointPrice(Discount discount, int newPointPrice) throws DiscountNotFoundException;
}
