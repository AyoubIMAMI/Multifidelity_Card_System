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
    private FidelityAccount fidelityAccount = new FidelityAccount();

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFidelityAccount(FidelityAccount fidelityAccount) {
        this.fidelityAccount = fidelityAccount;
    }

    public List<Payment> getHistory() {
        return history;
    }

    public void setHistory(List<Payment> history) {
        this.history = history;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(password, customer.password) && Objects.equals(email, customer.email) && Objects.equals(fidelityAccount, customer.fidelityAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, email, fidelityAccount, history);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fidelityAccount=" + fidelityAccount +
                '}';
    }
}
