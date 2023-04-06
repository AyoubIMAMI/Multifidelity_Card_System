package fr.univcotedazur.simpletcfs.cli.model;

public class CliAdvantage {

    private String advantageName;
    private Long id;

    public CliAdvantage(String advantageName){
        this.advantageName=advantageName;
    }
    public CliAdvantage() {

    }

    public Long getId() {
        return id;
    }

    public String getAdvantageName() {
        return advantageName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String advantageName) {this.advantageName = advantageName;}

    @Override
    public String toString() {
        return "CliAdvantage{" +
                "id=" + id +
                ", name='" + advantageName + '\'' +
                '}';
    }
}
