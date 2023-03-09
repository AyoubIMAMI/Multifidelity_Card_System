package fr.polytech.components.payment;

import fr.polytech.exceptions.paiment.NegativeAmountException;
import fr.polytech.exceptions.paiment.PaymentException;
import fr.polytech.interfaces.payment.Bank;
import fr.polytech.interfaces.payment.RefillFidelityCard;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.FidelityAccount;
import fr.polytech.pojo.PaymentDTO;
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

    private FidelityAccount johnFidelityAccount;
    private FidelityAccount mouradFidelityAccount;

    private final String correct_credit_card = "1234-896983";
    private final String bad_credit_card = "1234-137911";

    @BeforeEach
    public void setUp() throws Exception {
        johnFidelityAccount = john.getFidelityAccount();
        mouradFidelityAccount = mourad.getFidelityAccount();

        // Mocking the bank proxy
        when(bankMock.pay(any(PaymentDTO.class))).thenAnswer((Answer<Boolean>) invocation -> {
            PaymentDTO arg = invocation.getArgument(0);
            return correct_credit_card.equals(arg.getCreditCard());
        });
    }

    @Test
    public void okTransactionTestvMain() throws PaymentException, NegativeAmountException {
        PaymentDTO transaction = new PaymentDTO(correct_credit_card, 120);
        Date transactionDate = refillFidelityCard.refill(johnFidelityAccount, transaction);
        assertNotNull(transactionDate);
    }
    @Test
    public void okTransactionTestvBranche() throws PaymentException, NegativeAmountException {
        // Creating a transaction
        PaymentDTO transaction = new PaymentDTO(correct_credit_card, 120);

        // Verifying customer balance
        assertEquals(0, johnFidelityAccount.getBalance());

        // Making transaction
        Date transactionDate = refillFidelityCard.refill(john, transaction);

        // Verifying if transaction is successful by checking transaction date
        assertNotNull(transactionDate);

        // Verifying balance update on customer account
        assertEquals(120, johnFidelityAccount.getBalance());
    }

    @Test
    public void nokTransactionTest() {
        PaymentDTO transaction = new PaymentDTO(bad_credit_card, 50);
        assertThrows(PaymentException.class,  () -> refillFidelityCard.refill(mourad, transaction));
    }
}
