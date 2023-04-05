package fr.polytech.components.discount;

import fr.polytech.entities.item.Discount;
import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.interfaces.discount.DiscountExplorer;
import fr.polytech.interfaces.discount.DiscountModifier;
import fr.polytech.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class DiscountManager implements DiscountModifier, DiscountExplorer {

    DiscountRepository discountRepository;

    @Autowired
    public DiscountManager(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public Discount findDiscountById(Long id) throws DiscountNotFoundException {
        return discountRepository.findById(id).orElseThrow(DiscountNotFoundException::new);
    }

    @Override
    public Discount findDiscountByName(String name) throws DiscountNotFoundException {
        return discountRepository.findDiscountByName(name).orElseThrow(DiscountNotFoundException::new);
    }

    @Override
    public List<Discount> findDiscountsByStore(Long storeId) throws NoDiscountsFoundException {
        List<Discount> discounts = discountRepository.findByStoreId(storeId);
        if(discounts.isEmpty()) {
            throw new NoDiscountsFoundException();
        }
        return discounts;
    }

    @Override
    public List<Discount> findAllDiscounts() throws NoDiscountsFoundException {
        List<Discount> discounts = discountRepository.findAll();
        if(discounts.isEmpty()) {
            throw new NoDiscountsFoundException();
        }
        return discounts;
    }

    @Override
    public Discount createDiscount(String name, Long storeId, int pointPrice){
        Discount discount = new Discount(name, storeId, pointPrice);
        return discountRepository.save(discount);
    }

    @Override
    public Discount modifyPointPrice(Long id, int newPointPrice) throws DiscountNotFoundException {
        Discount discount = findDiscountById(id);
        discount.setPointPrice(newPointPrice);
        return discountRepository.save(discount);
    }

    @Override
    public boolean deleteDiscount(Long id) throws DiscountNotFoundException {
        if(discountRepository.findById(id).isEmpty()) {
            throw new DiscountNotFoundException();
        }
        discountRepository.deleteById(id);
        return discountRepository.existsById(id);
    }
}
