package game;

import static items.ItemEnum.HONEYSYRUP;
import static items.ItemEnum.REDMUSHROOM;

/**
 * Class creator of every round playable in the game, with their quantity of enemies per round
 * and items given to players at start.
 *
 * @author Diego Zuniga.
 */
public class Round extends AbstractPhase {

    /**
     * Number of enemies.
     */
    int enemies;
    /**
     * Number of redMushrooms.
     */
    int redMushrooms;
    /**
     * Number of honeySyrups.
     */
    int honeySyrups;

    /**
     * Creates a round with a number of enemies, redMushrooms and honeySyrups.
     *
     * @param enemies Number of enemies.
     * @param redMushrooms Number of redMushrooms.
     * @param honeySyrups Number of honeySyrups.
     */
    public Round(int enemies, int redMushrooms, int honeySyrups){
        this.enemies = enemies;
        this.redMushrooms = redMushrooms;
        this.honeySyrups = honeySyrups;
    }

    /**
     * Prepares all to start a new round. Erase remaining enemies, level up players,
     * increases the difficulty, adds items to players' chest, adds the new enemies and
     * starts with Marcos.
     */
    public void playRound(){
        controller.resetWinner();
        controller.eraseEnemies();
        controller.levelUpPlayers();
        controller.increaseDifficulty();
        controller.addItem(REDMUSHROOM, redMushrooms);
        controller.addItem(HONEYSYRUP, honeySyrups);
        controller.addEnemies(enemies);
        controller.setPhase(new MarcosPhase());
    }
}