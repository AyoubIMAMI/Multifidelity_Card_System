package fr.univcotedazur.simpletcfs.cli.model;

public class CliCustomer {
    private Long id;
    private String name;
    private String password;
    private String email;
    private CliFidelityAccount fidelityAccount;

    public CliCustomer(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public CliCustomer() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CliFidelityAccount getFidelityAccount() {
        return fidelityAccount;
    }

    public void setFidelityAccount(CliFidelityAccount fidelityAccount) {
        this.fidelityAccount = fidelityAccount;
    }

    @Override
    public String toString() {
        return "CliCustomer{" +
                "\u001B[34m" + "id=" + id + "\u001B[0m" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fidelityAccount=" + fidelityAccount +
                '}';
    }
}

