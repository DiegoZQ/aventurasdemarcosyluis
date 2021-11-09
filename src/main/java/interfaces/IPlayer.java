package interfaces;

import animantia.Goomba;
import animantia.Spiny;
import items.ItemEnum;

/**
 * This interface is for objects that can use items, receive effects and be attacked by goomba and spiny.
 *
 * @author Diego Zuniga.
 */
public interface IPlayer extends CanMove {

    /**
     * Gets a specific percentage of Player's MaxHp.
     *
     * @param percentage percentage to get.
     * @return percentage of Player's MaxHp.
     */
    int getMaxHpPercentage(double percentage);

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
     * Receives the damage for being attacked by Goomba.
     *
     * @param aGoomba a Goomba.
     */
    void attackedByGoomba(Goomba aGoomba);

    /**
     * Receives the damage for being attacked by Spiny.
     *
     * @param aSpiny a Spiny.
     */
    void attackedBySpiny(Spiny aSpiny);

    /**
     * Consumes an Item from Players chest (if it's available)
     * and give its effects to a specific target.
     *
     * @param anItem Item to be used.
     * @param target Player to get the effect.
     */
    void use(IPlayer target, ItemEnum anItem);

    /**
     * Checks if a Player doesn't have its fight points up to its maximum.
     *
     * @return true if Player's fp are lower than Player's maxFp;
     *         false otherwise.
     */
    boolean isTired();
}