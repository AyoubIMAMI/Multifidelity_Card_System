package fr.polytech.interfaces.advantage;

import fr.polytech.entities.Customer;
import fr.polytech.entities.CustomerAdvantage;
import fr.polytech.exceptions.advantage.AdvantageAlreadyConsumedException;
import fr.polytech.exceptions.advantage.NoAdvantageFoundException;
import fr.polytech.exceptions.advantage.VFPNotFoundException;

import java.util.Optional;

public interface AdvantageCustomer {
    CustomerAdvantage addCustomerToProgramVFP(Customer consumer);

    Optional<CustomerAdvantage> findCustomerAdvantageAccount(Long customerID);
    void consumeAdvantage(CustomerAdvantage consumerAdvantageID, Long advantageID) throws NoAdvantageFoundException, VFPNotFoundException, AdvantageAlreadyConsumedException;
}
