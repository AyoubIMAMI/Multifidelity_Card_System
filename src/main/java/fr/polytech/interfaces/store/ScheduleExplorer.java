package fr.polytech.interfaces.store;

import fr.polytech.exceptions.StoreNotFoundException;
import fr.polytech.exceptions.store.NotValidDayException;
import fr.polytech.pojo.structure.Store;

public interface ScheduleExplorer {
    boolean isStoreOpen(Store store) throws StoreNotFoundException;
    String getOpeningHour(Store store, String day) throws StoreNotFoundException, NotValidDayException;
    String getClosingHour(Store store, String day) throws StoreNotFoundException, NotValidDayException;
}
