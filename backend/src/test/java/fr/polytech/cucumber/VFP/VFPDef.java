package fr.polytech.cucumber.VFP;

import fr.polytech.connectors.externaldto.BankTransactionDTO;
import fr.polytech.connectors.externaldto.ParkingTransactionDTO;
import fr.polytech.entities.Advantage;
import fr.polytech.entities.Customer;
import fr.polytech.entities.CustomerAdvantage;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.MailAlreadyUsedException;
import fr.polytech.exceptions.advantage.AdvantageAlreadyConsumedException;
import fr.polytech.exceptions.advantage.NoAdvantageFoundException;
import fr.polytech.exceptions.advantage.VFPNotFoundException;
import fr.polytech.interfaces.Parking;
import fr.polytech.interfaces.advantage.VFPTransaction;
import fr.polytech.interfaces.customer.CustomerRegistration;
import fr.polytech.repository.AdvantageRepository;
import fr.polytech.repository.CustomerAdvantageRepository;
import fr.polytech.repository.CustomerRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class VFPDef {
    @Autowired
    AdvantageRepository advantageRepository;
    @Autowired
    VFPTransaction vfpTransaction;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    Parking parkingMock;
    Advantage advantageParking;
    @Autowired
    CustomerRegistration customerRegistration;
    @Autowired
    CustomerAdvantageRepository customerAdvantageRepository;
    Exception exception;

    Customer customer=new Customer("leo","leo@gmail.com","leo123");

    @Before
    public void setUp() throws Exception {
        customerAdvantageRepository.deleteAll();
        customerRepository.deleteAll();
        exception=null;
        // Mocking the bank proxy
        when(parkingMock.getParkingPlace(any(ParkingTransactionDTO.class))).thenAnswer((Answer<Boolean>) invocation -> {
            return true;
        });

    }

    @Given("an advantage parking")
    public void anAdvantageParking() {
        advantageParking=advantageRepository.save(new Advantage("parking"));
    }

    @And("a user with VFP account")
    public void aUserWithVFPAccount() {
        customer=customerRepository.save(customer);
        customerAdvantageRepository.save(new CustomerAdvantage(customer.getId()));
    }

    @When("a user set his plate")
    public void aUserSetHisPlate() {
        try {
            customerRegistration.registerNewPlate(customer.getId(), "plate");
        } catch (CustomerNotFoundException e) {
            exception=e;
        }

    }
    @When("we try to use the parking advantage")
    public void weTryToUseTheParkingAdvantage() {
        try {
            vfpTransaction.tryUseParkingAdvantage(customer.getId(),advantageParking.getId(),Long.valueOf(12));
        } catch (Exception e) {
            exception=e;
        }
    }

    @And("he tries to use a not valid AdvantageID")
    public void heTriesToUseANotValidAdvantageID() {
        try {
            vfpTransaction.tryUseParkingAdvantage(customer.getId(),Long.valueOf(10),Long.valueOf(12));
        } catch (Exception e) {
            exception=e;
        }
    }

    @Then("we use it")
    public void weUseIt() {
        assertNull(exception);
    }

    @And("the date is set in the database")
    public void theDateIsSetInTheDatabase() {
        CustomerAdvantage customerAdvantage = customerAdvantageRepository.findByConsumerID(customer.getId()).get();
        assertTrue(customerAdvantage.getAdvantageDate(advantageParking.getId()).isPresent());
    }

    @Then("it fails with NoAdvantageFoundException Exception")
    public void itFailsWithNoAdvantageFoundExceptionException() {
        assertTrue(exception instanceof NoAdvantageFoundException);
    }

    @Given("a user with no vfp")
    public void aUserWithNoVfp() throws MailAlreadyUsedException {
        customer=customerRegistration.register("Antoine","antoine@gmail.com","AntoineDu652345532255");
    }

    @Then("it fails with VFPNotFoundException Exception")
    public void itFailsWithVFPNotFoundExceptionException() {
        assertTrue(exception instanceof VFPNotFoundException);
    }

    @Then("it fails with AdvantageAlreadyConsumedException")
    public void itFailsWithAdvantageAlreadyConsumedException() {
        assertTrue(exception instanceof AdvantageAlreadyConsumedException);
    }
}
