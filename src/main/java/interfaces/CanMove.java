package interfaces;

/**
 * This interface is for all attackable objects.
 *
 * @author Diego Zuniga.
 */
public interface CanMove {

    /**
     * Checks if it's knockout.
     *
     * @return true if its hit points are equal to zero;
     *         false otherwise.
     */
    boolean isKO();

    /**
     * Checks if it's damaged.
     *
     * @return true if its hit points are lower than its maximum hp;
     *         false otherwise.
     */
    boolean isDamaged();
}