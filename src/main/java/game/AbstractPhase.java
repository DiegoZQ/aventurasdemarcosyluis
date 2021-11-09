package game;

/**
 * AbstractPhase is the superclass of all phases used in battle.
 *
 * @author Diego Zuniga.
 */
public class AbstractPhase {

    protected GameController controller;

    /**
     * Sets the controller to this phase.
     *
     * @param controller Controller to set.
     */
    public void setController(GameController controller) {
        this.controller = controller;
    }
}//780