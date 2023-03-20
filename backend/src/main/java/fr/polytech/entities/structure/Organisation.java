package fr.polytech.entities.structure;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

@Entity
public abstract class Organisation {
    private String siret;
    private String name;
    @Id
    private Long id;

    public Organisation(String siret, String name) {
        this.siret = siret;
        this.name = name;
    }

    public Organisation() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
