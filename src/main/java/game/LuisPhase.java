package game;

import animantia.Luis;
import animantia.PlayerAttackType;
import interfaces.AttackableByLuis;

/**
 * LuisPhase is the phase used when the controller owner is Luis.
 *
 * @author Diego Zuniga.
 */
public class LuisPhase extends PlayersPhase{

    /**
     * Chooses an attackable enemy by Marcos from a list and tries to perform
     * a specific attack.
     *
     * @param targetIndex Index from the attackable list.
     * @param anAttack Attack to perform.
     */
    @Override
    public void attack(int targetIndex, PlayerAttackType anAttack){
        Luis attacker = (Luis) controller.getOwner();
        AttackableByLuis attacked = controller.getListOfGSs().get(targetIndex);
        attacker.attack(attacked, anAttack);
        System.out.println(verboseAttack(attacker, attacked));
        if (attacked.isKO()){
            controller.getListOfGSs().remove(targetIndex);
        }
    }

    /**
     * Checks if Luis is alive.
     *
     * @return true if yes; false if not.
     */
    @Override
    public boolean exists(){
        return Luis.isAlive();
    }
}