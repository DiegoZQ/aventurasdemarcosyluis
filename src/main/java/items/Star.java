package items;

import animantia.Player;

/**
 * Star is a final class that's going to representate a usable Item in Players
 * inventory with the property to make the user "Invencible", which means
 * the Player cannot be damaged by an Enemy.
 *
 * @author Diego Zuniga.
 */
public final class Star extends AbstractItem {
    public Star(){
        super();
    }
    /**
     * Makes a player invencible.
     *
     * @param aPlayer who gets invencible.
     */
    @Override
    public void giveEffect(Player aPlayer){
        aPlayer.setInvincible(true);
    }
}