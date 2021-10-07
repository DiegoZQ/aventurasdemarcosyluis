package interfaces;

/**
 * This interface is for all attackable objects.
 *
 * @author Diego Zuniga.
 */
public interface CanBeAttacked{
    /**
     * Checks if it's knockout.
     *
     * @return true if its hit points are equal to zero;
     *         false otherwise.
     */
    boolean isKO();
}