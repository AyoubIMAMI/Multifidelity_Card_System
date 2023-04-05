package fr.polytech.interfaces.catalog;
import java.util.Date;
import fr.polytech.exceptions.IllegalDateException;

public interface StatsExplorer {
    /**
     * Give the total cost of the discount operation since the begining.
     * @return cost of the operation.
     */
    double getOperationCost();

    /**
     * Give the total cost of the discount operation since the given date.
     * @param date The starting date to check the cost of the operation.
     * @return The cost of the operation.
     */
    double getOperationCost(Date date) throws IllegalDateException;


    /**
     * Give the number of points generatess since the begining.
     * @return Nuber of generated points.
     */
    int getTotalPointGenerated();


    //int getTotalPointGenerated(Date date) throw 
}
