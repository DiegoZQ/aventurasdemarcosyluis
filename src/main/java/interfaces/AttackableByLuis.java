package interfaces;

import animantia.Luis;
import animantia.PlayerAttackType;

/**
 * This interface is for all Enemies that can be attacked by Luis and can attack IPlayer.
 *
 * @author Diego Zuniga.
 */
public interface AttackableByLuis extends IEnemy {

    /**
     * Receives the damage for being attacked by Luis.
     *
     * @param aLuis a Luis.
     * @param anAttack Attack that Luis can perform.
     */
    void attackedByLuis(Luis aLuis, PlayerAttackType anAttack);

    /**
     * Checks if the Enemy can attack that Player.
     * If it is, then attacks the Player lowering its hit points.
     *
     * @param aPlayer Player to be attacked.
     */
    void attack(IPlayer aPlayer);
}