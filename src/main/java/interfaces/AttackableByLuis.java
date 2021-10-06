package interfaces;

import animantia.Luis;
import animantia.PlayerAttackType;

/**
 * This interface is for all Enemies that can be attacked by Luis.
 *
 * @author Diego Zuniga.
 */
public interface AttackableByLuis extends CanBeAttacked{
    /**
     * Receives the damage for being attacked by Luis.
     *
     * @param aLuis a Luis.
     * @param anAttack Attack that Luis can perform.
     */
    void attackedByLuis(Luis aLuis, PlayerAttackType anAttack);
}