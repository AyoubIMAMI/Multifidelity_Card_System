package fr.polytech.components.customer;

import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.MailAlreadyUsedException;
import fr.polytech.exceptions.MalformedCredentialsExceptions;
import fr.polytech.interfaces.customer.CustomerExplorer;
import fr.polytech.interfaces.customer.CustomerFinder;
import fr.polytech.interfaces.customer.CustomerRegistration;
import fr.polytech.pojo.Customer;
import fr.polytech.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Component
public class CustomerManager implements CustomerRegistration, CustomerFinder, CustomerExplorer {

    CustomerRepository customerRepository;

    @Autowired
    CustomerManager(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer register(String name, String mail, String password) throws MailAlreadyUsedException {
        if(customerRepository.isMailAlreadyUsed(mail))
            throw new MailAlreadyUsedException();

        else {
            Customer customer = new Customer(name, mail, password);
            customerRepository.save(customer.getId(), customer);
            return customer;
        }
    }

    @Override
    public Customer findCustomerById(UUID id) throws CustomerNotFoundException {
        Optional<Customer> customer= customerRepository.findById(id);
        if (customer.isEmpty()) throw new CustomerNotFoundException();
        else return customer.get();
    }

    @Override
    public UUID checkCredentials(String email, String password) throws BadCredentialsException {
        Optional<Customer> customerCurrent=StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .filter(customer -> email.equals(customer.getEmail())&&password.equals(customer.getPassword())).findAny();
        if (customerCurrent.isEmpty()) throw new BadCredentialsException();
        else return customerCurrent.get().getId();
    }
}
