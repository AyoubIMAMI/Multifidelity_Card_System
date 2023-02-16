package java.fr.unice.polytech.cod.unit;

import fr.polytech.interfaces.customer.CustomerRegistration;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RegistrationTest {

    @Autowired
    private CustomerRegistration customerRegistration;

    private String name;
    private String mail;
    private String password;

    @BeforeEach
    void setUp() {
        name = "Pierre";
        mail = "pierre@mail.com";
    }
}
