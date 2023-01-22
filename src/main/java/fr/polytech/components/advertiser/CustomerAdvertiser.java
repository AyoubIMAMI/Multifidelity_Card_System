package fr.polytech.components.advertiser;

import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.interfaces.advertiser.AdvertiserManager;

public class CustomerAdvertiser implements AdvertiserManager {
    @Override
    public void createPromotionalOffer(int userId) throws CustomerNotFoundException {

    }

    @Override
    public void createSatisfactionSurvey() {

    }

    @Override
    public void createReminderMessage(int userId) throws CustomerNotFoundException {

    }
}
