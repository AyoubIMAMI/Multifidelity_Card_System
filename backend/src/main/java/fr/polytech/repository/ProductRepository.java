package fr.polytech.repository;

import fr.polytech.entities.Store;
import fr.polytech.entities.item.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNameAndStoreAndCashPrice(String name, Store store, double cashPrice);
}
