package fr.univcotedazur.simpletcfs.cli.commands;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.client.RestTemplate;

@ShellComponent
public class VFPCommands {

    public static final String BASE_URI = "/VFP";

    @Autowired
    RestTemplate restTemplate;

    @ShellMethod("Use an advantage (use-advantage CUSTOMER_ID ADVANTAGE_ID)")
    public String useAdvantage(Long customerId, Long advantageID) {
        return restTemplate.getForObject(BASE_URI + "/" + customerId + "/" + advantageID, String.class);
    }

    @ShellMethod("Use a parking advantage (use-parking-advantage CUSTOMER_ID ADVANTAGE_ID PARKING_ID)")
    public String useParkingAdvantage(Long customerId, Long advantageID, Long parkingId) {
        return restTemplate.getForObject(BASE_URI + "/" + customerId + "/" + advantageID + "/" + parkingId, String.class);
    }


}
