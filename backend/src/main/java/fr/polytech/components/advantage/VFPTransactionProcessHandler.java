package fr.polytech.components.advantage;

import fr.polytech.interfaces.advantage.VFPTransaction;
import fr.polytech.pojo.Advantage;

public class VFPTransactionProcessHandler implements VFPTransaction {
    @Override
    public boolean tryUseAdvantage(String username, Advantage advantage) {
        return false;
    }
}
