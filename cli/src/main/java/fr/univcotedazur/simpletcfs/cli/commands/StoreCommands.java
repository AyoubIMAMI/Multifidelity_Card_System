package fr.univcotedazur.simpletcfs.cli.commands;

import fr.univcotedazur.simpletcfs.cli.CliContext;
import fr.univcotedazur.simpletcfs.cli.model.CliStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@ShellComponent
public class StoreCommands {
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
    public Double getStatsCost() {
        return restTemplate.getForObject(BASE_URI + COST_URI, Double.class);
    }
}
