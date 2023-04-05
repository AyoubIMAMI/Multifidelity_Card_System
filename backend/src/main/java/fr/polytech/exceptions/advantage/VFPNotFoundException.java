package fr.polytech.exceptions.advantage;

public class VFPNotFoundException extends Exception{

    Long userId;

    public VFPNotFoundException(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
