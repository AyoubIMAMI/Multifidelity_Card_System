package fr.polytech.controllers.dto;

import fr.polytech.entities.structure.Organisation;


public class StoreDTO extends OrganisationDTO {
    //TODO
    //Schedule schedule;
    //TODO
    //private Set<Discount> offers;

    public StoreDTO(String storeName, String storeSiret,String password) {
        super(storeSiret, storeName,password);
    }


}
