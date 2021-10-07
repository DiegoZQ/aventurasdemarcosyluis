package interfaces;

import animantia.Goomba;
import animantia.Spiny;

/**
 * This interface is for all Players that can be attacked by Goomba and Spiny.
 *
 * @author Diego Zuniga.
 */
public interface AttackableByGoombaAndSpiny extends IPlayer{
    /**
     * Receives the damage for being attacked by Goomba.
     *
     * @param aGoomba a Goomba.
     */
    void attackedByGoomba(Goomba aGoomba);

    /**
     * Receives the damage for being attacked by Spiny.
     *
     * @param aSpiny a Spiny.
     */
    void attackedBySpiny(Spiny aSpiny);
}