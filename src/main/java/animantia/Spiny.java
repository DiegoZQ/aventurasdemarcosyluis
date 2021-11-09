package animantia;

import interfaces.AttackableByLuis;
import interfaces.IPlayer;
import static animantia.PlayerAttackType.SALTO;

/**
 * Spiny is a subclass of AbstractEnemy used to create a defense-focused Enemy
 * attackable by all Players and inmune to Player Attack Type SALTO because
 * of the spikes in its shell.
 *
 * @author Diego Zuniga.
 */
public final class Spiny extends AbstractEnemy implements AttackableByLuis {

    /**
     * Creates a Spiny.
     */
    public Spiny() {
        super(80, 4, 125, 1);
    }

    /**
     * Receives the damage for being attacked by Marcos unless the attack performed by Marcos
     * is SALTO, in that case Marcos receives damage equals to 5% of his maximum hp.
     */
    @Override
    public void attackedByMarcos(Marcos aMarcos, PlayerAttackType anAttack) {
        if (anAttack == SALTO) {
            aMarcos.receiveDamage(aMarcos.getMaxHpPercentage(5));
        } else {
            super.attackedByMarcos(aMarcos, anAttack);
        }
    }

    /**
     * Receives the damage for being attacked by Luis unless the attack performed by Luis
     * is SALTO, in that case Luis receives damage equals to 5% of his maximum hp.
     */
    @Override
    public void attackedByLuis(Luis aLuis, PlayerAttackType anAttack) {
        if (anAttack == SALTO) {
            aLuis.receiveDamage(aLuis.getMaxHpPercentage(5));
        } else {
            this.receiveDamage(anAttack.getK() * aLuis.getAtk() / this.getDef());
        }
    }

    @Override
    public void attack(IPlayer aPlayer) {
        if (this.canAttack(aPlayer)) {
            aPlayer.attackedBySpiny(this);
        }
    }

    /**
     * Converts into a string specific stats like lvl and hp, plus
     * the class type Spiny.
     *
     * @return the string.
     */
    @Override
    public String toString(){
        return "Spiny " + super.toString();
    }
}