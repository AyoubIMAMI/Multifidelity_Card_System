package fr.univcotedazur.simpletcfs.cli.model;
import java.util.Objects;


public class CliCustomer {

    private Long id;

    private String name;
    private String password;
    private String email;
    private FidelityAccount fidelityAccount;

    //@OneToMany(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "customer")
    //private List<Payment> history;

    public CliCustomer(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
        //this.history = new ArrayList<>();
    }
    public CliCustomer(){
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

        CliCustomer customer = (CliCustomer) o;

        if (!Objects.equals(name, customer.name)) return false;
        if (!Objects.equals(password, customer.password)) return false;
        if (!Objects.equals(email, customer.email)) return false;
        return Objects.equals(fidelityAccount, customer.fidelityAccount);
    }
}

