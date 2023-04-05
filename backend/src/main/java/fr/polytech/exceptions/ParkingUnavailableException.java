package fr.polytech.exceptions;

public class ParkingUnavailableException extends Exception{

    Long id;

    public ParkingUnavailableException(Long id) {
        this.id = id;
    }

    public ParkingUnavailableException() {}

    public Long getId() {
        return id;
    }
}
