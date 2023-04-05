package fr.polytech.entities.structure;

import java.util.Objects;

public class Employee {
    private int id;
    private String name;
    private String password;
    private Role roles;

    public Employee(String name, String password, Role roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(password, employee.password) && roles == employee.roles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, roles);
    }
}
