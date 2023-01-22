package fr.polytech.interfaces.customer;

import fr.polytech.pojo.Customer;

public interface CustomerModifier {
    void modifyUsername(Customer customer);
    void modifyMail(Customer customer);
    void modifyPassword(Customer customer);
}
