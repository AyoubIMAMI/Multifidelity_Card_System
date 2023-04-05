package fr.polytech.exceptions.advantage;

public class AdvantageAlreadyConsumedException extends Exception{

    Long id;

    public AdvantageAlreadyConsumedException(Long id) {
        this.id = id;
    }

    public AdvantageAlreadyConsumedException() {}

    public Long getId() {
        return id;
    }
}
