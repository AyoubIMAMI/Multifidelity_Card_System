package fr.univcotedazur.simpletcfs.cli.model;

public class CliStore {

    private Long id;
    private String siret;
    private String name;
    private String password;

    public CliStore(String siret, String name, String password) {
        this.siret = siret;
        this.name = name;
        this.password = password;
    }

    public CliStore() {
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CliStore{" +
                "\u001B[34m" + "id=" + id + "\u001B[0m" +
                ", siret='" + siret + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
