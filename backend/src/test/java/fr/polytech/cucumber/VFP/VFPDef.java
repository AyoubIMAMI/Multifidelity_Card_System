package fr.polytech.cucumber.VFP;

import fr.polytech.entities.Advantage;
import fr.polytech.interfaces.advantage.VFPTransaction;
import fr.polytech.repository.AdvantageRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VFPDef {
    @Autowired
    AdvantageRepository advantageRepository;
    @Autowired
    VFPTransaction vfpTransaction;
    

    @Given("an advantage parking")
    public void anAdvantageParking() {
        advantageRepository.save(new Advantage("parking"));

    }

    @And("a user with VFP account")
    public void aUserWithVFPAccount() {

    }

    @When("we try to use the parking advantage")
    public void weTryToUseTheParkingAdvantage() {

    }

    @Then("we use it")
    public void weUseIt() {
    }

    @When("a user set his plate")
    public void aUserSetHisPlate() {
    }
}
