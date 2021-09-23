package items;

import animantia.Player;

/**
 * HoneySyrup is a final class that's going to representate an Item in Players
 * inventory with the property to give fight points (FP) to the user.
 *
 * @author Diego Zuniga.
 */
public final class HoneySyrup extends AbstractItem {
    public HoneySyrup(){
        super();
    }
    /**
     * Increases the player's FP by 3, or up to his maximum FP
     * if the player's FP is three points or fewer of his maximum FP.
     *
     * @param aPlayer who gets FP.
     */
    @Override
    public void giveEffect(Player aPlayer) {
        int energy = aPlayer.getFp()+3;
        energy = Math.min(energy, aPlayer.getFpMax());
        aPlayer.setFp(energy);
    }
}