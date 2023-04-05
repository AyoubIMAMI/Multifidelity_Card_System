package fr.polytech.interfaces.catalog;

import java.util.Date;

import org.springframework.stereotype.Component;

import fr.polytech.exceptions.IllegalDateException;

@Component
public interface StatsExplorer {
    /**
     * Give the total cost of the discount operation since the begining.
     * @return cost of the operation
     */
    double getOperationCost();

    /**
     * Give the total cost of the discount operation since the given date.
     * @param date The starting date to check the cost of the operation
     * @return
     */
    double getOperationCost(Date date) throws IllegalDateException;
}
