package fr.polytech.components.payment;

import fr.polytech.interfaces.payment.Bank;
import fr.polytech.pojo.BankTransaction;
import fr.polytech.pojo.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class BankProxy implements Bank {

    @Value("${bank.host.baseurl}")
    private String bankHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean pay(Customer customer, BankTransaction transaction) {
        try {
            ResponseEntity<BankTransaction> result = restTemplate.postForEntity(
                    bankHostAndPort + "/cctransactions",
                    transaction,
                    BankTransaction.class
            );
            return (result.getStatusCode().equals(HttpStatus.CREATED));
        }
        catch (HttpClientErrorException errorException) {
            if (errorException.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                return false;
            }
            throw errorException;
        }
    }
}