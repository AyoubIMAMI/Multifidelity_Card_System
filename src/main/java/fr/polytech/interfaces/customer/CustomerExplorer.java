package fr.polytech.interfaces.customer;

import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.MalformedCredentialsExceptions;

import java.util.UUID;

public interface CustomerExplorer {
    /**
     * Check the given credentials
     * @param email The name of the customer
     * @param password The password of the customer
     * @throws BadCredentialsException The credentials are mismatched
     */
    UUID checkCredentials(String email, String password) throws BadCredentialsException;
}
