package animantia;

import interfaces.AttackableByMarcos;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Enemy is the class creator of every hostile creatures controlled by PC. It was created
 * to be an obstacle for the players. It has implemented methods to attack Players, and has fields
 * (like difficulty and power) to increase the hardness of the following Enemies created each time
 * an Enemy is knocked out.
 *
 * @author Diego Zuniga.
 */
public abstract class AbstractEnemy extends AbstractAnimantia implements AttackableByMarcos{
    /**
     * A static double value used to set the upper bound of the next enemy stats.
     */
    private static double difficulty=1;

    /**
     * A static int value used to set the next Enemy stats.
     */
    private static int power=1;

    /**
     * Creates a new Enemy using parameters like attack, defense maximum hit points and level
     * weighted by the actual Enemies {@link #power}. It also set the value of the new Enemies
     * {@link #power}to a random number between {@link #difficulty} - 3 (or 1 if {@link #difficulty}
     * is lower than 4) and {@link #difficulty}.
     *
     * @param ATK attack.
     * @param DEF defense.
     * @param HP_MAX maximum hit points
     * @param LVL level.
     */
    protected AbstractEnemy(int ATK, int DEF, int HP_MAX, int LVL){
        super(ATK*getPower(),DEF*getPower(),HP_MAX*getPower(),LVL*getPower());
        AbstractEnemy.setPower(ThreadLocalRandom.current().nextInt(
                Math.max(1, (int)difficulty - 3), (int)difficulty + 1));
    }

    /**
     * Gets the {@link #difficulty}.
     *
     * @return the difficulty.
     */
    private static double getDifficulty(){
        return AbstractEnemy.difficulty;
    }

    /**
     * Sets the {@link #difficulty}.
     *
     * @param difficulty to set.
     */
    private static void setDifficulty(double difficulty){
        AbstractEnemy.difficulty = difficulty;
    }

    /**
     * Increases the {@link #difficulty} by 0.5.
     */
    protected static void increaseDifficulty(){
        setDifficulty(getDifficulty() + 0.5);
    }

    /**
     * Sets difficulty back to its initial value.
     */
    public static void resetDifficulty(){
        AbstractEnemy.difficulty = 1;
    }

    /**
     * Gets the {@link #power}.
     *
     * @return the power.
     */
    private static int getPower(){
        return AbstractEnemy.power;
    }

    /**
     * Sets the {@link #power}.
     *
     * @param power to set.
     */
    private static void setPower(int power){
        AbstractEnemy.power = power;
    }

    /**
     * Sets power back to its initial value.
     */
    public static void resetPower(){
        AbstractEnemy.power = 1;
    }

    @Override
    public void attackedByMarcos(Marcos aMarcos, PlayerAttackType anAttack){
        this.receiveDamage(anAttack.getK() * aMarcos.getAtk() / this.getDef());
        if (this.isKO()){
            increaseDifficulty();
            aMarcos.receiveExp(1);
        }
    }
}