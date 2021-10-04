package types;

/**
 * Player attack types that can use a Player to attack an Enemy with their respective
 * {@link #k}, {@link #energy}  and {@link #accuracy} of the attack.
 *
 * @author Diego Zuniga.
 */
public enum PlayerAttackType {
    /**
     * A jump attack.
     */
    SALTO(1, 1, 1),
    /**
     * A hammer attack.
     */
    MARTILLO(1.5, 2, 0.75);
    /**
     * A double constant used in attack formula.
     */
    private final double k;
    /**
     * An int number which represent the cost to Players PF when used.
     */
    private final int energy;
    /**
     * A double number which represent the probably to hit an Enemy.
     */
    private final double accuracy;
    /**
     * Creates a Player attack type using a {@link #k}, {@link #energy}, and {@link #accuracy}.
     *
     * @param k a constant k.
     * @param energy an energy cost.
     * @param accuracy accuracy to hit the target.
     */
    PlayerAttackType(double k, int energy, double accuracy){
        this.k = k;
        this.energy = energy;
        this.accuracy = accuracy;
    }
    /**
     * Gets the {@link #k}.
     *
     * @return the constant k.
     */
    public double getK(){
        return k;
    }
    /**
     * Gets the {@link #energy}.
     *
     * @return the energy cost.
     */
    public int getEnergy(){
        return energy;
    }
    /**
     * Gets the {@link #k}.
     *
     * @return the accuracy to hit the target.
     */
    public double  getAccuracy(){
        return accuracy;
    }
}