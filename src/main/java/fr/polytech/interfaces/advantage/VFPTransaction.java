package fr.polytech.interfaces.advantage;

import fr.polytech.pojo.Advantage;

public interface VFPTransaction {
    boolean tryUseAdvantage(String username, Advantage advantage);
}
