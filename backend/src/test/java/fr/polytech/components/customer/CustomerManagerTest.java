package fr.polytech.components.customer;

import fr.polytech.exceptions.MailAlreadyUsedException;
import fr.polytech.interfaces.customer.CustomerRegistration;
import fr.polytech.pojo.Customer;
import fr.polytech.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerManagerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerRegistration customerRegistration;

    private String name;
    private String mail;
    private String password;


    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
        name = "Pierre";
        mail = "pierre@mail.com";
        password = "myPassword";
    }

    @Test
    void registrationTest() throws MailAlreadyUsedException {
        Customer customer = customerRegistration.register(name, mail, password);

        assertEquals(name, customer.getName());
        assertEquals(mail, customer.getEmail());
        assertEquals(password, customer.getPassword());

        assertTrue(customerRepository.existsById(customer.getId()));
        assertEquals(1, customerRepository.count());
        assertEquals(customer, customerRepository.findById(customer.getId()).get());
    }

    @Test
    void registrationMailAlreadyUsedTest() throws MailAlreadyUsedException {
        customerRegistration.register(name, mail, password);
        Customer jeanPierre = new Customer("Jean-Pierre", mail, "anotherPassword");

        assertThrows(MailAlreadyUsedException.class,
                () -> customerRegistration.register("Jean-Pierre", mail, "anotherPassword"));

        assertFalse(customerRepository.existsById(jeanPierre.getId()));
        assertEquals(1, customerRepository.count());
        assertEquals(Optional.empty(), customerRepository.findById(jeanPierre.getId()));
    }

}