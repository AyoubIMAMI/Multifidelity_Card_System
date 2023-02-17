package fr.polytech.components.customer;

import fr.polytech.pojo.PaymentDTO;
import fr.polytech.repository.FidelityAccountRepository;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.FidelityAccountNotFoundException;
import fr.polytech.exceptions.paiment.NegativeAmountException;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.interfaces.fidelity.FidelityExplorer;
import fr.polytech.interfaces.fidelity.PointModifier;
import fr.polytech.interfaces.payment.BalanceModifier;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.FidelityAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CustomerFidelityManager implements FidelityExplorer, PointModifier, BalanceModifier {

    FidelityAccountRepository fidelityAccountRepository;

    @Autowired
    public CustomerFidelityManager(FidelityAccountRepository fidelityAccountRepository){
        this.fidelityAccountRepository = fidelityAccountRepository;
    }

    @Override
    public FidelityAccount findFidelityAccountByCustomer(Customer customer) throws CustomerNotFoundException, FidelityAccountNotFoundException {
        return null;
    }

    @Override
    public FidelityAccount findFidelityAccountById(UUID id) throws CustomerNotFoundException, FidelityAccountNotFoundException {
        return null;
    }

    @Override
    public List<FidelityExplorer> getAllEligibleVFPCustomer() {
        return null;
    }

    @Override
    public void incrementPoints(FidelityAccount fidelityAccount, float price) {
        fidelityAccount.setPoints((int) (fidelityAccount.getPoints()+Math.floor(price)));
        fidelityAccountRepository.save(fidelityAccount.getClientId(), fidelityAccount);
    }

    @Override
    public void decrementPoints(FidelityAccount fidelityAccount, int points) {
        fidelityAccount.setPoints(fidelityAccount.getPoints() - points);
        fidelityAccountRepository.save(fidelityAccount.getClientId(), fidelityAccount);
    }

    @Override
    public void decreaseBalance(FidelityAccount fidelityAccount, double amount) throws NotEnoughBalanceException {
        double balance = fidelityAccount.getBalance();
        if(balance < amount)
            throw new NotEnoughBalanceException();

        fidelityAccount.setBalance(balance - amount);
        fidelityAccountRepository.save(fidelityAccount.getClientId(), fidelityAccount);
    }

    @Override
    public void rechargeBalance(FidelityAccount fidelityAccount, PaymentDTO paymentDTO) {
        double balance = fidelityAccount.getBalance();
        fidelityAccount.setBalance(balance + paymentDTO.getAmount());
        fidelityAccountRepository.save(fidelityAccount.getClientId(), fidelityAccount);
    }
}
