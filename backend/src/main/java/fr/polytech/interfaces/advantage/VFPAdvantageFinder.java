package fr.polytech.interfaces.advantage;

import fr.polytech.entities.Customer;
import fr.polytech.entities.CustomerAdvantage;
import fr.polytech.exceptions.advantage.AdvantageAlreadyConsumedException;
import fr.polytech.exceptions.advantage.NoAdvantageFoundException;
import fr.polytech.entities.Advantage;
import fr.polytech.exceptions.advantage.VFPNotFoundException;

import java.util.Optional;

public interface VFPAdvantageFinder {
    Optional<CustomerAdvantage> findCustomerAdvantageAccount(Customer customer);
    void consumeAdvantage(CustomerAdvantage consumerAdvantageID, Advantage advantage) throws NoAdvantageFoundException, VFPNotFoundException, AdvantageAlreadyConsumedException;

}
