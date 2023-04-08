package fr.polytech.components.advantage;

import fr.polytech.connectors.externaldto.ParkingTransactionDTO;
import fr.polytech.entities.Customer;
import fr.polytech.entities.VFPAccount;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.ParkingUnavailableException;
import fr.polytech.exceptions.advantage.AdvantageAlreadyConsumedException;
import fr.polytech.exceptions.advantage.NoAdvantageFoundException;
import fr.polytech.exceptions.advantage.VFPNotFoundException;
import fr.polytech.interfaces.Parking;
import fr.polytech.interfaces.advantage.VFPAdvantageFinder;
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
    VFPAdvantageFinder VFPAdvantageFinder;
    Parking parking;
    @Autowired
    public VFPTransactionProcessHandler(Parking parking, AdvantageExplorer advantageExplorer, CustomerFinder customerFinder, VFPAdvantageFinder advantageCustomer){
        this.advantageExplorer=advantageExplorer;
        this.customerFinder=customerFinder;
        this.VFPAdvantageFinder =advantageCustomer;
        this.parking=parking;
    }
    @Override
    public void tryUseParkingAdvantage(Long userID, Long advantageID, Long parkingID) throws CustomerNotFoundException, NoAdvantageFoundException, VFPNotFoundException, AdvantageAlreadyConsumedException, ParkingUnavailableException {
        useAnAdvantage(userID, advantageID);
        if (!parking.getParkingPlace(new ParkingTransactionDTO(this.customerFinder.findCustomerById(userID).getFidelityAccount().getLicencePlate(),parkingID)))
            throw new ParkingUnavailableException(parkingID);
        VFPAdvantageFinder.consumeAdvantage(VFPAdvantageFinder.findCustomerAdvantageAccount(this.customerFinder.findCustomerById(userID)).get(),advantageExplorer.VerifyAdvantage(advantageID).get());
    }
    public void useAnAdvantage(Long userID, Long advantageID) throws CustomerNotFoundException, NoAdvantageFoundException, VFPNotFoundException, AdvantageAlreadyConsumedException {
        Customer customer=this.customerFinder.findCustomerById(userID);
        Optional<Advantage> advantageOptional = advantageExplorer.VerifyAdvantage(advantageID);
        if (advantageOptional.isEmpty())
            throw new NoAdvantageFoundException(advantageID);
        Optional<VFPAccount> customerAdvantageOptional= VFPAdvantageFinder.findCustomerAdvantageAccount(customer);
        if (customerAdvantageOptional.isEmpty())
            throw new VFPNotFoundException(userID);
    }
    @Override
    public void tryUseAdvantage(Long userID, Long advantageID) throws CustomerNotFoundException, NoAdvantageFoundException, VFPNotFoundException, AdvantageAlreadyConsumedException {
        useAnAdvantage( userID,  advantageID);
        VFPAdvantageFinder.consumeAdvantage(VFPAdvantageFinder.findCustomerAdvantageAccount(this.customerFinder.findCustomerById(userID)).get(),advantageExplorer.VerifyAdvantage(advantageID).get());
    }

}
