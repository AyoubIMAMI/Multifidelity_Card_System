package fr.polytech.interfaces.customer;

import fr.polytech.exceptions.MailAlreadyUsedException;
import fr.polytech.entities.Customer;

import java.util.Date;

public interface CustomerModifier {
    Date modifyUsername(Customer customer);
    Date modifyMail(Customer customer) throws MailAlreadyUsedException;
    Date modifyPassword(Customer customer);
}
