package fr.polytech.repository;

import fr.polytech.pojo.item.Discount;
import fr.polytech.repository.Rep.BasicRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class DiscountRepository extends BasicRepositoryImpl<Discount, UUID> {

    public Iterable<Discount> findByStore(UUID storeId) {
        return storage.values().stream()
                .filter(p -> p.getStoreId().equals(storeId))
                .collect(Collectors.toList());
    }
}
