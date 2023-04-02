package fr.polytech.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;
    private String email;

    @Embedded
    private FidelityAccount fidelityAccount = new FidelityAccount();;

    @OneToMany(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Payment> history;

    public Customer(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Customer() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public FidelityAccount getFidelityAccount() {
        return fidelityAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!Objects.equals(name, customer.name)) return false;
        if (!Objects.equals(password, customer.password)) return false;
        if (!Objects.equals(email, customer.email)) return false;
        return Objects.equals(fidelityAccount, customer.fidelityAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, email, fidelityAccount, history);
    }
}