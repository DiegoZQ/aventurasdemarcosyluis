package animantia;

import interfaces.AttackableByMarcos;

/**
 * Marcos is a subclass of AbstractPlayer used to create a defense-focused Player
 * attackable by Goomba and Spiny.
 *
 * @author Diego Zuniga.
 */
public final class Marcos extends AbstractPlayer{
    /**
     * Creates a Marcos.
     */
    public Marcos(){
        super(20,10,300,4,1);
    }
    /**
     * Checks if Marcos can attack an Enemy.
     *
     * @param anEnemy Enemy to be attacked.
     * @param anAttack Attack that Marcos can perform.
     * @return true if Marcos has enough fp to perform the attack
     *         and canAttack method from superclass is true;
     *         false otherwise.
     */
    private boolean canAttack(AttackableByMarcos anEnemy, PlayerAttackType anAttack){
        return super.canAttack(anEnemy) && this.hasEnoughFpToPerform(anAttack);
    }
    /**
     * Checks if {@link #canAttack(AttackableByMarcos, PlayerAttackType)} is true.
     * If it is, then tries to hit the Enemy to lower its hit points and lose
     * an amount of fp equal to attack energy cost.
     *
     * @param anEnemy Enemy to be attacked.
     * @param anAttack Attack that Marcos can perform.
     */
    public void attack(AttackableByMarcos anEnemy, PlayerAttackType anAttack){
        if (this.canAttack(anEnemy, anAttack)){
            if (this.hit(anAttack)){
                anEnemy.attackedByMarcos(this, anAttack);
            }
            this.subtractFp(anAttack.getEnergy());
        }
    }
}