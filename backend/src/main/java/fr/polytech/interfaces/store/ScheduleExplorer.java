package fr.polytech.interfaces.store;

import fr.polytech.exceptions.StoreNotFoundException;
import fr.polytech.exceptions.store.InvalidDayException;
import fr.polytech.entities.structure.Store;

public interface ScheduleExplorer {
    boolean isStoreOpen(Store store) throws StoreNotFoundException;
    String getOpeningHour(Store store, String day) throws StoreNotFoundException, InvalidDayException;
    String getClosingHour(Store store, String day) throws StoreNotFoundException, InvalidDayException;
}
