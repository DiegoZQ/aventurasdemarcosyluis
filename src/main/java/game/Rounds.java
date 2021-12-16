package game;

import java.util.Arrays;
import java.util.List;


/**
 * Class creator of the array of rounds playable in the game, with specific enemies and items.
 *
 * @author Diego Zuniga.
 */
public class Rounds {

    /**
     * List of rounds.
     */
    private final List<Round> rounds;

    /**
     * Int value which follows the actual round in the list of rounds.
     */
    private int roundIndex;

    /**
     * Creates a new phases initializing the order of the phases and setting the
     * actualPhaseIndex to zero.
     */
    public Rounds(){
        rounds = Arrays.asList(new Round(3, 3, 3),
                new Round(3, 1, 1), new Round(5, 1 ,1),
                new Round(5, 1, 1), new Round(6, 1, 1));
        roundIndex = -1;
    }

    /**
     * Gets the next round.
     *
     * @return the next round.
     */
    public Round getNextRound(){
        roundIndex++;
        return rounds.get(roundIndex);
    }

    /**
     * Gets round index.
     *
     * @return round index.
     */
    public int getRoundIndex(){
        return roundIndex;
    }
}