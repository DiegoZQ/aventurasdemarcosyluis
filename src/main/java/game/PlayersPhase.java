package game;

import interfaces.*;
import items.ItemEnum;

/**
 * PlayersPhase is the superclass of all player phases. It has specific methods
 * that share all phases of this type.
 *
 * @author Diego Zuniga.
 */
public class PlayersPhase extends Phase {

    /**
     * Consumes a specific Item from Players chest (if it's available)
     * and give its effects to a specific target using an index in a list
     * of players alive.
     *
     * @param anItem Item to be used.
     * @param targetIndex Index of the player which receives the effect.
     */
    @Override
    public void use(int targetIndex, ItemEnum anItem){
        IPlayer player = (IPlayer) controller.getOwner();
        player.use(controller.getListOfPlayers().get(targetIndex), anItem);
        System.out.println(verboseUse(player, controller.getListOfPlayers().get(targetIndex), anItem));
    }

    /**
     * Converts into a string a use item interaction with the user and the target.
     *
     * @param user Player who perform the action.
     * @param target Player who receives the item effects.
     * @return the string of interaction.
     */
    public String verboseUse(CanMove user, CanMove target, ItemEnum anItem){
        if (user.equals(target)){
            return user.toString() + " used a " + anItem.toString() + " on himself";
        }
        return user.toString() + " used a " + anItem.toString() + " on " + target.toString();
    }
}