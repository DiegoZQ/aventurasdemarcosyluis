package interfaces;

/**
 * This interface is for Players that can use items and receive effects
 *
 * @author Diego Zuniga.
 */
public interface IPlayer extends CanBeAttacked{
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
     * Sets the boolean value of invincible which means
     * Player cannot get damaged from any when it's set to true.
     *
     * @param aBool true to activate; false to desactivate.
     */
    void setInvincible(boolean aBool);
}