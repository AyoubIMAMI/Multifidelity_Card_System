package fr.polytech.controllers.dto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class OrganisationDTO {

    private Long id;
    @NotBlank(message = "siret should not be blank")
    private String siret;
    @NotBlank(message = "name should not be blank")
    private String name;
    @NotBlank(message = "password should not be blank")
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
                "id=" + id +
                ", siret='" + siret + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganisationDTO that = (OrganisationDTO) o;
        return Objects.equals(siret, that.siret) && Objects.equals(name, that.name) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(siret, name, password);
    }
}
