package game;

import java.util.Arrays;
import java.util.List;

/**
 * Phases is a class used to switch between the different kind of phases defined in battle.
 *
 * @author Diego Zuniga.
 */
public class Phases extends AbstractPhase {

    /**
     * List of phases.
     */
    private final List<Phase> phases;

    /**
     * Int value which follows the actual phase in the list of phases.
     */
    private int actualPhaseIndex;

    /**
     * Creates a new phases initializing the order of the phases and setting the
     * actualPhaseIndex to zero.
     */
    public Phases(){
        phases = Arrays.asList(new MarcosPhase(), new LuisPhase(), new GSPhase(), new BooPhase());
        actualPhaseIndex = 0;
    }

    /**
     * Gets the next phase index.
     *
     * @return the next phase index.
     */
    private int getNextPhaseIndex(){
        actualPhaseIndex = (actualPhaseIndex+1)%phases.size();
        return actualPhaseIndex;
    }

    /**
     * Gets the next phase in the list of phases. If the phase doesn't exist then gets
     * the phase that follows.
     *
     * @return the next phase.
     */
    public Phase nextPhase(){
        int nextPhaseIndex;
        do{
            nextPhaseIndex = getNextPhaseIndex();
            controller.setPhase(phases.get(nextPhaseIndex));
        }while(!phases.get(nextPhaseIndex).exists());
        return phases.get(actualPhaseIndex);
    }
}