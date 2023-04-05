package fr.univcotedazur.simpletcfs.cli.model;

public class Customer {
    private Long id;
    private String name;
    private String password;
    private String email;

    private FidelityAccount fidelityAccount;

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

    public FidelityAccount getFidelityAccount() {
        return fidelityAccount;
    }

    public void setFidelityAccount(FidelityAccount fidelityAccount) {
        this.fidelityAccount = fidelityAccount;
    }

    @Override
    public String toString() {
        return "CliCustomer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fidelityAccount=" + fidelityAccount +
                '}';
    }
}

