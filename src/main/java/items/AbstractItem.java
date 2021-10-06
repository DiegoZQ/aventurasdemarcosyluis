package items;

import interfaces.Consumable;
import interfaces.IPlayer;

/**
 * AbstractItem is an Abstract class, superclass of every usable item in Players inventory.
 * It determines the methods and variables that any item must have thanks to the implementation
 * of the Consumable interface. It also has a {@link #quantity} field to give sense of being
 * Consumable.
 *
 * @author Diego Zuniga.
 */
public abstract class AbstractItem implements Consumable{
    /**
     * Int value which determines how many times an Item can be used to get its effects.
     */
    private int quantity;
    /**
     * Creates an Item with quantity zero.
     */
    protected AbstractItem(){
        this.quantity = 0;
    }
    @Override
    public void consume(){
        this.quantity--;
    }
    @Override
    public void add(int quantity){
        this.quantity += quantity;
    }
    @Override
    public boolean isAvailable(){
        return quantity > 0;
    }
}//166