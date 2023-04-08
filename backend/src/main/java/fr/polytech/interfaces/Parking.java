package fr.polytech.interfaces;

import fr.polytech.connectors.externaldto.ParkingTransactionDTO;

public interface Parking {

    /**
     * Get a parking place
     * @param parkingTransactionDTO dto
     * @return a boolean according to the availability of the places
     */
    boolean getParkingPlace(ParkingTransactionDTO parkingTransactionDTO);
}
