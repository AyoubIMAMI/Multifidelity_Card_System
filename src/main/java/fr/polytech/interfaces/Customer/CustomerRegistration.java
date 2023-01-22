package fr.polytech.interfaces.Customer;

import fr.polytech.pojo.Customer;

public interface CustomerRegistration {
    Customer register(String name, String mail, String password);
    //return the token as a String
    String login(String mail, String password);
}
