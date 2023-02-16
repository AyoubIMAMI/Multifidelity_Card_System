package fr.polytech.interfaces.customer;

import fr.polytech.exceptions.MailAlreadyUsedException;
import fr.polytech.pojo.Customer;

public interface CustomerModifier {
    void modifyUsername(Customer customer);
    void modifyMail(Customer customer) throws MailAlreadyUsedException;
    void modifyPassword(Customer customer);
}
