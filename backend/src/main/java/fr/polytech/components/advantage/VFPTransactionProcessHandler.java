package fr.polytech.components.advantage;

import fr.polytech.connectors.externaldto.ParkingTransactionDTO;
import fr.polytech.entities.Customer;
import fr.polytech.entities.CustomerAdvantage;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.advantage.AdvantageAlreadyConsumedException;
import fr.polytech.exceptions.advantage.NoAdvantageFoundException;
import fr.polytech.exceptions.advantage.VFPNotFoundException;
import fr.polytech.interfaces.Parking;
import fr.polytech.interfaces.advantage.AdvantageCustomer;
import fr.polytech.interfaces.advantage.AdvantageExplorer;
import fr.polytech.interfaces.advantage.VFPTransaction;
import fr.polytech.entities.Advantage;
import fr.polytech.interfaces.customer.CustomerFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VFPTransactionProcessHandler implements VFPTransaction {

    AdvantageExplorer advantageExplorer;
    CustomerFinder customerFinder;
    AdvantageCustomer advantageCustomer;
    Parking parking;
    @Autowired
    public VFPTransactionProcessHandler(Parking parking,AdvantageExplorer advantageExplorer,CustomerFinder customerFinder,AdvantageCustomer advantageCustomer){
        this.advantageExplorer=advantageExplorer;
        this.customerFinder=customerFinder;
        this.advantageCustomer=advantageCustomer;
        this.parking=parking;
    }
    @Override
    public void tryUseAdvantage(Long userID, Long advantageID,Long parkingID) throws CustomerNotFoundException, NoAdvantageFoundException, VFPNotFoundException, AdvantageAlreadyConsumedException {
        Customer customer=this.customerFinder.findCustomerById(userID);
        Optional<Advantage> advantageOptional = advantageExplorer.VerifyAdvantage(advantageID);
        if (advantageOptional.isEmpty())
            throw new NoAdvantageFoundException(advantageID);
        Optional< CustomerAdvantage> customerAdvantageOptional=advantageCustomer.findCustomerAdvantageAccount(userID);
        if (customerAdvantageOptional.isEmpty())
            throw new VFPNotFoundException(userID);
        else if(advantageOptional.get().getAdvantageName().equals("parking")) {
            parking.getParkingPlace(new ParkingTransactionDTO(customer.getFidelityAccount().getLicencePlate(),parkingID));
        }

        else
            advantageCustomer.consumeAdvantage(customerAdvantageOptional.get(),advantageID);
    }
}
