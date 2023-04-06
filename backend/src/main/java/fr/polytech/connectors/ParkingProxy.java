package fr.polytech.connectors;

import fr.polytech.connectors.externaldto.ParkingTransactionDTO;
import fr.polytech.interfaces.Parking;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ParkingProxy implements Parking {
    @Value("${parking.host.baseurl}")
    private String parkingHostandPort;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean getParkingPlace(ParkingTransactionDTO parkingTransactionDTO) {
        try {
            ResponseEntity<ParkingTransactionDTO> result = restTemplate.postForEntity(
                    parkingHostandPort + "/claim",
                    parkingTransactionDTO,
                    ParkingTransactionDTO.class
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
