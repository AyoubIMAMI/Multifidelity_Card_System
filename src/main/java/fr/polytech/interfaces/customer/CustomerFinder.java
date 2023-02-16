package fr.polytech.interfaces.customer;

import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.pojo.Customer;

import java.util.Set;
import java.util.UUID;

public interface CustomerFinder {
    /**
     * For a given id, return the customer with the given id
     * @param id The id of the customer to find
     * @return The customer associated with the given id
     * @throws CustomerNotFoundException The customer wasn't found
     */
    Customer findCustomerById(UUID id) throws CustomerNotFoundException;

    /**
     * For a given id, return all the customers with the given name
     * @param name The name of the customer to find
     * @return The customers associated with the given name
     */
    Set<Customer> findCustomersByName(String name);
}
