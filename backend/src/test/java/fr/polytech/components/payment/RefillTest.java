package fr.polytech.components.payment;

import fr.polytech.exceptions.MalformedBankInformationException;
import fr.polytech.exceptions.paiment.PaymentException;
import fr.polytech.interfaces.payment.Bank;
import fr.polytech.interfaces.payment.RefillFidelityCard;
import fr.polytech.pojo.BankTransaction;
import fr.polytech.pojo.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RefillTest {
    @Autowired
    private RefillFidelityCard refillFidelityCard;

    @MockBean
    private Bank bankMock;

    private final Customer john = new Customer("John", "jhon@mail.com", "password");
    private final Customer mourad = new Customer("Mourad", "mourad@mail.com", "password");

    @BeforeEach
    public void setUp() throws Exception {
        // Mocking the bank proxy
        when(bankMock.pay(any(BankTransaction.class))).thenAnswer((Answer<Boolean>) invocation -> {
            BankTransaction arg = invocation.getArgument(0);
            return "John".equals(arg.getCustomer().getName());
        });
    }

    @Test
    public void okTransactionTest() throws PaymentException, MalformedBankInformationException {
        BankTransaction bankTransaction = new BankTransaction();
        bankTransaction.setCardNumber("1234-896983");
        bankTransaction.setCustomer(john);
        Date now = refillFidelityCard.refill(bankTransaction);
        assertNotNull(now);
    }

    @Test
    public void nokTransactionTest() throws MalformedBankInformationException {
        BankTransaction bankTransaction = new BankTransaction();
        bankTransaction.setCardNumber("1234-896983");
        bankTransaction.setCustomer(mourad);
        assertThrows(PaymentException.class,  () -> refillFidelityCard.refill(bankTransaction));
    }
}
