package fr.univcotedazur.simpletcfs.cli.commands;

import fr.univcotedazur.simpletcfs.cli.CliContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class UsefulCommands {

    @Autowired
    private CliContext cliContext;

    @ShellMethod("Print message (print MESSAGE)")
    public String print(String message) {
        return "\n" + "\u001B[34m" + message + "\u001B[0m";
    }

    @ShellMethod("Clear context (clear-ctx)")
    public void clearCtx() {
        cliContext.clearAll();
    }

}
