package interfaces;

/**
 * This interface is for all attackable objects.
 *
 * @author Diego Zuniga.
 */
public interface CanBeAttacked{
    /**
     * Gets the maximum hit points.
     *
     * @return the maximum hit points.
     */
    int getMaxHp();
    /**
     * Gets the hit points.
     *
     * @return the hit points.
     */
    int getHp();
    /**
     * Checks if it's damaged.
     *
     * @return true if its hit points are lower than its maximum hp;
     *         false otherwise.
     */
    boolean isDamaged();
    /**
     * Checks if it's knockout.
     *
     * @return true if its hit points are equal to zero;
     *         false otherwise.
     */
    boolean isKO();
    /**
     * Checks if both attacked and attacker aren't knockout.
     *
     * @param anAttacked An attacked.
     * @return true if both aren't knockout;
     *         false otherwise.
     */
    boolean canAttack(CanBeAttacked anAttacked);
}