package fr.polytech.components.advantage;

import fr.polytech.entities.Advantage;
import fr.polytech.entities.Customer;
import fr.polytech.entities.CustomerAdvantage;
import fr.polytech.exceptions.advantage.AdvantageAlreadyConsumedException;
import fr.polytech.exceptions.advantage.NoAdvantageFoundException;
import fr.polytech.exceptions.advantage.VFPNotFoundException;
import fr.polytech.interfaces.advantage.AdvantageCustomer;
import fr.polytech.interfaces.advantage.AdvantageCustomerFinder;
import fr.polytech.interfaces.advantage.AdvantageExplorer;
import fr.polytech.repository.CustomerAdvantageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;


@Component
public class VFPAdvantageManager implements AdvantageCustomer, AdvantageCustomerFinder {
    CustomerAdvantageRepository customerAdvantageRepository;

    AdvantageExplorer advantageExplorer;
    @Autowired
    public VFPAdvantageManager(CustomerAdvantageRepository customerAdvantageRepository,AdvantageExplorer advantageExplorer){
        this.customerAdvantageRepository=customerAdvantageRepository;
        this.advantageExplorer=advantageExplorer;
    }
    @Override
    public CustomerAdvantage addCustomerToProgramVFP(Customer consumer) {
        return customerAdvantageRepository.save(new CustomerAdvantage(consumer));
    }

    @Override
    public void consumeAdvantage(CustomerAdvantage customerAdvantage, Advantage advantage) throws NoAdvantageFoundException, VFPNotFoundException, AdvantageAlreadyConsumedException {
        Optional<Date> date = customerAdvantage.getAdvantageDate(advantage);
        if (date.isEmpty()||!isSameDay(date.get(),new Date()))
        {
            customerAdvantage.setUpAdvantage(advantage);
            customerAdvantageRepository.save(customerAdvantage);
        }
        else
            throw new AdvantageAlreadyConsumedException(advantage.getId());
    }

    @Override
    public Optional<CustomerAdvantage> findCustomerAdvantageAccount(Customer customer){
        return(this.customerAdvantageRepository.findByCustomer(customer));
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
