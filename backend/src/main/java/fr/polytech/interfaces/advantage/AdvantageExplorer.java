package fr.polytech.interfaces.advantage;


import fr.polytech.entities.Advantage;
import fr.polytech.entities.item.Discount;

import java.util.List;
import java.util.Optional;

public interface AdvantageExplorer {

    Optional<Advantage> VerifyAdvantage(Long advantageID);
}
