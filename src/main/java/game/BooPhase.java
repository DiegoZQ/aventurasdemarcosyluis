package game;

import animantia.Boo;
import animantia.Luis;

/**
 * BooPhase is the phase used when the controller owner is Boo.
 *
 * @author Diego Zuniga.
 */
public class BooPhase extends Phase{

    /**
     * Tries to attack Luis.
     */
    @Override
    public void attack(){
        Boo attacker = (Boo)controller.getOwner();
        attacker.attack(Luis.getInstance());
        System.out.println(verboseAttack(attacker, Luis.getInstance()));
    }

    /**
     * Checks if there is a Boo alive.
     *
     * @return true if yes; false if not.
     */
    @Override
    public boolean exists(){
        return controller.getListOfBoos().size() != 0;
    }

    /**
     * Gets the number of Boos alive.
     *
     * @return The number of Boos alive.
     */
    @Override
    public int getSize(){
        return controller.getListOfBoos().size();
    }
}