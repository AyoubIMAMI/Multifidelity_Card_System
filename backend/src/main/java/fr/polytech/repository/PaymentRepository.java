package fr.polytech.repository;


import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByCustomer(Customer customer);
    List<Payment> findByStore(Store store);
    List<Payment> findAllByTransactionDateAfter(Date date);
}
