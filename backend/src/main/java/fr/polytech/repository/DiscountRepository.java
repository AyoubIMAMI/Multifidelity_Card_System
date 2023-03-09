package fr.polytech.repository;

import fr.polytech.entities.Customer;
import fr.polytech.entities.item.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    Optional<Discount> findByStoreId(long storeId);
}
