package fr.polytech.components.customer;

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

import java.util.List;

public class CustomerFidelityManager implements FidelityExplorer, PointModifier, BalanceModifier {

    @Autowired
    PaymentModifier paymentModifier;
    @Autowired
    PaymentExplorer paymentExplorer;

    @Override
    public FidelityAccount getFidelityAccountByCustomer(Customer customer) throws CustomerNotFoundException, FidelityAccountNotFoundException {
        return null;
    }

    @Override
    public List<FidelityExplorer> getAllEligibleVFPCustomer() {
        return null;
    }

    @Override
    public void incrementPoints(Customer customer, float price) {

    }

    @Override
    public void decrementPoints(Customer customer, int points) {

    }

    @Override
    public void decreaseBalance(Customer customer, float amount) throws NotEnoughBalanceException {

    }

    @Override
    public void rechargeBalance(Customer customer, BankTransaction bankTransaction, float amount) throws MalformedBankInformationException {

    }
}
