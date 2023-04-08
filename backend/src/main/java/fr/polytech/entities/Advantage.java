package fr.polytech.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Advantage {

    //private TerritorialCommunity provider;
    private String advantageName;
    @GeneratedValue
    @Id
    private Long id;

    public Advantage(String advantageName){
        this.advantageName=advantageName;
    }
    public Advantage() {

    }

    public Long getId() {
        return id;
    }

    public String getAdvantageName() {
        return advantageName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String advantageName) {this.advantageName = advantageName;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advantage advantage = (Advantage) o;

        if (!Objects.equals(advantageName, advantage.advantageName))
            return false;
        return Objects.equals(id, advantage.id);
    }

    @Override
    public int hashCode() {
        int result = advantageName != null ? advantageName.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
