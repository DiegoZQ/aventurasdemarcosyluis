package animantia;

import interfaces.Consumable;
import items.HoneySyrup;
import items.ItemEnum;
import items.RedMushroom;

/**
 * Chest is the class in charge of serving as a shared inventory between the Players, used to save,
 * get and use items inside it.
 *
 * @author Diego Zuniga.
 */
public class Chest {

    /**
     * Array used to save the items.
     */
    private Consumable[] inventory;

    /**
     * Creates a chest initialized with a RedMushroom and a HoneySyrup.
     */
    public Chest(){
        inventory = new Consumable[]{new RedMushroom(),new HoneySyrup()};
    }

    /**
     * Adds a specific item quantity to the chest.
     *
     * @param anItem Specific item.
     * @param quantity Amount to add.
     */
    public void add(ItemEnum anItem, int quantity){
        inventory[anItem.getIndex()].add(quantity);
    }

    /**
     * Gets a specific item from the chest.
     *
     * @param anItem Specific item.
     * @return the item.
     */
    public Consumable get(ItemEnum anItem){
        return inventory[anItem.getIndex()];
    }

    /**
     * Gets the items in chest inventory.
     *
     * @return chest inventory.
     */
    public Consumable[] getItems(){
        return inventory;
    }

    /**
     * Returns the chest back to its initial values.
     */
    public void reset(){
        inventory = new Consumable[]{new RedMushroom(),new HoneySyrup()};
    }
}