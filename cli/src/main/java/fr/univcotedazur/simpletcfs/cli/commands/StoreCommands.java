package fr.univcotedazur.simpletcfs.cli.commands;

import fr.univcotedazur.simpletcfs.cli.CliContext;
import fr.univcotedazur.simpletcfs.cli.model.CliStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.client.RestTemplate;

@ShellComponent
public class StoreCommands {
    public static final String BASE_URI = "/stores";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private CliContext cliContext;

    @ShellMethod("Register a store in the backend (register-store SIRET STORE_NAME STORE_PASSWORD)")
    public CliStore registerStore(String siret, String name, String password) {
        CliStore res = restTemplate.postForObject(BASE_URI + "/registration", new CliStore(siret, name, password), CliStore.class);
        cliContext.getStores().put(res.getId(), res);
        return res;
    }

    @ShellMethod("List all stores")
    public String stores() {
        return cliContext.getStores().toString();
    }
}
