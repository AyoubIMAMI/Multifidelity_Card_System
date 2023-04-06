package fr.polytech.cucumber.clientAccount;

import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.MailAlreadyUsedException;
import fr.polytech.interfaces.customer.CustomerExplorer;
import fr.polytech.interfaces.customer.CustomerRegistration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ConnectingToAccount {

    @Autowired
    private CustomerRegistration customerRegistration;
    @Autowired
    private CustomerExplorer customerExplorer;
    String username;
    String email;
    String password;
    Exception exception;

    @Given("a user named {string} with {string} mail and this password {string}")
    public void a_user_named_with_mail_and_this_password(String username, String email, String password) {
        this.username=username;
        this.email=email;
        this.password=password;
    }

    @When("the user registers")
    public void theUserRegisters() {
        try {
            customerRegistration.register(username,email,password);
        } catch (MailAlreadyUsedException e) {
            exception=e;
        }
    }

    @Then("he can login and get his ID with {string} mail and this password {string}")
    public void heCanLoginAndGetHisIDWithMailAndThisPassword(String email, String password) {
            assertDoesNotThrow(()->customerExplorer.checkCredentials(email, password));
    }


    @Then("he can't login and get his ID with {string} mail and this password {string}")
    public void heCanTLoginAndGetHisIDWithMailAndThisPassword(String email, String password) {
        assertThrows(BadCredentialsException.class, ()->customerExplorer.checkCredentials(email, password));

    }

    @And("an other user registers named {string} with {string} mail and this password {string}")
    public void anOtherUserRegistersNamedWithMailAndThisPassword(String username, String email, String password) {
        try {
            customerRegistration.register(username,email,password);
        } catch (MailAlreadyUsedException e) {
            exception=e;
        }
    }

    @Then("he trigger a UserAlreadyExistingException")
    public void heTriggerAUserAlreadyExistingException() {
        assertTrue(exception instanceof MailAlreadyUsedException);
    }
}
