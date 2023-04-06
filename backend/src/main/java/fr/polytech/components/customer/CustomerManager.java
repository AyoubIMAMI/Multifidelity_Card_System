package fr.polytech.components.customer;

import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.MailAlreadyUsedException;
import fr.polytech.interfaces.customer.CustomerExplorer;
import fr.polytech.interfaces.customer.CustomerFinder;
import fr.polytech.interfaces.customer.CustomerRegistration;
import fr.polytech.entities.Customer;
import fr.polytech.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Transactional
@Component
public class CustomerManager implements CustomerRegistration, CustomerFinder, CustomerExplorer {

    CustomerRepository customerRepository;

    @Autowired
    CustomerManager(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer register(String name, String mail, String password) throws MailAlreadyUsedException {
        if(isMailAlreadyUsed(mail))
            throw new MailAlreadyUsedException(mail);

        Customer customer = new Customer(name, mail, password);
        return customerRepository.save(customer);
    }

    @Override
    public Customer registerNewPlate(Long customerID, String licensePlate) throws CustomerNotFoundException {
        Optional<Customer> customer= customerRepository.findById(customerID);
        if (customer.isEmpty()) throw new CustomerNotFoundException();
        else{
            customer.get().getFidelityAccount().setLicencePlate(licensePlate);
            return(customerRepository.save(customer.get()));
        }
    }

    @Override
    public Customer findCustomerById(Long id) throws CustomerNotFoundException {
        Optional<Customer> customer= customerRepository.findById(id);
        if (customer.isEmpty()) throw new CustomerNotFoundException(id);
        else return customer.get();
    }

    @Override
    public Long checkCredentials(String email, String password) throws BadCredentialsException {
        Optional<Customer> customerCurrent=StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .filter(customer -> email.equals(customer.getEmail())&&password.equals(customer.getPassword())).findAny();
        if (customerCurrent.isEmpty()) throw new BadCredentialsException();
        else return customerCurrent.get().getId();
    }
    private boolean isMailAlreadyUsed(String mail) {
        List<String> mails = new ArrayList<>();
        Iterable<Customer> storage = customerRepository.findAll();
        storage.forEach(customer -> mails.add(customer.getEmail()));
        return mails.contains(mail);
    }
}
