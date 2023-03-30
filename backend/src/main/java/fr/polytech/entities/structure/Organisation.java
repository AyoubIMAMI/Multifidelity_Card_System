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
    private String password;

    public Organisation(String siret, String name,String password) {
        this.siret = siret;
        this.name = name;
        this.password=password;
    }

    public Organisation() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSiret() {
        return siret;
    }

}
