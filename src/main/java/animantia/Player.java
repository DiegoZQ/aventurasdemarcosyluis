package animantia;

import types.PlayerAttackType;
import items.*;
import types.PlayerType;
import java.util.Random;
import static animantia.AttackTable.PlayerAttackTable;

/**
 * Player is the class creator of every playable creatures controlled by a user. It was created
 * to make the user the protagonist of the game. It has implemented methods to attack Enemies, level up, use items,
 * and a shared inventory between the Players to get and use those Items in battle against Enemies. It also
 * has new fields like {@link #fp}, fp max, {@link #exp}, {@link #invincible} and some cheats used to make tests.
 *
 * @author Diego Zuniga.
 */
public final class Player extends AbstractAnimantia<Enemy, PlayerAttackType, PlayerType> implements Levelable {
    private int fpMax;
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
        this.fpMax = FP_MAX;
        this.fp = FP_MAX;
        this.exp = 0;
    }
    /**
     * Creates a new Player using an PlayerType to set its base stats in the constructor
     * {@link #Player(int, int, int, int, int)}. It also sets the fpMax and level equal to 4 and 1
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
        return this.fpMax;
    }
    /**
     * Gets the {@link #fp}.
     *
     * @return the fight points.
     */
    public int getFp(){
        return this.fp;
    }
    /**
     * Sets the {@link #fp}.
     *
     * @param FP fight points.
     */
    public void setFp(int FP){
        this.fp = FP;
    }
    /**
     * Checks if the player is {@link #invincible}.
     *
     * @return true if the player is invincible;
     *         false otherwise.
     */
    public boolean isInvincible(){
        return this.invincible;
    }
    /**
     * Sets the boolean value of {@link #invincible}.
     *
     * @param aBool true to activate; false to desactivate.
     */
    public void setInvincible(boolean aBool){
        this.invincible=aBool;
    }
    /**
     * Sets the boolean value of {@link #perfectPrecision}.
     *
     * @param aBool true to activate; false to desactivate.
     */
    public void setPerfectPrecision(boolean aBool){
        this.perfectPrecision=aBool;
    }
    /**
     * Sets the boolean value of {@link #infiniteEnergy}.
     *
     * @param aBool true to activate; false to desactivate.
     */
    public void setInfiniteEnergy(boolean aBool){
        this.infiniteEnergy=aBool;
    }
    public void levelUp(){
        this.setAtk((int)(this.getAtk()*(1+1/(double)getLvl())));
        this.setDef((int)(this.getDef()*(1+1/(double)getLvl())));
        this.setHpMax((int)(this.getHpMax()*(1+1/(double)getLvl())));
        this.setHp(getHpMax());
        this.fp=this.fpMax;
        this.setLvl(this.getLvl()+1);
    }
    public void levelUp(int n){
        for (int i=0;i<n;i++){
            this.levelUp();
        }
    }
    public void tryLevelUp(){
        while (this.exp>=2){
            this.levelUp();
            this.exp -= 2;
        }
    }
    /**
     * Checks if an attack hits the target using the attack accuracy and comparing it with a random double number
     * between 0 and 1.
     *
     * @param anAttack Attack chosen from all possible attacks that a Player can do.
     * @return true if the Player hits;
     *         false otherwise.
     */
    public boolean hit(PlayerAttackType anAttack){
        double rand = new Random().nextDouble();
        return anAttack.getAccuracy() >= rand;
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
    public boolean canAttack(Enemy anEnemy){
        boolean a = PlayerAttackTable[this.getType().getIndex()][anEnemy.getType().getIndex()];
        return !this.isKO() && !anEnemy.isKO() && a;
    }
    /**
     * Attacks an Enemy lowering his hit points using a specific attack when {@link #canAttack} is true and
     * the Player has enough {@link #fp}. If the attack is not hit, nothing else happens. Also, if the Enemy
     * is inmune to the attack the resulting damage is zero and the Player gets penalized (or not).
     * Plus if the enemy gets Knocked out after the attack, the Enemy difficulty increases by 0.5.
     *
     * @param anEnemy Enemy to be attacked.
     * @param anAttack Attack chosen from all possible attacks that a Player can do.
     */
    @Override
    public void attack(Enemy anEnemy, PlayerAttackType anAttack){
        if (this.canAttack(anEnemy) && this.fp>=anAttack.getEnergy()){
            if (this.hit(anAttack) || this.perfectPrecision){
                double damage = anEnemy.getType().beAttackedBy(this, anAttack);
                int reducedDamage = (int)(damage/anEnemy.getDef());
                reducedDamage = Math.min(reducedDamage, anEnemy.getHp());
                anEnemy.setHp(anEnemy.getHp()-reducedDamage);
                if (anEnemy.isKO()){
                    this.exp++;
                    Enemy.setDifficulty(Enemy.getDifficulty()+0.5);
                }
            }
            if (!this.infiniteEnergy){
                this.fp -= anAttack.getEnergy();
            }
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