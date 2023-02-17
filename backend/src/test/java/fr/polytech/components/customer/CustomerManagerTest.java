package fr.polytech.components.customer;

import fr.polytech.exceptions.MailAlreadyUsedException;
import fr.polytech.interfaces.customer.CustomerRegistration;
import fr.polytech.pojo.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CustomerManagerTest {

    @Autowired
    private CustomerRegistration customerRegistration;

    private String name;
    private String mail;
    private String password;

    @BeforeEach
    void setUp() {
        name = "Pierre";
        mail = "pierre@mail.com";
        password = "myPassword";
    }

    @Test
    void registration() throws MailAlreadyUsedException {
        Customer customer = customerRegistration.register(name, mail, password);
        assertEquals(name, customer.getName());
        assertEquals(mail, customer.getEmail());
        assertEquals(password, customer.getPassword());

        //assertTrue(customerRegistration.);
    }

}