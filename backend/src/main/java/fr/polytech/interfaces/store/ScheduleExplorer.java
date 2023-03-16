package fr.polytech.interfaces.store;

import fr.polytech.entities.Store;
import fr.polytech.exceptions.StoreNotFoundException;
import fr.polytech.exceptions.store.InvalidDayException;

public interface ScheduleExplorer {
    boolean isStoreOpen(Store store) throws StoreNotFoundException;
    String getOpeningHour(Store store, String day) throws StoreNotFoundException, InvalidDayException;
    String getClosingHour(Store store, String day) throws StoreNotFoundException, InvalidDayException;
}
