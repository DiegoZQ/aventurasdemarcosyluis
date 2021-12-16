package game;

import interfaces.AttackableByLuis;
import interfaces.IPlayer;
import java.util.Random;

/**
 * GSPhase is the phase used when the controller owner is Goomba or Spiny.
 *
 * @author Diego Zuniga.
 */
public class GSPhase extends Phase{

    /**
     * Tries to attack a random player alive.
     */
    @Override
    public void attack(){
        this.attack(new Random().nextInt(controller.getListOfPlayers().size()));
    }

    /**
     * Tries to attack a specific player alive.
     */
    @Override
    public void attack(int targetIndex){
        AttackableByLuis attacker = getOwner();
        IPlayer attacked = controller.getListOfPlayers().get(targetIndex);
        attacker.attack(attacked);
        controller.getOut().println(verboseAttack(attacker, attacked));
    }

    /**
     * Checks if there is a Goomba or Spiny alive.
     *
     * @return true if yes; false if not.
     */
    @Override
    public boolean exists(){
        return controller.getListOfGSs().size() != 0;
    }

    /**
     * Gets the number of Goombas and Spinies alive.
     *
     * @return The number of Goombas and Spinies alive.
     */
    @Override
    public int getSize(){
        return controller.getListOfGSs().size();
    }

    @Override
    public AttackableByLuis getOwner(){
        return controller.getListOfGSs().get(controller.getPhaseTurn());
    }
}