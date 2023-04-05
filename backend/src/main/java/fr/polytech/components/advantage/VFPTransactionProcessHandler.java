package fr.polytech.components.advantage;

import fr.polytech.entities.CustomerAdvantage;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.advantage.AdvantageAlreadyConsumedException;
import fr.polytech.exceptions.advantage.NoAdvantageFoundException;
import fr.polytech.exceptions.advantage.VFPNotFoundException;
import fr.polytech.interfaces.advantage.AdvantageCustomer;
import fr.polytech.interfaces.advantage.AdvantageExplorer;
import fr.polytech.interfaces.advantage.VFPTransaction;
import fr.polytech.entities.Advantage;
import fr.polytech.interfaces.customer.CustomerFinder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VFPTransactionProcessHandler implements VFPTransaction {

    AdvantageExplorer advantageExplorer;
    CustomerFinder customerFinder;
    AdvantageCustomer advantageCustomer;
    public VFPTransactionProcessHandler(AdvantageExplorer advantageExplorer,CustomerFinder customerFinder,AdvantageCustomer advantageCustomer){
        this.advantageExplorer=advantageExplorer;
        this.customerFinder=customerFinder;
        this.advantageCustomer=advantageCustomer;
    }
    @Override
    public void tryUseAdvantage(Long userID, Long advantageID) throws CustomerNotFoundException, NoAdvantageFoundException, VFPNotFoundException, AdvantageAlreadyConsumedException {
        this.customerFinder.findCustomerById(userID);
        Optional<Advantage> advantageOptional = advantageExplorer.VerifyAdvantage(advantageID);
        if (advantageOptional.isEmpty())
            throw new NoAdvantageFoundException();
        Optional< CustomerAdvantage> customerAdvantageOptional=advantageCustomer.findCustomerAdvantageAccount(userID);
        if (customerAdvantageOptional.isEmpty())
            throw new VFPNotFoundException();
        else
            advantageCustomer.consumeAdvantage(customerAdvantageOptional.get(),advantageID);
    }
}
