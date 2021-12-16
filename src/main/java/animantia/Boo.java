package animantia;

import interfaces.*;
import static animantia.PlayerAttackType.MARTILLO;

/**
 * Boo is a subclass of AbstractEnemy used to create an attack-focused Enemy
 * attackable by Marcos and inmune to Player Attack Type MARTILLO. Enemies
 * created in this class will only attack Luis.
 *
 * @author Diego Zuniga.
 */
public final class Boo extends AbstractEnemy {

    /**
     * Creates a Boo.
     */
    public Boo() {
        super(100,2,75);
    }

    /**
     * Receives the damage for being attacked by Marcos unless the attack performed by Marcos
     * is MARTILLO.
     *
     */
    @Override
    public void attackedByMarcos(Marcos aMarcos, PlayerAttackType anAttack){
        if (anAttack != MARTILLO) {
            super.attackedByMarcos(aMarcos, anAttack);
        }
    }

    /**
     * Checks if {@link #canAttack(CanMove)} is true.
     * If it is, then attacks the Player lowering its hit points.
     *
     * @param aPlayer Player to be attacked.
     */
    public void attack(AttackableByBoo aPlayer){
        if (this.canAttack(aPlayer)){
            aPlayer.attackedByBoo(this);
        }
    }

    /**
     * Converts into a string specific stats like lvl and hp, plus
     * the class type Boo.
     *
     * @return the string.
     */
    @Override
    public String toString(){
        return "Boo " + super.toString();
    }
}