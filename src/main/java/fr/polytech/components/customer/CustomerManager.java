package fr.polytech.components.customer;

import fr.polytech.Repository.CustomerRepository;
import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.MailAlreadyUsedException;
import fr.polytech.exceptions.MalformedCredentialsExceptions;
import fr.polytech.interfaces.customer.CustomerExplorer;
import fr.polytech.interfaces.customer.CustomerFinder;
import fr.polytech.interfaces.customer.CustomerModifier;
import fr.polytech.interfaces.customer.CustomerRegistration;
import fr.polytech.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerManager implements CustomerRegistration, CustomerFinder {

    CustomerRepository customerRepository;

    @Autowired
    CustomerManager(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer register(String name, String mail, String password) throws MailAlreadyUsedException {
        try{
            Customer customer = new Customer(name, mail, password);
            customerRepository.save(customer, customer.getId());
            return customer;
        } catch (Exception exception) {
            throw new MailAlreadyUsedException();
        }
    }

    @Override
    public Customer findCustomerById(UUID id) throws CustomerNotFoundException {
        Optional<Customer> customer= customerRepository.findById(id);
        if (customer.isEmpty()) throw new CustomerNotFoundException();
        else return customer.get();
    }
}
