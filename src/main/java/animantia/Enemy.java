package animantia;

import types.EnemyAttackType;
import types.EnemyType;
import java.util.concurrent.ThreadLocalRandom;
import static animantia.AttackTable.EnemyAttackTable;

/**
 * Enemy is the class creator of every hostile creatures controlled by PC. It was created
 * to be an obstacle for the players. It has implemented methods to attack Players, and fields (like
 *  difficulty and power) to increase the hardness of the following enemies created each time
 *  an Enemy is knocked out.
 *
 * @author Diego Zuniga.
 */
public final class Enemy extends AbstractAnimantia<EnemyType, EnemyAttackType, Player>  {
    /**
     * A static double value used to set the upper bound of the next enemy stats.
     */
    private static double difficulty=1;
    /**
     * A static int value used to set the next Enemy stats.
     */
    private static int power=1;
    /**
     * Creates a new Enemy using parameters like attack, defense maximum hit points and level.
     * It also set the value of the {@link #power} to a random number between
     * {@link #difficulty}-3 (or 1 if {@link #difficulty} is lower than 4) and {@link #difficulty}.
     *
     * @param ATK attack.
     * @param DEF defense.
     * @param HP_MAX maximum hit points
     * @param LVL level.
     */
    private Enemy(int ATK, int DEF, int HP_MAX, int LVL){
        super(ATK,DEF,HP_MAX,LVL);
        power = ThreadLocalRandom.current().nextInt(Math.max(1,(int)difficulty-3), (int)difficulty + 1);
    }
    /**
     * Creates a new Enemy using an EnemyType to set its base stats in the constructor
     * {@link #Enemy(int, int, int, int)} and weights those stats by the {@link #power} generated
     * in the last Enemy created using this constructor. It also sets its type to identify this Enemy when is attacking
     * or being attacked. If there have been no previously created enemies
     * then the {@link #power} value for this Enemy is 1.
     *
     * @param anEnemy Enemy type.
     */
    public Enemy(EnemyType anEnemy){
        this(power*anEnemy.getBaseAtk(),power*anEnemy.getBaseDef(),power*anEnemy.getBaseMaxHp(),power);
        setType(anEnemy);
    }
    /**
     * Gets the {@link #difficulty}.
     *
     * @return the difficulty.
     */
    private static double getDifficulty(){
        return Enemy.difficulty;
    }
    /**
     * Sets the {@link #difficulty}.
     *
     * @param difficulty to set.
     */
    private static void setDifficulty(double difficulty){
        Enemy.difficulty = difficulty;
    }
    /**
     * Sets the {@link #power}.
     *
     * @param power to set.
     */
    public static void setPower(int power){
        Enemy.power = power;
    }
    /**
     * Checks if it's Knock out.
     * If it is, increases the difficulty by 0.5.
     *
     * @return true if its hit points are equal to zero;
     *         false otherwise.
     */
    @Override
    public boolean isKO(){
        if (super.isKO()){
            setDifficulty(getDifficulty()+0.5);
        }
        return super.isKO();
    }
    /**
     * Gets the raw damage from an attack, which is the damage without the attacked defense
     * applied in the attack formula.
     *
     * @param anAttack Attack chosen from all possible attacks that the Enemy can do.
     * @return Raw damage.
     */
    @Override
    protected double getRawDamage(EnemyAttackType anAttack){
        return anAttack.getK()*getAtk()*getLvl();
    }
    /**
     * Checks if the Enemy and the Player aren't Knocked out to perform the attack.
     * It also checks in the EnemyAttackTable if the Enemy type is allowed to attack
     * the Player type.
     *
     * @param aPlayer A Player to be attacked.
     * @return true if the Enemy can attack the Player;
     *         false otherwise.
     */
    @Override
    protected boolean canAttack(Player aPlayer){
        boolean isAttackable = EnemyAttackTable[aPlayer.getType().getIndex()][getType().getIndex()];
        return !isKO() && !aPlayer.isKO() && isAttackable;
    }
    /**
     * Attacks a Player lowering his hit points using a specific attack when {@link #canAttack} is true.
     * If the Player is Invincible, the resulting damage is zero.
     *
     * @param aPlayer Player who gets its hit point lowered.
     * @param anAttack Attack chosen from all possible attacks that the Enemy can do.
     */
    @Override
    public void attack(Player aPlayer, EnemyAttackType anAttack){
        if (canAttack(aPlayer)){
            int damage = (int)(getRawDamage(anAttack)/aPlayer.getDef());
            aPlayer.setHp(aPlayer.getHp()-damage);
        }
    }
}