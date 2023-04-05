package fr.polytech.controllers.dto;

import fr.polytech.entities.FidelityAccount;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CustomerDTO {

    private Long id;

    @NotBlank(message = "name should not be blank")
    private String name;
    @NotBlank(message = "password should not be blank")
    private String password;
    @NotBlank(message = "email should not be blank")
    private String email;

    private FidelityAccount fidelityAccount;

    public CustomerDTO(Long id, String name, String email, String password, FidelityAccount fidelityAccount){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.fidelityAccount = fidelityAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public FidelityAccount getFidelityAccount() {
        return fidelityAccount;
    }

    public void setFidelityAccount(FidelityAccount fidelityAccount) {
        this.fidelityAccount = fidelityAccount;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fidelityAccount=" + fidelityAccount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(password, that.password) && Objects.equals(email, that.email) && Objects.equals(fidelityAccount, that.fidelityAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, email, fidelityAccount);
    }
}
