package animantia;

import interfaces.AttackableByLuis;
import interfaces.IPlayer;

/**
 * Goomba is a subclass of AbstractEnemy used to create a generic Enemy
 * attackable by all Players.
 *
 * @author Diego Zuniga.
 */
public final class Goomba extends AbstractEnemy implements AttackableByLuis {

    /**
     * Creates a Goomba.
     */
    public Goomba() {
        super(80,3,100,1);
    }

    @Override
    public void attackedByLuis(Luis aLuis, PlayerAttackType anAttack){
        this.receiveDamage(anAttack.getK() * aLuis.getAtk() / this.getDef());
    }

    @Override
    public void attack(IPlayer aPlayer){
        if (this.canAttack(aPlayer)){
            aPlayer.attackedByGoomba(this);
        }
    }

    /**
     * Converts into a string specific stats like lvl and hp, plus
     * the class type Goomba.
     *
     * @return the string.
     */
    @Override
    public String toString(){
        return "Goomba " + super.toString();
    }
}