package fr.polytech.interfaces.customer;

import fr.polytech.exceptions.LoginErrorException;
import fr.polytech.exceptions.MailAlreadyUsedException;
import fr.polytech.pojo.Customer;

public interface CustomerRegistration {
    Customer register(String name, String mail, String password) throws MailAlreadyUsedException;
    //return the token as a String
    String login(String mail, String password) throws LoginErrorException;
}
