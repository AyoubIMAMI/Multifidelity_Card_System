package fr.polytech.interfaces.store;
import java.util.Date;
import fr.polytech.exceptions.IllegalDateException;

public interface StatsExplorer {
    /**
     * Give the total cost of the discount operation since the beginning.
     * @return cost of the operation.
     */
    double getOperationCost();

    /**
     * Give the total cost of the discount operation since a given date.
     * @param date The starting date.
     * @return The cost of the operation.
     */
    double getOperationCost(Date date) throws IllegalDateException;


    /**
     * Give the number of points used since the beginning.
     * @return Number of generated points.
     */
    int getUsedPoints();

    /**
     * Give the number of points used since a given date.
     * @param date The starting date.
     * @return
     * @throws IllegalDateException
     */
    int getUsedPoints(Date date) throws IllegalDateException;
}
