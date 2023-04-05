package fr.univcotedazur.simpletcfs.cli.commands;


import fr.univcotedazur.simpletcfs.cli.CliContext;
import fr.univcotedazur.simpletcfs.cli.model.CliStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.client.RestTemplate;

@ShellComponent
public class VFPCommand {

    public static final String BASE_URI = "/VFP";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private CliContext cliContext;

    @ShellMethod("Use an advantage (use-advantage CUSTOMER_ID ADVANTAGE_ID PARKING_ID)")
    public CliStore useAdvantage(Long customerId, Long advantageID,Long parkingID) {
        CliStore res = restTemplate.postForObject(BASE_URI + "/" + customerId + "/advantage", new CliAdvantage(advantageID, parkingID), CliStore.class);
        cliContext.getStores().put(res.getId(), res);
        return res;
    }


}
