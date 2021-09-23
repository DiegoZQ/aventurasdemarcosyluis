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
public abstract class AbstractItem implements Consumable {
    /**
     * Int value which determines how many times an item can be used to get its effects.
     */
    private int quantity;
    /**
     * Creates an Abstract Item with quantity zero.
     */
    public AbstractItem(){
        this.quantity = 0;
    }
    public void consume(){
        this.quantity--;
    }
    public void add(){
        this.quantity++;
    }
    public boolean isAvailable(){
        return this.quantity > 0;
    }
    public abstract void giveEffect(Player aPlayer);
}