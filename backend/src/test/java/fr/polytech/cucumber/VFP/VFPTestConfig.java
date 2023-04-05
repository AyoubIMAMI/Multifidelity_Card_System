package fr.polytech.cucumber.VFP;

import fr.polytech.interfaces.Parking;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@CucumberContextConfiguration
@SpringBootTest
public class VFPTestConfig {
    @MockBean
    private Parking parking;
}




