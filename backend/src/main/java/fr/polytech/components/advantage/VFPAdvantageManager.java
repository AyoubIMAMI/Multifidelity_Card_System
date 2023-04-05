package fr.polytech.components.advantage;

import fr.polytech.entities.Customer;
import fr.polytech.entities.CustomerAdvantage;
import fr.polytech.exceptions.advantage.AdvantageAlreadyConsumedException;
import fr.polytech.exceptions.advantage.NoAdvantageFoundException;
import fr.polytech.exceptions.advantage.VFPNotFoundException;
import fr.polytech.interfaces.advantage.AdvantageCustomer;
import fr.polytech.interfaces.advantage.AdvantageExplorer;
import fr.polytech.interfaces.advantage.CustomerAdvantageExplorer;
import fr.polytech.entities.Advantage;
import fr.polytech.repository.CustomerAdvantageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Component
public class VFPAdvantageManager implements AdvantageCustomer, CustomerAdvantageExplorer {
    CustomerAdvantageRepository customerAdvantageRepository;

    AdvantageExplorer advantageExplorer;
    @Autowired
    public VFPAdvantageManager(CustomerAdvantageRepository customerAdvantageRepository){
        this.customerAdvantageRepository=customerAdvantageRepository;
    }
    @Override
    public CustomerAdvantage addCustomerToProgramVFP(Customer consumer) {
        return customerAdvantageRepository.save(new CustomerAdvantage(consumer.getId()));
    }

    @Override
    public void consumeAdvantage(CustomerAdvantage customerAdvantage, Long advantageID) throws NoAdvantageFoundException, VFPNotFoundException, AdvantageAlreadyConsumedException {
        Optional<Date> date = customerAdvantage.getAdvantageDate(advantageID);
        if (date.isEmpty()||!isSameDay(date.get(),new Date()))
        {
            customerAdvantage.setUpAdvantage(advantageID);
            customerAdvantageRepository.save(customerAdvantage);
        }
        else
            throw new AdvantageAlreadyConsumedException(advantageID);
    }

    @Override
    public Optional<CustomerAdvantage> findCustomerAdvantageAccount(Long customerID){
        return(this.customerAdvantageRepository.findByConsumerID(customerID));
    }

    @Override
    public List<Advantage> getAdvantage(int userId) {
        return null;
    }


    private static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }
}
