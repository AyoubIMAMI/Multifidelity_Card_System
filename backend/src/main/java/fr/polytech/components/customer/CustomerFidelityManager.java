package fr.polytech.components.customer;

import fr.polytech.components.advantage.VFPAdvantageManager;
import fr.polytech.connectors.externaldto.BankTransactionDTO;
import fr.polytech.interfaces.advantage.AdvantageCustomer;
import fr.polytech.interfaces.payment.PaymentExplorer;
import fr.polytech.repository.CustomerRepository;
import fr.polytech.exceptions.CustomerNotFoundException;
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

    PaymentExplorer paymentExplorer;


    AdvantageCustomer advantageCustomer;

    @Autowired
    public CustomerFidelityManager(CustomerRepository customerRepository,PaymentExplorer paymentExplorer,AdvantageCustomer advantageCustomer){
        this.customerRepository = customerRepository;
        this.paymentExplorer=paymentExplorer;
        this.advantageCustomer=advantageCustomer;
    }

    @Override
    public FidelityAccount findFidelityAccountByCustomer(Customer customer) throws CustomerNotFoundException {
        return null;
    }

    @Override
    public FidelityAccount findFidelityAccountById(Long id) throws CustomerNotFoundException {
        return null;
    }

    @Override
    public boolean checkIfPossibleToBecomeVfp(Customer customer) {
        boolean hasReached10Payments = paymentExplorer.customerReached10Payments(customer);
        if (hasReached10Payments) advantageCustomer.addCustomerToProgramVFP(customer);
        return hasReached10Payments;
    }


    @Override
    public Customer incrementPoints(Customer customer, float points) {
        FidelityAccount fidelityAccount = customer.getFidelityAccount();
        System.out.println("Fidelity account found : " + fidelityAccount);
        System.out.println("Ancien solde de point : " + fidelityAccount.getPoints());
        System.out.println("On ajoute : " + Math.floor(points));
        fidelityAccount.setPoints((int) (fidelityAccount.getPoints() + Math.floor(points)));
        System.out.println("Nouveau solde : " + fidelityAccount.getPoints());
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
    public Customer rechargeBalance(Customer customer, BankTransactionDTO bankTransactionDTO) {
        FidelityAccount fidelityAccount = customer.getFidelityAccount();
        double balance = fidelityAccount.getBalance();
        fidelityAccount.setBalance(balance + bankTransactionDTO.getAmount());
        return customerRepository.save(customer);
    }
}
