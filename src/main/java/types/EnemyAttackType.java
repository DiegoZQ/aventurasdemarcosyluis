package types;

/**
 * Enemy attack types that can use an Enemy to attack a Player with their
 * respective {@link #k}.
 *
 * @author Diego Zuniga
 */
public enum EnemyAttackType {
    /**
     * A basic attack.
     */
    BASICATTACK(0.75);
    /**
     * A double constant used in attack formula.
     */
    private final double k;
    /**
     * Creates an Enemy attack type with and specific {@link #k}.
     *
     * @param k a constant k.
     */
    EnemyAttackType(double k){
        this.k = k;
    }
    /**
     * Gets the {@link #k}.
     *
     * @return the constant k.
     */
    public double getK(){
        return k;
    }
}//353