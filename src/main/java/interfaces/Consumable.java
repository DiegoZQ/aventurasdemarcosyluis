package interfaces;

/**
 * This interface gives a class the property of being Consumable like real
 * food or drink.
 *
 * @author Diego Zuniga.
 */
public interface Consumable{

    /**
     * Reduces by one the quantity of the Item.
     */
    void consume();

    /**
     * Increases the Item's quantity by a specific number.
     *
     * @param quantity quantity to add.
     */
    void add(int quantity);

    /**
     * Checks if an Item is available (or not).
     *
     * @return true if quantity of the Item is higher than one;
     *         false otherwise.
     */
    boolean isAvailable();

    /**
     * Gives a specific effect to a Player who uses this Item.
     *
     * @param aPlayer who gets the effect.
     */
    void giveEffect(IPlayer aPlayer);
}