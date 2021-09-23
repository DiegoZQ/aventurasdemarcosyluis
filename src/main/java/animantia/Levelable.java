package animantia;

/**
 * This interface gives an object the quality of being levelable, which means it can
 * improve its stats using exp and get powerful. It shouldn't be implemented to a class without
 * variables that can be improved or without an exp system.
 *
 * @author Diego Zuniga.
 */
public interface Levelable {
    /**
     * Increases every base stat of the Object and sets them to its maximum.
     */
    void levelUp();
    /**
     * {@link #levelUp} n times.
     *
     * @param n Quantity of levels to level up.
     */
    void levelUp(int n);
    /**
     * {@link #levelUp} a Object if it has enough exp to do it.
     */
    void tryLevelUp();
}