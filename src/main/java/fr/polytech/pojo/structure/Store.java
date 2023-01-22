package fr.polytech.pojo.structure;
import fr.polytech.pojo.item.FidelityProduct;

import java.util.Date;
import java.util.Set;

public class Store extends Organisation {
    private Date opening;
    private Date closure;
    private Set<FidelityProduct> offers;
}
