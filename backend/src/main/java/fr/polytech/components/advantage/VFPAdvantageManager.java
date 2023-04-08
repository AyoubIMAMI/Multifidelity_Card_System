package fr.polytech.components.advantage;

import fr.polytech.entities.Advantage;
import fr.polytech.entities.Customer;
import fr.polytech.entities.VFPAccount;
import fr.polytech.exceptions.advantage.AdvantageAlreadyConsumedException;
import fr.polytech.exceptions.advantage.NoAdvantageFoundException;
import fr.polytech.exceptions.advantage.VFPNotFoundException;
import fr.polytech.interfaces.advantage.VFPAdvantageModifier;
import fr.polytech.interfaces.advantage.VFPAdvantageFinder;
import fr.polytech.interfaces.advantage.AdvantageExplorer;
import fr.polytech.repository.CustomerAdvantageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;


@Component
public class VFPAdvantageManager implements VFPAdvantageModifier, VFPAdvantageFinder {
    CustomerAdvantageRepository customerAdvantageRepository;

    AdvantageExplorer advantageExplorer;
    @Autowired
    public VFPAdvantageManager(CustomerAdvantageRepository customerAdvantageRepository, AdvantageExplorer advantageExplorer){
        this.customerAdvantageRepository=customerAdvantageRepository;
        this.advantageExplorer=advantageExplorer;
    }
    @Override
    public VFPAccount addCustomerToProgramVFP(Customer consumer) {
        return customerAdvantageRepository.save(new VFPAccount(consumer));
    }

    @Override
    public void consumeAdvantage(VFPAccount VFPAccount, Advantage advantage) throws NoAdvantageFoundException, VFPNotFoundException, AdvantageAlreadyConsumedException {
        Optional<Date> date = VFPAccount.getAdvantageDate(advantage);
        if (date.isEmpty()||!isSameDay(date.get(),new Date()))
        {
            VFPAccount.setUpAdvantage(advantage);
            customerAdvantageRepository.save(VFPAccount);
        }
        else
            throw new AdvantageAlreadyConsumedException(advantage.getId());
    }

    @Override
    public Optional<VFPAccount> findCustomerAdvantageAccount(Customer customer){
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
