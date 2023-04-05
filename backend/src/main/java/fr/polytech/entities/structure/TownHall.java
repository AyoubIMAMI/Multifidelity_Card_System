package fr.polytech.entities.structure;

import fr.polytech.entities.Store;

import java.util.Objects;
import java.util.Set;

public class TownHall {
    private Set<Store> stores;

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TownHall townHall = (TownHall) o;
        return Objects.equals(stores, townHall.stores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stores);
    }

    @Override
    public String toString() {
        return "TownHall{" +
                "stores=" + stores +
                '}';
    }
}
