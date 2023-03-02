package fr.polytech.interfaces.customer;

import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.entities.Customer;

public interface CustomerFinder {
    /**
     * For a given id, return the customer with the given id
     * @param id The id of the customer to find
     * @return The customer associated with the given id
     * @throws CustomerNotFoundException The customer wasn't found
     */
    Customer findCustomerById(Long id) throws CustomerNotFoundException;

}
