package fr.univcotedazur.simpletcfs.cli.commands;

import fr.univcotedazur.simpletcfs.cli.CliContext;
import fr.univcotedazur.simpletcfs.cli.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@ShellComponent
public class StoreCommands {
    public static final String BASE_URI = "/stores";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private CliContext cliContext;

    @ShellMethod("List all stores")
    public String stores() {
        StringBuilder stores = new StringBuilder("List of stores:\n");
        for (Map.Entry<Long, Store> entry : cliContext.getStores().entrySet()) {
            stores.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }
        return stores.toString();
    }

    @ShellMethod("Register a store in the backend (register-store SIRET STORE_NAME STORE_PASSWORD)")
    public Store registerStore(String siret, String name, String password) {
        Store res = restTemplate.postForObject(BASE_URI + "/registration", new Store(siret, name, password), Store.class);
        cliContext.getStores().put(res.getId(), res);
        return res;
    }
}
