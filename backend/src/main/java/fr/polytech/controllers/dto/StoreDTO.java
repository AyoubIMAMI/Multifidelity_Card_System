package fr.polytech.controllers.dto;

public class StoreDTO extends OrganisationDTO {
    //TODO
    //Schedule schedule;
    //TODO
    //private Set<Discount> offers;

    public StoreDTO(Long id, String storeName, String storeSiret,String password) {
        super(id, storeSiret, storeName,password);
    }


}
