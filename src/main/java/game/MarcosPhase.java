package game;

import animantia.Marcos;
import animantia.PlayerAttackType;
import interfaces.IEnemy;

/**
 * MarcosPhase is the phase used when the controller owner is Marcos.
 *
 * @author Diego Zuniga.
 */
public class MarcosPhase extends PlayersPhase {

    /**
     * Chooses an attackable enemy by Marcos from a list and tries to perform
     * a specific attack.
     *
     * @param targetIndex Index from the attackable list.
     * @param anAttack Attack to perform.
     */
    @Override
    public void attack(int targetIndex, PlayerAttackType anAttack){
        Marcos attacker = (Marcos) controller.getOwner();
        IEnemy attacked = controller.getListOfEnemies().get(targetIndex);
        attacker.attack(attacked, anAttack);
        System.out.println(verboseAttack(attacker, attacked));
        if (attacked.isKO()){
            if (targetIndex + 1 <= controller.getListOfGSs().size()){
                controller.getListOfGSs().remove(targetIndex);
            }
            else{
                controller.getListOfBoos().remove(targetIndex);
            }
        }
    }

    /**
     * Checks if Marcos is alive.
     *
     * @return true if yes; false if not.
     */
    @Override
    public boolean exists(){
        return Marcos.isAlive();
    }
}