package animantia;

import interfaces.AttackableByBoo;
import interfaces.AttackableByLuis;

/**
 * Luis is a subclass of AbstractPlayer used to create an attack-focused Player
 * attackable by all Enemies. Players created in this class won't attack Boo.
 *
 * @author Diego Zuniga.
 */
public final class Luis extends AbstractPlayer implements AttackableByBoo{

    private static Luis uniqueInstance;

    /**
     * Creates a Luis.
     */
    private Luis(){
        super(130,3,150,4,1);
    }

    public static Luis getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new Luis();
        }
        return uniqueInstance;
    }

    public static boolean isAlive() {
        return !uniqueInstance.isKO();
    }

    public static void reset(){
        uniqueInstance = new Luis();
    }

    @Override
    public void attackedByBoo(Boo aBoo){
        this.receiveDamage(0.75 * aBoo.getAtk() / this.getDef());
    }

    /**
     * Checks if Luis can attack an Enemy.
     *
     * @param anEnemy Enemy to be attacked.
     * @param anAttack Attack that Luis can perform.
     * @return true if Luis has enough fp to perform the attack
     *         and canAttack method from superclass is true;
     *         false otherwise.
     */
    private boolean canAttack(AttackableByLuis anEnemy, PlayerAttackType anAttack){
        return super.canAttack(anEnemy) && this.hasEnoughFpToPerform(anAttack);
    }

    /**
     * Checks if {@link #canAttack(AttackableByLuis, PlayerAttackType)} is true.
     * If it is, then tries to hit the Enemy to lower its hit points and lose
     * an amount of fp equal to attack energy cost.
     *
     * @param anEnemy Enemy to be attacked.
     * @param anAttack Attack that Luis can perform.
     */
    public void attack(AttackableByLuis anEnemy, PlayerAttackType anAttack){
        if (this.canAttack(anEnemy, anAttack)){
            if (this.hit(anAttack)){
                anEnemy.attackedByLuis(this, anAttack);
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
        return "Luis " + super.toString();
    }
}