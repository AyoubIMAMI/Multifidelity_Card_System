package fr.polytech.interfaces.advertiser;

import fr.polytech.exceptions.CustomerNotFoundException;

public interface AdvertiserManager {
    void createPromotionalOffer(int userId) throws CustomerNotFoundException;
    void createSatisfactionSurvey(String survey);
    void createReminderMessage(int userId) throws CustomerNotFoundException;
}
