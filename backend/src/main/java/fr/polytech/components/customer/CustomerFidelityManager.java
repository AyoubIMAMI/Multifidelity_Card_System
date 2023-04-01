package fr.polytech.components.customer;

import fr.polytech.connectors.externaldto.BankTransactionDTO;
import fr.polytech.repository.CustomerRepository;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.FidelityAccountNotFoundException;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.interfaces.fidelity.FidelityExplorer;
import fr.polytech.interfaces.fidelity.PointModifier;
import fr.polytech.interfaces.payment.BalanceModifier;
import fr.polytech.entities.Customer;
import fr.polytech.entities.FidelityAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Component
public class CustomerFidelityManager implements FidelityExplorer, PointModifier, BalanceModifier {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerFidelityManager(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public FidelityAccount findFidelityAccountByCustomer(Customer customer) throws CustomerNotFoundException, FidelityAccountNotFoundException {
        return null;
    }

    @Override
    public FidelityAccount findFidelityAccountById(Long id) throws CustomerNotFoundException, FidelityAccountNotFoundException {
        return null;
    }

    @Override
    public List<FidelityExplorer> getAllEligibleVFPCustomer() {
        return null;
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
            throw new NotEnoughBalanceException();

        fidelityAccount.setBalance(balance - amount);
        return customerRepository.save(customer);
    }

    @Override
    public Customer rechargeBalance(Customer customer, BankTransactionDTO bankTransactionDTO) {
        FidelityAccount fidelityAccount = customer.getFidelityAccount();
        double balance = fidelityAccount.getBalance();
        fidelityAccount.setBalance(balance + bankTransactionDTO.getAmount());
        return customerRepository.save(customer);
    }
}
