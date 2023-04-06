package fr.polytech.exceptions.advantage;

public class AdvantageNotFoundException extends Exception{

    Long id;

    public AdvantageNotFoundException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
