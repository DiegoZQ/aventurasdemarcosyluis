package interfaces;

import animantia.Boo;

/**
 * This interface is for all objects that can be attacked by Boo.
 *
 * @author Diego Zuniga.
 */
public interface AttackableByBoo extends CanMove {

    /**
     * Receives the damage for being attacked by Boo.
     *
     * @param aBoo a Boo.
     */
    void attackedByBoo(Boo aBoo);
}//194