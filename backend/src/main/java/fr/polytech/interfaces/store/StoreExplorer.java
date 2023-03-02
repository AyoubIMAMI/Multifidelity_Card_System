package fr.polytech.interfaces.store;

import fr.polytech.exceptions.store.StoreNotFoundException;
import fr.polytech.entities.Schedule;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.structure.Store;

import java.util.Set;

public interface StoreExplorer {
    Schedule getOpeningHours(Store store) throws StoreNotFoundException;
    Set<Discount> getOffers(Store store) throws StoreNotFoundException;
}
