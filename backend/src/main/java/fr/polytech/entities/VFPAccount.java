package fr.polytech.entities;

import javax.persistence.*;
import java.util.*;

@Entity
public class VFPAccount {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(optional = true)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ElementCollection
    private List<AdvantageConsumption> advantageConsumptionList;

    public VFPAccount(Customer customer){
        this.advantageConsumptionList =new ArrayList<>();
        this.customer=customer;
    }

    public VFPAccount() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUpAdvantage(Advantage advantageToVerify){
        for (AdvantageConsumption advantage: advantageConsumptionList){
            if (advantage.getAdvantage().equals(advantageToVerify)){
                advantage.setLastConsumedDate(new Date());
                return;
            }
        }
        advantageConsumptionList.add(new AdvantageConsumption(advantageToVerify,new Date()));
    }

    public Optional<Date> getAdvantageDate(Advantage advantageToVerify){
        for (AdvantageConsumption advantage: advantageConsumptionList){
            if (advantage.getAdvantage().equals(advantageToVerify))
                return Optional.of(advantage.getLastConsumedDate());
        }
        return Optional.empty();
    }
}