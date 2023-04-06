package fr.polytech.exceptions.store;

public class StoreSiretAlreadyUsedException extends Exception{

    String siret;

    public StoreSiretAlreadyUsedException(String siret) {
        this.siret = siret;
    }

    public StoreSiretAlreadyUsedException(){}

    public String getSiret() {
        return siret;
    }

}
