package fr.polytech.controllers.dto;

import javax.validation.constraints.NotBlank;

public class OrganisationDTO {
    @NotBlank(message = "name should not be blank")
    private String siret;
    @NotBlank(message = "name should not be blank")
    private String name;
    private Long id;
    private String password;

    public OrganisationDTO(Long id, String siret, String name, String password) {
        this.id = id;
        this.siret = siret;
        this.name = name;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSiret() {
        return siret;
    }

    public String getPassword() {
        return password;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "OrganisationDTO{" +
                "siret='" + siret + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", password='" + password + '\'' +
                '}';
    }
}
