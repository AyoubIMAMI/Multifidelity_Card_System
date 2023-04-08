package fr.polytech.interfaces.customer;

import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.MailAlreadyUsedException;
import fr.polytech.entities.Customer;

public interface CustomerRegistration {

    /**
     * Register a new user
     * @param name user's name
     * @param mail user's mail
     * @param password user's password
     * @return the new Customer registered
     * @throws MailAlreadyUsedException threw if the mail has already been used
     */
    Customer register(String name, String mail, String password) throws MailAlreadyUsedException;

    /**
     * Register a car license plate
     * @param customerID customer ID
     * @param licensePlate car license plate
     * @return the customer involved int this registration
     * @throws CustomerNotFoundException threw if the customer is not found
     */
    Customer registerNewPlate(Long customerID,String licensePlate) throws CustomerNotFoundException;
}
