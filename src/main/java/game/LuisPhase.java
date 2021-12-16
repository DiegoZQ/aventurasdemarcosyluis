package game;

import animantia.Luis;
import animantia.PlayerAttackType;
import interfaces.AttackableByLuis;
import interfaces.IEnemy;

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
        Luis attacker = Luis.getInstance();
        AttackableByLuis attacked = controller.getListOfGSs().get(targetIndex);
        attacker.attack(attacked, anAttack);
        controller.getOut().println(verboseAttack(attacker, attacked));
        if (attacked.isKO()){controller.getListOfGSs().remove(targetIndex);
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

    @Override
    public Lista<IEnemy> getListOfTargets() {
        return new Lista<>(controller.getListOfGSs());
    }
}