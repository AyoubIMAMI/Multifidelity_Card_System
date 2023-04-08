package fr.polytech.interfaces.fidelity;

import fr.polytech.entities.Customer;

public interface PointModifier {

    /**
     * Increment the points which are computed from the price
     * @param customer involved in the incrementation
     * @param price of the bought article or service
     * @return the customer involved
     */
    Customer incrementPoints(Customer customer, float price);

    /**
     * Decrement points when they are used to buy something
     * @param customer involved in the decremental
     * @param points number of points used
     * @return the customer involved
     */
    Customer decrementPoints(Customer customer, int points);
}
