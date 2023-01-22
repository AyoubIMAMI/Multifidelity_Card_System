package fr.polytech.interfaces.customer;

import fr.polytech.pojo.Customer;

public interface CustomerExplorer {
    Customer findCustomerById(int id);
}
