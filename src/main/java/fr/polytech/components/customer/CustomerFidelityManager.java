package fr.polytech.components.customer;

import fr.polytech.Repository.FidelityAccountRepository;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.FidelityAccountNotFoundException;
import fr.polytech.exceptions.MalformedBankInformationException;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.interfaces.fidelity.FidelityExplorer;
import fr.polytech.interfaces.fidelity.PointModifier;
import fr.polytech.interfaces.payment.BalanceModifier;
import fr.polytech.interfaces.payment.PaymentExplorer;
import fr.polytech.interfaces.payment.PaymentModifier;
import fr.polytech.pojo.BankTransaction;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.FidelityAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerFidelityManager implements FidelityExplorer, PointModifier, BalanceModifier {

    PaymentModifier paymentModifier;
    PaymentExplorer paymentExplorer;

    FidelityAccountRepository fidelityAccountRepository;

    @Autowired
    public CustomerFidelityManager(PaymentModifier paymentModifier, PaymentExplorer paymentExplorer, FidelityAccountRepository fidelityAccountRepository){
        this.paymentModifier = paymentModifier;
        this.paymentExplorer = paymentExplorer;
        this.fidelityAccountRepository = fidelityAccountRepository;
    }

    @Override
    public FidelityAccount getFidelityAccountByCustomer(Customer customer) throws CustomerNotFoundException, FidelityAccountNotFoundException {
        return null;
    }

    @Override
    public List<FidelityExplorer> getAllEligibleVFPCustomer() {
        return null;
    }

    @Override
    public void incrementPoints(FidelityAccount fidelityAccount, float price) {
        fidelityAccount.setPoints((int) (fidelityAccount.getPoints()+Math.ceil(price)));
    }

    @Override
    public void decrementPoints(FidelityAccount fidelityAccount, int points) {

    }

    @Override
    public void decreaseBalance(Customer customer, float amount) throws NotEnoughBalanceException {

    }

    @Override
    public void rechargeBalance(Customer customer, BankTransaction bankTransaction, float amount) throws MalformedBankInformationException {

    }
}
