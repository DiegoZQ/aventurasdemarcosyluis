package items;

import animantia.Player;

/**
 * RedMushroom is a final class that's going to representate a usable Item in Players
 * inventory with the property to heal the user.
 *
 * @author Diego Zuniga.
 */
public final class RedMushroom extends AbstractItem {
    public RedMushroom(){
        super();
    }
    /**
     * Increases the Player's hit points by 10% of his maximum hit points, or up to his
     * maximum hp if the Player's hp is above 90% of its maximum hp.
     *
     * @param aPlayer Player who gets healed.
     */
    @Override
    public void giveEffect(Player aPlayer) {
        int health = (int)(aPlayer.getHp()+aPlayer.getHpMax()*0.1);
        health = Math.min(health, aPlayer.getHpMax());
        aPlayer.setHp(health);
    }
}