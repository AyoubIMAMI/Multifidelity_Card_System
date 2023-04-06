package fr.polytech.interfaces;

import fr.polytech.connectors.externaldto.ParkingTransactionDTO;

public interface Parking {
    boolean getParkingPlace(ParkingTransactionDTO parkingTransactionDTO);
}
