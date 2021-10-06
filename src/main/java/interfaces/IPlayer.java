package interfaces;

import items.ItemEnum;

/**
 * This interface is for Players that can use items and receive effects
 * like hp, fp or get invincible.
 *
 * @author Diego Zuniga.
 */
public interface IPlayer extends CanBeAttacked{
    /**
     * Adds hit points to Player's hp.
     *
     * @param hp hit points to add.
     */
    void receiveHp(int hp);
    /**
     * Adds fight points to Player's fp.
     *
     * @param fp fight points to add.
     */
    void receiveFp(int fp);
    /**
     * Sets the boolean value of invincible which means
     * Player cannot get damaged from any when it's set to true.
     *
     * @param aBool true to activate; false to desactivate.
     */
    void setInvincible(boolean aBool);
    /**
     * Checks if the user can use a specific item.
     *
     * @param anItem Item to be used.
     * @return true if the user isn't KO and the item is available;
     *         false otherwise.
     */
    boolean canUse(Consumable anItem);
    /**
     * Consumes an Item from Players inventory (if it's available)
     * and give its effects to itself.
     *
     * @param anItem Item to be used.
     */
    void use(ItemEnum anItem);
    /**
     * Consumes an Item from Players inventory (if it's available)
     * and give its effects to a specific target.
     *
     * @param anItem Item to be used.
     * @param target Player to get the effect.
     */
    void use(ItemEnum anItem, IPlayer target);
    /**
     * Increases by one the quantity of an Item in Players inventory.
     *
     * @param anItem Item to increase its quantity.
     */
    void get(ItemEnum anItem);
    /**
     * Increases by a specific amount the quantity of an Item in Players inventory.
     *
     * @param anItem Item to increase its quantity.
     */
    void get(ItemEnum anItem, int amount);
}