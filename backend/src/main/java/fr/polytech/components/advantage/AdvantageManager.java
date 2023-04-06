package fr.polytech.components.advantage;

import fr.polytech.entities.Advantage;
import fr.polytech.entities.item.Discount;
import fr.polytech.exceptions.advantage.AdvantageNotFoundException;
import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.interfaces.advantage.AdvantageExplorer;
import fr.polytech.interfaces.advantage.AdvantageModifier;
import fr.polytech.repository.AdvantageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AdvantageManager implements AdvantageExplorer, AdvantageModifier {
    AdvantageRepository advantageRepository;

    @Autowired
    public AdvantageManager(AdvantageRepository advantageRepository){
        this.advantageRepository=advantageRepository;
    }

    @Override
    public Optional<Advantage> VerifyAdvantage(Long advantageID) {
        return advantageRepository.findById(advantageID);
    }

    @Override
    public Advantage createAdvantage(String advantageName) {
        System.out.println("AdvantageManager - createAdvantage: " + advantageName);
        Advantage advantage = new Advantage(advantageName);
        System.out.println("AdvantageManager - createAdvantage - after creating Advantage: " + advantage.getAdvantageName());
        return advantageRepository.save(advantage);
    }

    @Override
    public void deleteAdvantage(Long advantageID) throws AdvantageNotFoundException {
        try{
            advantageRepository.deleteById(advantageID);
        }
        catch(Exception e){
            throw new AdvantageNotFoundException(advantageID);
        }
    }

    @Override
    public Advantage findAdvantageById(Long advantageId) throws AdvantageNotFoundException {
        Optional<Advantage> advantage = advantageRepository.findById(advantageId);
        if (advantage.isEmpty()) throw new AdvantageNotFoundException(advantageId);
        return advantage.get();
    }
}
