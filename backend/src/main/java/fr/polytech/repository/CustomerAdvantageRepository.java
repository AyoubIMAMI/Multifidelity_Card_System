package fr.polytech.repository;

import fr.polytech.entities.Customer;
import fr.polytech.entities.CustomerAdvantage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerAdvantageRepository extends JpaRepository<CustomerAdvantage, Long> {
    Optional<CustomerAdvantage> findByConsumerID(Long consumerID);
}
