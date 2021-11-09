package items;

import interfaces.IPlayer;

/**
 * HoneySyrup that represent an Item in Players inventory
 * with the property to give fight points to the user when is used.
 *
 * @author Diego Zuniga.
 */
public final class HoneySyrup extends AbstractItem{

    /**
     * Creates a HoneySyrup item with quantity equals to zero.
     */
    public HoneySyrup(){
        super();
    }

    /**
     * Increases the Player's FP by 3.
     *
     * @param aPlayer Player who gets FP.
     */
    @Override
    public void giveEffect(IPlayer aPlayer){
        aPlayer.receiveFp(3);
    }
}