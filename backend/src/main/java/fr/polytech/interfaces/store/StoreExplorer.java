package fr.polytech.interfaces.store;

import fr.polytech.entities.Store;
import fr.polytech.exceptions.store.StoreNotFoundException;
import fr.polytech.entities.Schedule;
import fr.polytech.entities.item.Discount;

import java.util.Set;

public interface StoreExplorer {
    Schedule getOpeningHours(Store store) throws StoreNotFoundException;
    Set<Discount> getOffers(Store store) throws StoreNotFoundException;
}
