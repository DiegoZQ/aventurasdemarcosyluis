package interfaces;

import animantia.Marcos;
import animantia.PlayerAttackType;

/**
 * This interface is for all Enemies that can be attacked by Marcos.
 *
 * @author Diego Zuniga.
 */
public interface AttackableByMarcos extends CanBeAttacked{
    /**
     * Receives the damage for being attacked by Marcos.
     *
     * @param aMarcos a Marcos.
     * @param anAttack Attack that Marcos can perform.
     */
    void attackedByMarcos(Marcos aMarcos, PlayerAttackType anAttack);
}