package fr.polytech.components.discount;

import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.interfaces.discount.DiscountExplorer;
import fr.polytech.interfaces.discount.DiscountModifier;
import fr.polytech.pojo.item.Discount;
import fr.polytech.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DiscountManager implements DiscountModifier, DiscountExplorer {

    DiscountRepository discountRepository;

    @Autowired
    public DiscountManager(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public Discount findDiscountById(UUID id) throws DiscountNotFoundException {
        return discountRepository.findById(id).orElseThrow(DiscountNotFoundException::new);
    }

    @Override
    public Iterable<Discount> findDiscountsByStore(UUID storeId) throws NoDiscountsFoundException {
        Iterable<Discount> discounts = discountRepository.findByStore(storeId);
        if(!discounts.iterator().hasNext()) {
            throw new NoDiscountsFoundException();
        }
        return discounts;
    }

    @Override
    public Iterable<Discount> findAllDiscounts() throws NoDiscountsFoundException {
        Iterable<Discount> discounts = discountRepository.findAll();
        if(!discounts.iterator().hasNext()) {
            throw new NoDiscountsFoundException();
        }
        return discounts;
    }

    @Override
    public Discount createDiscount(String name, UUID storeId, double cashPrice, int pointPrice){
        Discount discount = new Discount(name, storeId, cashPrice, pointPrice);
        discountRepository.save(discount.getId(), discount);
        return discount;
    }

    @Override
    public void modifyPointPrice(UUID id, int newPointPrice) throws DiscountNotFoundException {
        if(!discountRepository.existsById(id)) {
            throw new DiscountNotFoundException();
        }
        Discount discount = findDiscountById(id);
        discount.setPointPrice(newPointPrice);
        discountRepository.save(id,discount);
    }

    @Override
    public boolean deleteDiscount(UUID id) throws DiscountNotFoundException {
        if(!discountRepository.existsById(id)) {
            throw new DiscountNotFoundException();
        }
        discountRepository.deleteById(id);
        return discountRepository.existsById(id);
    }
}
