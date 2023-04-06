package fr.polytech.interfaces.store;

import fr.polytech.entities.Store;
import fr.polytech.exceptions.store.StoreNotFoundException;

public interface ScheduleExplorer {
    boolean isStoreOpen(Store store) throws StoreNotFoundException;
    String getOpeningHour(Store store, String day) throws StoreNotFoundException;
    String getClosingHour(Store store, String day) throws StoreNotFoundException;
}
