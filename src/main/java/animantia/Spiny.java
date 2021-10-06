package animantia;

import interfaces.AttackableByGoombaAndSpiny;
import interfaces.AttackableByLuis;
import interfaces.CanBeAttacked;
import static animantia.PlayerAttackType.SALTO;

/**
 * Spiny is a subclass of AbstractEnemy used to create a defense-focused Enemy
 * attackable by all Players and inmune to Player Attack Type SALTO because
 * of the spikes in its shell.
 *
 * @author Diego Zuniga.
 */
public final class Spiny extends AbstractEnemy implements AttackableByLuis{
    /**
     * Creates a Spiny.
     */
    public Spiny(){
        super(15,10,150,1);
    }
    /**
     * Receives the damage for being attacked by Marcos unless the attack performed by Marcos
     * is SALTO, in that case Marcos receives damage equals to 5% of his maximum hp.
     *
     */
    @Override
    public void attackedByMarcos(Marcos aMarcos, PlayerAttackType anAttack){
        if (anAttack == SALTO){
            aMarcos.receiveDamage(aMarcos.getMaxHp() * 0.05);
        }
        else{
            super.attackedByMarcos(aMarcos, anAttack);
        }
    }
    /**
     * Receives the damage for being attacked by Luis unless the attack performed by Luis
     * is SALTO, in that case Luis receives damage equals to 5% of his maximum hp.
     *
     */
    @Override
    public void attackedByLuis(Luis aLuis, PlayerAttackType anAttack){
        if (anAttack == SALTO){
            aLuis.receiveDamage(aLuis.getMaxHp() * 0.05);
        }
        else{
            this.receiveDamage(anAttack.getK() * aLuis.getAtk() / this.getDef());
            if (this.isKO()){
                increaseDifficulty(0.5);
                aLuis.receiveExp(1);
            }
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
            aPlayer.attackedBySpiny(this);
        }
    }
}