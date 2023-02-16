package fr.polytech.repository;

import fr.polytech.repository.Rep.BasicRepositoryImpl;
import fr.polytech.pojo.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CustomerRepository extends BasicRepositoryImpl<Customer, UUID> {

    public boolean isMailAlreadyUsed(String mail) {
        List<String> mails = new ArrayList<>();
        Iterable<Customer> storage = findAll();
        storage.forEach(customer -> mails.add(customer.getEmail()));
        return mails.contains(mail);
    }
}
