package fr.polytech.cucumber.payment;

import fr.polytech.interfaces.payment.Bank;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@CucumberContextConfiguration
@SpringBootTest
public class PaymentTestConfig {
    @MockBean
    private Bank bankMock;
}




