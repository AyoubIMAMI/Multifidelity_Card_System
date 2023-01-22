package fr.polytech.interfaces.store;

import fr.polytech.exceptions.StoreNotFoundException;
import fr.polytech.pojo.structure.Store;

public interface ScheduleExplorer {
    boolean isStoreOpen(Store store) throws StoreNotFoundException;


}
