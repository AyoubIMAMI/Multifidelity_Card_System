package fr.polytech.entities;

import javax.persistence.*;
import java.util.*;

@Entity
public class CustomerAdvantage {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(optional = true)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ElementCollection
    private List<AdvantageCustomer> advantageCustomerList;

    public CustomerAdvantage(Customer customer){
        this.advantageCustomerList=new ArrayList<>();
        this.customer=customer;
    }

    public CustomerAdvantage() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUpAdvantage(Advantage advantageToVerify){
        for (AdvantageCustomer advantage:advantageCustomerList){
            if (advantage.getAdvantage().equals(advantageToVerify)){
                advantage.setLastConsumedDate(new Date());
                return;
            }
        }
        advantageCustomerList.add(new AdvantageCustomer(advantageToVerify,new Date()));
    }

    public Optional<Date> getAdvantageDate(Advantage advantageToVerify){
        for (AdvantageCustomer advantage:advantageCustomerList){
            if (advantage.getAdvantage().equals(advantageToVerify))
                return Optional.of(advantage.getLastConsumedDate());
        }
        return Optional.empty();
    }
}