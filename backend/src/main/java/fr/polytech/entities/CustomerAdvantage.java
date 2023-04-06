package fr.polytech.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Entity
public class CustomerAdvantage {

    @Id
    @GeneratedValue
    private Long id;

    private Long consumerID;

    @ElementCollection
    @MapKeyJoinColumn(name = "advantage_id")
    @Temporal(TemporalType.DATE)
    @Column(name = "consumed_date")
    private Map<Long, Date> consumedAdvantage;

    public CustomerAdvantage(Long id){
        this.consumerID=id;
    }

    public CustomerAdvantage() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUpAdvantage(Long advantageID){
        consumedAdvantage.put(advantageID,new Date());
    }

    public Optional<Date> getAdvantageDate(Long advantageID){
        if (!consumedAdvantage.containsKey(advantageID))
            return Optional.empty();
        return Optional.of(consumedAdvantage.get(advantageID));
    }
}