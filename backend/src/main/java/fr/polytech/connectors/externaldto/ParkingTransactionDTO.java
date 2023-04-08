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

    public Long getParkingID() {
        return parkingID;
    }

    public void setParkingID(Long parkingID) {
        this.parkingID = parkingID;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
