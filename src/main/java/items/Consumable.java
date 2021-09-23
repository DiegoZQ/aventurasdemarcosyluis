package items;
import animantia.Player;

/**
 * This interface gives an object the quality of being consumable like a food or a drink.
 * It shouldn't be implemented to a class without a quantity variable used to count the
 * number of uses of an item before it runs out.
 *
 * @author Diego Zuniga.
 */
public interface Consumable {
    /**
     * Reduces by one the quantity of the object.
     */
    void consume();
    /**
     * Increases by one the quantity of the object.
     */
    void add();
    /**
     * Checks if an object is available (or not).
     *
     * @return true if quantity of the object is higher than one;
     *         false otherwise.
     */
    boolean isAvailable();
    /**
     * Gives a specific effect to a player who uses this object.
     *
     * @param aPlayer who gets the effect.
     */
    void giveEffect(Player aPlayer);
}