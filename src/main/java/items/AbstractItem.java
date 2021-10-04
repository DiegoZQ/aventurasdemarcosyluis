package items;

import animantia.Player;

/**
 * AbstractItem is an Abstract class, superclass of every usable item in Players inventory.
 * It determines the methods and variables that any item must have; Methods like consume or
 * giveEffect when an object from this class is used. It also has a {@link #quantity}
 * field.
 *
 * @author Diego Zuniga.
 */
public abstract class AbstractItem{
    /**
     * Int value which determines how many times an item can be used to get its effects.
     */
    private int quantity;
    /**
     * Creates an Item with quantity zero.
     */
    protected AbstractItem(){
        quantity = 0;
    }
    /**
     * Reduces by one the quantity of the Item.
     */
    public void consume(){
        quantity--;
    }
    /**
     * Increases by one the quantity of the Item.
     */
    public void add(){
        quantity++;
    }
    /**
     * Checks if an Item is available (or not).
     *
     * @return true if quantity of the Item is higher than one;
     *         false otherwise.
     */
    public boolean isAvailable(){
        return quantity > 0;
    }
    /**
     * Gives a specific effect to a Player who uses this Item.
     *
     * @param aPlayer who gets the effect.
     */
    public abstract void giveEffect(Player aPlayer);
}//169