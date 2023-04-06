package fr.univcotedazur.simpletcfs.cli.model;

public class CliAdvantage {

    Long id;
    String name;

    public CliAdvantage(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CliAdvantage() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CliAdvantage{");
        if (getId() != null) {
            sb.append("id=").append(getId());
            sb.append(", ");
        }
        sb.append("name='").append(getName()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
