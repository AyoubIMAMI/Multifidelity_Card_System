package fr.polytech.connectors;


import fr.polytech.connectors.externaldto.BankTransactionDTO;
import fr.polytech.interfaces.payment.Bank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class BankProxy implements Bank {

    @Value("${bank.host.baseurl}")
    private String bankHostandPort;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean refill(BankTransactionDTO bankTransactionDTO) {
        try {
            ResponseEntity<BankTransactionDTO> result = restTemplate.postForEntity(
                    bankHostandPort + "/cctransactions",
                    bankTransactionDTO,
                    BankTransactionDTO.class
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