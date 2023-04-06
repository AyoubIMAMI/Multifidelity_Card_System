package fr.polytech.exceptions.advantage;

public class NoAdvantageFoundException extends Exception{

    Long id;

    public NoAdvantageFoundException(Long id) {
        this.id = id;
    }

    public NoAdvantageFoundException() {
    }

    public Long getId() {
        return id;
    }
}
