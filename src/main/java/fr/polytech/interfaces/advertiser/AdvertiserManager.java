package fr.polytech.interfaces.advertiser;

import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.NotEnoughPermissionException;

public interface AdvertiserManager {
    void createPromotionalOffer(int userId) throws CustomerNotFoundException, NotEnoughPermissionException;
    void createSatisfactionSurvey();
    void createReminderMessage(int userId) throws CustomerNotFoundException, NotEnoughPermissionException;
}
