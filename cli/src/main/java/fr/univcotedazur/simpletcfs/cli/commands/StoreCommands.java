package fr.univcotedazur.simpletcfs.cli.commands;

import fr.univcotedazur.simpletcfs.cli.CliContext;
import fr.univcotedazur.simpletcfs.cli.model.CliStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

@ShellComponent
public class StoreCommands {

    private static final Logger logger = Logger.getLogger("StoreCommands");
    public static final String BASE_URI = "/stores";
    public static final String COST_URI = "/statistics/cost";
    public static final String POINTS_URI = "/statistics/points";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private CliContext cliContext;

    @ShellMethod("List all stores")
    public String stores() {
        StringBuilder stores = new StringBuilder("List of stores:\n");
        for (Map.Entry<Long, CliStore> entry : cliContext.getStores().entrySet()) {
            stores.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }
        return stores.toString();
    }

    @ShellMethod("Register a store in the backend (register-store SIRET STORE_NAME STORE_PASSWORD)")
    public CliStore registerStore(String siret, String name, String password) {
        CliStore res = restTemplate.postForObject(BASE_URI + "/registration", new CliStore(siret, name, password), CliStore.class);
        cliContext.getStores().put(res.getId(), res);
        return res;
    }

    @ShellMethod("Statistics on the total cost of the discounts operations since the beginning")
    public Double getCostStats() {
        return restTemplate.getForObject(BASE_URI + COST_URI, Double.class);
    }

    @ShellMethod("Statistics on the total cost of the discounts operations since the given date")
    public String getDateCostStats(String stringDate) {
        Date date;
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date = dateFormat.parse(stringDate);
        } catch (Exception e) {
            return "Wrong format date! Format date expected: dd/MM/yyyy";
        }
        return "" + restTemplate.postForObject(BASE_URI + COST_URI, date, Double.class);

    }

    @ShellMethod("Statistics on the total points of the discounts operations since the beginning")
    public Double getPointsStats() {
        return restTemplate.getForObject(BASE_URI + POINTS_URI, Double.class);
    }

    @ShellMethod("Statistics on the total points of the discounts operations since the given date")
    public String getDatePointsStats(String stringDate) {
        Date date;
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date = dateFormat.parse(stringDate);
        } catch (Exception e) {
            return "Wrong format date! Format date expected: dd/MM/yyyy";
        }
        return "" + restTemplate.postForObject(BASE_URI + POINTS_URI, date, Double.class);
    }
}
