package fr.polytech.repository;

import fr.polytech.pojo.Customer;
import fr.polytech.pojo.Payment;
import fr.polytech.pojo.structure.Store;
import fr.polytech.repository.Rep.BasicRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class PaymentRepository extends BasicRepositoryImpl<Payment, UUID> {

    public Iterable<Payment> findByCustomer(Customer customer) {
        return storage.values().stream()
                .filter(p -> p.getCustomer().equals(customer))
                .collect(Collectors.toList());
    }

    public Iterable<Payment> findByStore(Store store) {
        return storage.values().stream()
                .filter(p -> p.getStore().equals(store))
                .collect(Collectors.toList());
    }

}
