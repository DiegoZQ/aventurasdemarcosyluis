package animantia;

import interfaces.AttackableByGoombaAndSpiny;
import interfaces.Consumable;
import interfaces.IPlayer;
import items.*;
import java.util.Random;

/**
 * Player is the class creator of every playable creatures controlled by a user. It was created
 * to make the user the protagonist of the game. It has implemented methods to attack Enemies, level up
 * and use items. It also has fields like a shared inventory between the Players to get and use those items
 * in battle against Enemies,  {@link #fp},  {@link #maxFp},  {@link #exp} and some boolean values used as cheats
 * to test attacks.
 *
 * @author Diego Zuniga.
 */
public abstract class AbstractPlayer extends AbstractAnimantia implements IPlayer, AttackableByGoombaAndSpiny{
    /**
     * Max fights points that a Player can have.
     */
    private final int maxFp;

    /**
     * Int value needed to perform an attack with a Player.
     */
    private int fp;

    /**
     * Int value required to level up.
     */
    private int exp;

    /**
     * Boolean value to get zero damage from an Enemy when is true.
     */
    private boolean invincible = false;

    /**
     * Boolean value to hit every attack when is true.
     */
    private boolean perfectPrecision = false;

    /**
     * Boolean value to don't lose FP when perform an attack when is true.
     */
    private boolean infiniteEnergy = false;

    /**
     * Consumable array used as a shared Inventory between the players to get and use Items.
     * It has initialized three different consumable Items.
     */
    private static final Consumable[] inventory = new Consumable[]{new Star(),new RedMushroom(), new HoneySyrup()};

    /**
     * Creates a new Player using parameters like attack, defense maximum hit points, maximum {@link #fp} and level.
     * It also sets the initial fp an exp equal to fpMax and zero respectively.
     *
     * @param ATK attack.
     * @param DEF defense.
     * @param MAX_HP maximum hit points.
     * @param MAX_FP maximum fight points.
     * @param LVL level.
     */
    protected AbstractPlayer(int ATK, int DEF, int MAX_HP, int MAX_FP, int LVL){
        super(ATK, DEF, MAX_HP, LVL);
        this.maxFp = MAX_FP;
        this.fp = MAX_FP;
        this.exp = 0;
    }

    @Override
    public void receiveHp(int hp){
        this.setHp(this.getHp() + hp);
    }

    @Override
    public int getMaxHpPercentage(double percentage){
        return (int)(this.getMaxHp()*percentage/100);
    }

    /**
     * Gets the maximum {@link #fp}.
     *
     * @return the maximum fp.
     */
    public int getMaxFp(){
        return this.maxFp;
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
     * Sets the fight points with the restriction than fp to set
     * can't be higher than fpMax.
     *
     * @param fp fight points.
     */
    private void setFp(int fp){
        this.fp = Math.min(fp, getMaxFp());
    }

    @Override
    public void receiveFp(int fp){
        this.setFp(this.getFp() + fp);
    }

    /**
     * Subtracts fight points to Player's fp unless it has {@link #infiniteEnergy}.
     *
     * @param fp fight points to subtract.
     */
    public void subtractFp(int fp){
        if (!infiniteEnergy){
            this.setFp(this.getFp() - fp);
        }
    }

    /**
     * Checks if a Player doesn't have its fight points up to its maximum.
     *
     * @return true if Player's fp are lower than Player's maxFp;
     *         false otherwise.
     */
    public boolean isTired(){
        return this.getFp() < this.getMaxFp();
    }

    /**
     * Check if the player has the fight points needed to perform
     * a specific attack.
     *
     * @return true if Player's fp are equal or higher than attack
     *         energy cost.
     */
    protected boolean hasEnoughFpToPerform(PlayerAttackType anAttack){
        return this.getFp() >= anAttack.getEnergy();
    }

    /**
     * Increases the Player's exp by a specific value.
     *
     * @param exp exp to increase.
     */
    public void receiveExp(int exp){
        this.exp += exp;
    }

    /**
     * Checks if the player is {@link #invincible}.
     *
     * @return true if the player is invincible;
     *         false otherwise.
     */
    private boolean isInvincible(){
        return this.invincible;
    }

    @Override
    public void setInvincible(boolean aBool){
        this.invincible = aBool;
    }

    /**
     * Sets the boolean value of {@link #perfectPrecision}.
     *
     * @param aBool true to activate; false to desactivate.
     */
    public void setPerfectPrecision(boolean aBool){
        this.perfectPrecision = aBool;
    }

    /**
     * Sets the boolean value of {@link #infiniteEnergy}.
     *
     * @param aBool true to activate; false to desactivate.
     */
    public void setInfiniteEnergy(boolean aBool){
        this.infiniteEnergy = aBool;
    }

    /**
     * Increases every stat of the Player and sets its hp and fp up to its maximum available.
     */
    private void levelUp(){
        this.setAtk((int)(this.getAtk() * (1 + 1.0/this.getLvl())));
        this.setDef((int)(this.getDef() * (1 + 1.0/this.getLvl())));
        this.setHpMax((int)(this.getMaxHp() * (1 + 1.0/this.getLvl())));
        this.setHp(this.getMaxHp());
        this.setFp(this.getMaxFp());
        this.setLvl(this.getLvl() + 1);
    }

    /**
     * {@link #levelUp} n times.
     *
     * @param n quantity of levels to level up.
     */
    public void levelUp(int n){
        for (int i=0; i<n; i++){
            this.levelUp();
        }
    }

    /**
     * {@link #levelUp} a Player if it has enough exp to do it.
     */
    public void tryLevelUp(){
        while (this.exp >= 2){
            this.levelUp();
            this.exp -= 2;
        }
    }

    /**
     * Checks if the user can use a specific item.
     *
     * @param anItem Item to be used.
     * @return true if the user isn't KO and the item is available;
     *         false otherwise.
     */
    public boolean canUse(Consumable anItem){
        return !this.isKO() && anItem.isAvailable();
    }

    /**
     * Consumes an Item from Players inventory (if it's available)
     * and give its effects to itself.
     *
     * @param anItem Item to be used.
     */
    public void use(ItemEnum anItem){
        this.use(anItem,this);
    }

    /**
     * Consumes an Item from Players inventory (if it's available)
     * and give its effects to a specific target.
     *
     * @param anItem Item to be used.
     * @param target Player to get the effect.
     */
    public void use(ItemEnum anItem, IPlayer target) {
        if (this.canUse(inventory[anItem.getIndex()])) {
            inventory[anItem.getIndex()].consume();
            inventory[anItem.getIndex()].giveEffect(target);
        }
    }

    /**
     * Increases by one the quantity of an Item in Players inventory.
     *
     * @param anItem Item to increase its quantity.
     */
    public void get(ItemEnum anItem){
        this.get(anItem,1);
    }

    /**
     * Increases by a specific amount the quantity of an Item in Players inventory.
     *
     * @param anItem Item to increase its quantity.
     */
    public void get(ItemEnum anItem, int amount){
        inventory[anItem.getIndex()].add(amount);
    }

    /**
     * Gets an Item and uses it on itself.
     *
     * @param anItem Item to get and use.
     */
    public void getAndUse(ItemEnum anItem){
        this.get(anItem);
        this.use(anItem);
    }

    /**
     * Sets the Players' inventory back to its initial value.
     */
    public static void resetInventory(){
        inventory[0] = new Star();
        inventory[1] = new RedMushroom();
        inventory[2] = new HoneySyrup();
    }

    /**
     * Subtracts hit points to Player's using the superclass method unless
     * the Player is invincible.
     *
     * @param damage hp to subtract.
     */
    @Override
    protected void receiveDamage(double damage){
        if (!this.isInvincible()){
            super.receiveDamage(damage);
        }
    }

    @Override
    public void attackedByGoomba(Goomba aGoomba){
        this.receiveDamage(0.75 * aGoomba.getAtk() / this.getDef());
    }

    @Override
    public void attackedBySpiny(Spiny aSpiny){
        this.receiveDamage(0.75 * aSpiny.getAtk() / this.getDef());
    }

    /**
     * Checks if the Player Attack Type performed hits using a random double
     * number generated between zero and one, and comparing it to the attack
     * accuracy, otherwise checks if the Player has {@link #perfectPrecision}.
     *
     * @param anAttack an attack that Player can perform.
     * @return true if the Player has perfectPrecision or hits;
     *         false otherwise.
     */
    protected boolean hit(PlayerAttackType anAttack){
        double rand = new Random().nextDouble();
        return anAttack.getAccuracy() >= rand || perfectPrecision;
    }
}