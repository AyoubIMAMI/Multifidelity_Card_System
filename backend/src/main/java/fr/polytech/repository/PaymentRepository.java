package fr.polytech.repository;


import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByCustomer(Customer customer);
    Optional<Payment> findByStore(Store store);

}
