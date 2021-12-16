package animantia;

import interfaces.IEnemy;

/**
 * Marcos is a subclass of AbstractPlayer used to create a defense-focused Player
 * attackable by Goomba and Spiny.
 *
 * @author Diego Zuniga.
 */
public final class Marcos extends AbstractPlayer{

    private static Marcos uniqueInstance;

    /**
     * Creates a Marcos.
     */
    private Marcos(){
        super(80,5,200,4,0);
    }

    public static Marcos getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new Marcos();
        }
        return uniqueInstance;
    }

    public static boolean isAlive() {
        return !uniqueInstance.isKO();
    }

    public static void reset(){
        uniqueInstance = new Marcos();
    }

    /**
     * Checks if canAttack and hasEnoughFpToPerform are true.
     * If it is, then tries to hit the Enemy to lower its hit points and lose
     * an amount of fp equal to attack energy cost.
     *
     * @param anEnemy Enemy to be attacked.
     * @param anAttack Attack that Marcos can perform.
     */
    public void attack(IEnemy anEnemy, PlayerAttackType anAttack){
        if (!canAttack(anEnemy)){
            throw new AssertionError("Enemy is KO!");
        }
        else if (!hasEnoughFpToPerform(anAttack)){
            throw new AssertionError("You don't have enough energy!");

        }
        else{
            if (this.hit(anAttack)){
                anEnemy.attackedByMarcos(this, anAttack);
            }
            this.subtractFp(anAttack.getEnergy());
        }
    }

    /**
     * Converts into a string specific stats like lvl, hp and fp, plus the
     * class type Luis.
     *
     * @return the string.
     */
    @Override
    public String toString(){
        return "Marcos " + super.toString();
    }
}