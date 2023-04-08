package fr.polytech.components.customer;

import fr.polytech.interfaces.advantage.VFPAdvantageModifier;
import fr.polytech.interfaces.payment.PaymentExplorer;
import fr.polytech.repository.CustomerRepository;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.interfaces.fidelity.FidelityExplorer;
import fr.polytech.interfaces.fidelity.PointModifier;
import fr.polytech.interfaces.payment.BalanceModifier;
import fr.polytech.entities.Customer;
import fr.polytech.entities.FidelityAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Transactional
@Component
public class CustomerFidelityManager implements FidelityExplorer, PointModifier, BalanceModifier {

    CustomerRepository customerRepository;

    PaymentExplorer paymentExplorer;


    VFPAdvantageModifier VFPAdvantageModifier;

    @Autowired
    public CustomerFidelityManager(CustomerRepository customerRepository, PaymentExplorer paymentExplorer, VFPAdvantageModifier VFPAdvantageModifier){
        this.customerRepository = customerRepository;
        this.paymentExplorer=paymentExplorer;
        this.VFPAdvantageModifier = VFPAdvantageModifier;
    }


    @Override
    public boolean checkIfPossibleToBecomeVfp(Customer customer) {
        boolean hasReached10Payments = paymentExplorer.customerReached10Payments(customer);
        if (hasReached10Payments) VFPAdvantageModifier.addCustomerToProgramVFP(customer);
        return hasReached10Payments;
    }


    @Override
    public Customer incrementPoints(Customer customer, float points) {
        FidelityAccount fidelityAccount = customer.getFidelityAccount();
        fidelityAccount.setPoints((int) (fidelityAccount.getPoints() + Math.floor(points)));
       return customerRepository.save(customer);
    }

    @Override
    public Customer decrementPoints(Customer customer, int points) {
        FidelityAccount fidelityAccount = customer.getFidelityAccount();
        fidelityAccount.setPoints(fidelityAccount.getPoints() - points);
        return customerRepository.save(customer);
    }

    @Override
    public Customer decreaseBalance(Customer customer, double amount) throws NotEnoughBalanceException {
        FidelityAccount fidelityAccount = customer.getFidelityAccount();
        double balance = fidelityAccount.getBalance();
        if(balance < amount)
            throw new NotEnoughBalanceException(balance, amount);

        fidelityAccount.setBalance(balance - amount);
        return customerRepository.save(customer);
    }

    @Override
    public Customer rechargeBalance(Customer customer, String creditCard, double amount) {
        FidelityAccount fidelityAccount = customer.getFidelityAccount();
        double balance = fidelityAccount.getBalance();
        fidelityAccount.setBalance(balance + amount);
        return customerRepository.save(customer);
    }
}
