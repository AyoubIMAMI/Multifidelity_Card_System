package fr.polytech.entities.structure;

import java.util.Set;

public abstract class Organisation {
    private String siret;
    private String name;

    public Organisation(String siret, String name) {
        this.siret = siret;
        this.name = name;
    }
}
