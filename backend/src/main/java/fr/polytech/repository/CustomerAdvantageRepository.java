package fr.polytech.repository;

import fr.polytech.entities.Customer;
import fr.polytech.entities.VFPAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerAdvantageRepository extends JpaRepository<VFPAccount, Long> {
    Optional<VFPAccount> findByCustomer(Customer customer);
}
