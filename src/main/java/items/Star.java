package items;

import interfaces.IPlayer;

/**
 * Star is class that represents a usable Item in Players inventory
 * with the property to make the user "Invencible", which means
 * the Player cannot be damaged by an Enemy.
 *
 * @author Diego Zuniga.
 */
public final class Star extends AbstractItem{
    /**
     * Creates a Star item with quantity equals to zero.
     */
    public Star(){
        super();
    }
    /**
     * Makes a Player invencible.
     *
     * @param aPlayer Player who gets invencible.
     */
    @Override
    public void giveEffect(IPlayer aPlayer){
        aPlayer.setInvincible(true);
    }
}