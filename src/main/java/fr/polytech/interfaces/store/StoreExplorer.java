package fr.polytech.interfaces.store;

import fr.polytech.exceptions.store.StoreNotFoundException;

public interface StoreExplorer {
    void getOpeningHours(String name)  throws StoreNotFoundException;
}
