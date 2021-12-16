package animantia;

import interfaces.IEnemy;
import static java.lang.Math.pow;

/**
 * AbstractEnemy is the class creator of every hostile creatures controlled by PC. It was created
 * to be an obstacle for the players. It has implemented methods to attack Players, and has fields
 * like power to increase the hardness of the following Enemies created.
 *
 * @author Diego Zuniga.
 */
public abstract class AbstractEnemy extends AbstractAnimantia implements IEnemy {

    /**
     * A static int value used to set the next Enemy stats.
     */
    private static int power=0;

    /**
     * Creates a new Enemy using parameters like attack, defense maximum hit points and level
     * weighted by the actual Enemies {@link #power}.
     *
     * @param ATK attack.
     * @param DEF defense.
     * @param HP_MAX maximum hit points
     */
    protected AbstractEnemy(int ATK, int DEF, int HP_MAX){
        super((int)(ATK * pow(1.15, power)),(int)(DEF * pow(1.15, power)),
                (int)(HP_MAX * pow(1.15, power)),power);
    }

    /**
     * Increases the {@link #power} by 1.
     */
    public static void increasePower(){
        power++;
    }

    /**
     * Sets power back to its initial value.
     */
    public static void resetPower(){
        AbstractEnemy.power = 0;
    }

    @Override
    public void attackedByMarcos(Marcos aMarcos, PlayerAttackType anAttack){
        this.receiveDamage(anAttack.getK() * aMarcos.getAtk() / this.getDef());
    }
}