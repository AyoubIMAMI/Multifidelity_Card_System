package fr.polytech.pojo.structure;
import fr.polytech.pojo.item.FidelityProduct;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class Store extends Organisation {
    HashMap<String,String> Calendar;
    private Set<FidelityProduct> offers;
}
