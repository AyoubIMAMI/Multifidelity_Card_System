package fr.polytech.interfaces.customer;

import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.MailAlreadyUsedException;
import fr.polytech.pojo.Customer;

public interface CustomerRegistration {
    Customer register(String name, String mail, String password) throws MailAlreadyUsedException;
}
