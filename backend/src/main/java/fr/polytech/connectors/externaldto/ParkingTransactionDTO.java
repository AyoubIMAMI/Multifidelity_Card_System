package fr.polytech.connectors.externaldto;

public class ParkingTransactionDTO {
    private String licensePlate;
    private Long parkingID;

    public ParkingTransactionDTO() {
    }

    public ParkingTransactionDTO(String licensePlate, Long parkingID) {
        this.parkingID=parkingID;
        this.licensePlate=licensePlate;
    }

}
