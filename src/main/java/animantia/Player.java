package animantia;

import items.*;
import types.PlayerAttackType;
import types.PlayerType;
import java.util.Random;
import static animantia.AttackTable.PlayerAttackTable;

/**
 * Player is the class creator of every playable creatures controlled by a user. It was created
 * to make the user the protagonist of the game. It has implemented methods to attack Enemies, level up, use animantia.player.items,
 * and a shared inventory between the Players to get and use those Items in battle against Enemies. It also
 * has new fields like {@link #fp}, fp max, {@link #exp}, {@link #invincible} and some cheats used to make tests.
 *
 * @author Diego Zuniga.
 */
public final class Player extends AbstractAnimantia<PlayerType, PlayerAttackType, Enemy> {
    private final int fpMax;
    /**
     * Int value needed to perform an attack with a Player.
     */
    private int fp;
    /**
     * Int value required to level up.
     */
    private int exp;
    /**
     * Boolean value to get zero damage from an Enemy when true.
     */
    private boolean invincible = false;
    /**
     * Boolean value to hit every attack when true.
     */
    private boolean perfectPrecision = false;
    /**
     * Boolean value to don't lose FP when perform an attack.
     */
    private boolean infiniteEnergy = false;
    /**
     * AbstractItem array used as a shared Inventory between the players to get and use Items.
     * It has initialized three items with quantity zero.
     */
    private static final AbstractItem[] inventory = new AbstractItem[]{new Star(),new RedMushroom(), new HoneySyrup()};
    /**
     * Creates a new Player using parameters like attack, defense maximum hit points, maximum {@link #fp} and level.
     * It also sets the fp equal to fpMax and the initial exp equal to zero.
     *
     * @param ATK attack.
     * @param DEF defense.
     * @param HP_MAX maximum hit points.
     * @param FP_MAX maximum fight points.
     * @param LVL level.
     */
    private Player(int ATK, int DEF, int HP_MAX, int FP_MAX, int LVL){
        super(ATK, DEF, HP_MAX, LVL);
        fpMax = FP_MAX;
        fp = FP_MAX;
        exp = 0;
    }
    /**
     * Creates a new Player using an PlayerType to set its base stats in the constructor
     * {@link #Player(int, int, int, int, int)} and its type to identify this Player when is attacking
     * or being attacked. It also sets the fpMax and level equal to 4 and 1
     * respectively.
     *
     * @param aPlayer Player type.
     */
    public Player(PlayerType aPlayer){
        this(aPlayer.getBaseAtk(), aPlayer.getBaseDef(), aPlayer.getBaseMaxHp(), 4,1);
        setType(aPlayer);
    }
    /**
     * Gets the maximum {@link #fp}.
     *
     * @return the maximum fp.
     */
    public int getFpMax(){
        return fpMax;
    }
    /**
     * Gets the {@link #fp}.
     *
     * @return the fight points.
     */
    public int getFp(){
        return fp;
    }
    /**
     * Sets the {@link #fp} with the restriction than
     * fp setter can't be higher than fpMax except when the Player
     * has {@link #infiniteEnergy} and FP to set is lower than Player's fp.
     *
     * @param FP fight points.
     */
    public void setFp(int FP){
        if (!infiniteEnergy || getFp()<FP){
            fp = Math.min(FP, getFpMax());
        }
    }
    /**
     * Checks if the player is {@link #invincible}.
     *
     * @return true if the player is invincible;
     *         false otherwise.
     */
    private boolean isInvincible(){
        return invincible;
    }
    /**
     * Sets the boolean value of {@link #invincible}.
     *
     * @param aBool true to activate; false to desactivate.
     */
    public void setInvincible(boolean aBool){
        invincible=aBool;
    }
    /**
     * Sets the boolean value of {@link #perfectPrecision}.
     *
     * @param aBool true to activate; false to desactivate.
     */
    public void setPerfectPrecision(boolean aBool){
        perfectPrecision=aBool;
    }
    /**
     * Sets the boolean value of {@link #infiniteEnergy}.
     *
     * @param aBool true to activate; false to desactivate.
     */
    public void setInfiniteEnergy(boolean aBool){
        infiniteEnergy=aBool;
    }
    /**
     * Sets hit points to Player except when Player is invincible
     * and HP to set is lower than Player's actual hp.
     *
     * @param HP hit points.
     */
    @Override
    public void setHp(int HP){
        if (!isInvincible() || getHp()<HP){
            super.setHp(HP);
        }
    }
    /**
     * Increases every stat of the Player and sets its hp and fp to its maximum available.
     */
    private void levelUp(){
        setAtk((int)(getAtk()*(1+1.0/getLvl())));
        setDef((int)(getDef()*(1+1.0/getLvl())));
        setHpMax((int)(getHpMax()*(1+1.0/getLvl())));
        setHp(getHpMax());
        fp=fpMax;
        setLvl(getLvl()+1);
    }
    /**
     * {@link #levelUp} n times.
     *
     * @param n Quantity of levels to level up.
     */
    public void levelUp(int n){
        for (int i=0;i<n;i++){
            levelUp();
        }
    }
    /**
     * {@link #levelUp} a Player if it has enough exp to do it.
     */
    public void tryLevelUp(){
        while (exp>=2){
            levelUp();
            exp -= 2;
        }
    }
    /**
     * Checks if the Player has {@link #perfectPrecision} to return true. Otherwise, checks if an attack
     * hits the target using the attack accuracy and comparing it with a
     * random double number between 0 and 1.
     *
     * @param anAttack Attack chosen from all possible attacks that a Player can do.
     * @return true if the Player has perfectPrecision or hits;
     *         false otherwise.
     */
    public boolean hit(PlayerAttackType anAttack){
        if (perfectPrecision){
            return true;
        }
        double rand = new Random().nextDouble();
        return anAttack.getAccuracy() >= rand;
    }
    /**
     * Gets the raw damage from an attack, which is the damage without the attacked defense
     * applied in the attack formula.
     *
     * @param anAttack Attack chosen from all possible attacks that the Player can do.
     * @return Raw damage.
     */
    @Override
    public double getRawDamage(PlayerAttackType anAttack){
        return anAttack.getK()*getAtk()*getLvl();
    }
    /**
     * Checks if the Player and the Enemy aren't Knocked out to perform the attack.
     * It also checks in the PlayerAttackTable if the Player type is allowed to attack
     * the Enemy type.
     *
     * @param anEnemy Enemy to be attacked.
     * @return true if the Player can attack the Enemy;
     *         false otherwise.
     */
    @Override
    protected boolean canAttack(Enemy anEnemy){
        boolean isAttackable = PlayerAttackTable[getType().getIndex()][anEnemy.getType().getIndex()];
        return !isKO() && !anEnemy.isKO() && isAttackable;
    }
    /**
     * Check if the Player {@link #canAttack} the enemy and has enough {@link #fp} to perform the attack.
     *
     * @param anEnemy Enemy to be attacked.
     * @param anAttack Attack chosen from all possible attacks that a Player can do.
     * @return true if the Player can attack the Enemy using anAttack;
     *         false otherwise.
     */
    private boolean canAttack(Enemy anEnemy, PlayerAttackType anAttack){
        return this.canAttack(anEnemy) && getFp()>=anAttack.getEnergy();
    }
    /**
     * Attacks an Enemy lowering his hit points using a specific attack when
     * {@link #canAttack(Enemy, PlayerAttackType)} is true.
     * If the attack is not hit, nothing else happens. Also, if the Enemy
     * is inmune to the attack the resulting damage is zero and the Player gets penalized (or not).
     * Plus if the enemy gets Knocked out after the attack, the Enemy difficulty increases
     * and the Player gets exp.
     *
     * @param anEnemy Enemy to be attacked.
     * @param anAttack Attack chosen from all possible attacks that a Player can do.
     */
    @Override
    public void attack(Enemy anEnemy, PlayerAttackType anAttack){
        if (canAttack(anEnemy, anAttack)){
            if (hit(anAttack)){
                int damage = (int)(anEnemy.getType().beAttackedBy(this, anAttack)/anEnemy.getDef());
                anEnemy.setHp(anEnemy.getHp()-damage);
                if (anEnemy.isKO()){
                    exp++;
                }
            }
            setFp(getFp()-anAttack.getEnergy());
        }
    }
    /**
     * Uses an item from the Players inventory reducing its quantity by one
     * and giving a specific effect to the user (if the Item is available);
     * Otherwise does nothing.
     *
     * @param anItem Item to be used.
     */
    public void useItem(Items anItem){
        if (inventory[anItem.getIndex()].isAvailable()){
            inventory[anItem.getIndex()].consume();
            inventory[anItem.getIndex()].giveEffect(this);
        }
    }
    /**
     * Increases the quantity of the Item by one in the Players Inventory.
     *
     * @param anItem Item which quantity is increased by one.
     */
    public void getItem(Items anItem){
        inventory[anItem.getIndex()].add();
    }
}