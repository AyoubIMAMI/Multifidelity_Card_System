package fr.polytech.interfaces.advantage;

import fr.polytech.entities.Customer;
import fr.polytech.entities.VFPAccount;
import fr.polytech.exceptions.advantage.AdvantageAlreadyConsumedException;
import fr.polytech.exceptions.advantage.NoAdvantageFoundException;
import fr.polytech.entities.Advantage;
import fr.polytech.exceptions.advantage.VFPNotFoundException;

import java.util.Optional;

public interface VFPAdvantageFinder {
    Optional<VFPAccount> findCustomerAdvantageAccount(Customer customer);
    void consumeAdvantage(VFPAccount consumerAdvantageID, Advantage advantage) throws NoAdvantageFoundException, VFPNotFoundException, AdvantageAlreadyConsumedException;

}
