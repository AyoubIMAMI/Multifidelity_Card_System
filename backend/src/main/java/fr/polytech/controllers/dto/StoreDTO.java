package fr.polytech.controllers.dto;


public class StoreDTO extends OrganisationDTO {
    //TODO
    //Schedule schedule;
    //TODO
    //private Set<Discount> offers;

    public StoreDTO(String storeName, String storeSiret,String password) {
        super(storeSiret, storeName,password);
    }


}
