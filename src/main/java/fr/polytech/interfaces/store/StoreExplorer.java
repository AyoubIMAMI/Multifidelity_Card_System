package fr.polytech.interfaces.store;

import fr.polytech.exceptions.store.StoreNotFoundException;
import fr.polytech.pojo.Schedule;
import fr.polytech.pojo.item.Discount;
import fr.polytech.pojo.structure.Store;

import java.util.Set;

public interface StoreExplorer {
    Schedule getOpeningHours(Store store) throws StoreNotFoundException;
    Set<Discount> getOffers(Store store) throws StoreNotFoundException;
}
