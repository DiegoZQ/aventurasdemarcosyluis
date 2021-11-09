package game;

import animantia.PlayerAttackType;
import interfaces.CanMove;
import items.ItemEnum;

public class Phase extends AbstractPhase {

    /**
     * Kind of attack used in the Enemy phase with random target.
     *
     */
    public void attack(){
        this.error();
    }

    /**
     * Kind of attack used in the Enemy phase with specific target.
     *
     */
    public void attack(int target){
        this.error();
    }

    /**
     * Kind of attack used in the Player phase.
     *
     */
    public void attack(int target, PlayerAttackType anAttack){
        this.error();
    }

    /**
     * Action realized in Player phase to get an item from a chest
     * and use it on a Player.
     *
     * @param target Player who receives the item.
     * @param anItem Item to use.
     */
    public void use(int target, ItemEnum anItem){
        this.error();
    }

    /**
     * Error thrown when it is not in the correct phase.
     */
    private void error() {
        throw new AssertionError("Wrong phase!");
    }

    /**
     * Gets the size of a specific list.
     *
     * @return 1 (by default).
     */

    public int getSize(){
        return 1;
    }

    /**
     * Checks if the specific list has elements.
     *
     * @return true (by default).
     */
    public boolean exists() {
        return true;
    }

    /**
     * Converts into a string an attack interaction with the attacker and the attacked.
     *
     * @param attacker Attacker.
     * @param attacked Attacked.
     * @return the string of interaction.
     */
    public String verboseAttack(CanMove attacker, CanMove attacked){
        return attacker.toString() + " attacked " + attacked.toString();
    }
}