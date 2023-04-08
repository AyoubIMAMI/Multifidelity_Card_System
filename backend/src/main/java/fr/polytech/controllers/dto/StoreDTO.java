package fr.polytech.controllers.dto;

public class StoreDTO extends OrganisationDTO {


    public StoreDTO(Long id, String storeSiret, String storeName, String password) {
        super(id, storeSiret, storeName, password);
    }
}
