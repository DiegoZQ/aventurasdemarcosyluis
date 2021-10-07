package items;

import interfaces.IPlayer;

/**
 * RedMushroom is a class that represents a usable Item in Players
 * inventory with the property to heal the user when is used.
 *
 * @author Diego Zuniga.
 */
public final class RedMushroom extends AbstractItem{
    /**
     * Creates a RedMushroom item with quantity equals to zero.
     */
    public RedMushroom(){
        super();
    }

    /**
     * Increases the Player's hit points by 10% of his maximum hit points.
     *
     * @param aPlayer Player who gets healed.
     */
    @Override
    public void giveEffect(IPlayer aPlayer){
        aPlayer.receiveHp(aPlayer.getMaxHpPercentage(10));
    }
}