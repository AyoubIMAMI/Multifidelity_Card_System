package fr.polytech.interfaces.customer;

import fr.polytech.exceptions.LoginError;
import fr.polytech.exceptions.MailAlreadyUsed;
import fr.polytech.pojo.Customer;

public interface CustomerRegistration {
    Customer register(String name, String mail, String password) throws MailAlreadyUsed;
    //return the token as a String
    String login(String mail, String password) throws LoginError;
}
