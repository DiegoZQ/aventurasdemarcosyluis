package items;

import interfaces.Consumable;

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

    @Override
    public int getQuantity(){
        return quantity;
    }

    /**
     * Checks if two Items are the same.
     *
     * @param o Item to compare
     * @return true if both have the same class and same quantity;
     *         false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractItem that = (AbstractItem) o;
        return quantity == that.quantity;
    }

    @Override
    public String toString() {
        return "quantity: " + quantity;
    }
}//171