package fr.polytech.controllers.dto;

import fr.polytech.entities.FidelityAccount;

import javax.validation.constraints.NotBlank;

public class CustomerDTO {
    @NotBlank(message = "name should not be blank")
    private String name;
    @NotBlank(message = "password should not be blank")
    private String password;
    @NotBlank(message = "email should not be blank")
    private String email;

    public CustomerDTO(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
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

    public void setEmail(String email) {
        this.email = email;
    }
}
