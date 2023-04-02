package fr.polytech.controllers.dto;

import javax.validation.constraints.NotBlank;

public class CustomerDTO {

    private Long id;

    @NotBlank(message = "name should not be blank")
    private String name;
    @NotBlank(message = "password should not be blank")
    private String password;
    @NotBlank(message = "email should not be blank")
    private String email;

    public CustomerDTO(Long id, String name, String email, String password){
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
