package animantia;

import interfaces.AttackableByGoombaAndSpiny;
import interfaces.AttackableByLuis;
import interfaces.CanBeAttacked;

/**
 * Goomba is a subclass of AbstractEnemy used to create a generic Enemy
 * attackable by all Players.
 *
 * @author Diego Zuniga.
 */
public final class Goomba extends AbstractEnemy implements AttackableByLuis{
    /**
     * Creates a Goomba.
     */
    public Goomba() {
        super(20,4,100,1);
    }
    @Override
    public void attackedByLuis(Luis aLuis, PlayerAttackType anAttack){
        this.receiveDamage(anAttack.getK() * aLuis.getAtk() / this.getDef());
        if (this.isKO()){
            increaseDifficulty(0.5);
            aLuis.receiveExp(1);
        }
    }
    /**
     * Checks if {@link #canAttack(CanBeAttacked)} is true.
     * If it is, then attacks the Player lowering its hit points.
     *
     * @param aPlayer Player to be attacked.
     */
    public void attack(AttackableByGoombaAndSpiny aPlayer){
        if (this.canAttack(aPlayer)){
            aPlayer.attackedByGoomba(this);
        }
    }
}