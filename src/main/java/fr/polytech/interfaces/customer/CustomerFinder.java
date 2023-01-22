package fr.polytech.interfaces.customer;

import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.MalformedCredentialsExceptions;
import fr.polytech.pojo.Customer;

import java.util.Set;

public interface CustomerFinder {
    /**
     * Check the given credentials
     * @param name The name of the customer
     * @param password The password of the customer
     * @throws BadCredentialsException The credentials are mismatched
     * @throws MalformedCredentialsExceptions The credentials are malformed
     */
    void checkCredentials(String name, String password) throws BadCredentialsException, MalformedCredentialsExceptions;
}
