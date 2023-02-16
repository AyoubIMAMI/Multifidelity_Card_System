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

import java.util.Set;

@Component
public class CustomerManager implements CustomerRegistration, CustomerModifier, CustomerFinder, CustomerExplorer {

    CustomerRepository customerRepository;

    @Autowired
    CustomerManager(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer register(String name, String mail, String password) throws MailAlreadyUsedException {
        /*try{
            Customer customer = new Customer(name, mail, password);
        }*/
        return null;
    }

    @Override
    public Customer findCustomerById(int id) throws CustomerNotFoundException {
        return null;
    }

    @Override
    public Set<Customer> findCustomersByName(String name) {
        return null;
    }

    @Override
    public void checkCredentials(String name, String password) throws BadCredentialsException, MalformedCredentialsExceptions {

    }

    @Override
    public void modifyUsername(Customer customer) {

    }

    @Override
    public void modifyMail(Customer customer) throws MailAlreadyUsedException {

    }

    @Override
    public void modifyPassword(Customer customer) {

    }
}
