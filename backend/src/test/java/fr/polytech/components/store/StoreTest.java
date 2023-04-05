package fr.polytech.components.store;

import fr.polytech.entities.Store;
import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.store.StoreSiretAlreadyUsedException;
import fr.polytech.interfaces.store.StoreFinder;
import fr.polytech.interfaces.store.StoreRegistration;
import fr.polytech.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StoreTest {
    @Autowired
    private StoreFinder storeFinder;
    @Autowired
    private StoreRegistration storeRegistration;
    @Autowired
    private StoreRepository storeRepository;

    private String name;
    private String password;
    private String siret;


    @BeforeEach
    void setUp() {
        storeRepository.deleteAll();
        name = "PierreJardinage";
        password = "myPassword";
        siret="numSiret";
    }
    @Test
    void registrationTest() throws BadCredentialsException, StoreSiretAlreadyUsedException {
        Store store = storeRegistration.registerNewStore(name,siret, password);

        assertEquals(name, store.getName());
        assertEquals(siret, store.getSiret());
        assertEquals(password, store.getPassword());

        assertTrue(storeRepository.existsStoreBySiret(siret));
        assertEquals(1, storeRepository.count());
        assertEquals(store.getName(), storeRepository.findStoreByName(name).get().getName());

        assertEquals(siret,storeFinder.findStore(name,password).getSiret());
    }

}
