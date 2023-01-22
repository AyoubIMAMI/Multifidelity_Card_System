package fr.polytech.interfaces.customer;

import fr.polytech.exceptions.MailAlreadyUsed;
import fr.polytech.pojo.Customer;

public interface CustomerModifier {
    void modifyUsername(Customer customer);
    void modifyMail(Customer customer) throws MailAlreadyUsed;
    void modifyPassword(Customer customer);
}
