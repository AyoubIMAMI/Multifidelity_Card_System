package fr.polytech.repository;

import fr.polytech.repository.Rep.BasicRepositoryImpl;
import fr.polytech.pojo.Customer;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CustomerRepository extends BasicRepositoryImpl<Customer, UUID> {

}
