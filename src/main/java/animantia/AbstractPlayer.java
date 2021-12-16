package animantia;

import interfaces.Consumable;
import interfaces.IPlayer;
import items.*;
import java.util.Random;

/**
 * AbstractPlayer is the class creator of every playable creatures controlled by a user. It was created
 * to make the user the protagonist of the game. It has implemented methods to attack Enemies, level up
 * and use items. It also has fields like a shared chest between the Players to get and use those items
 * in battle against Enemies,  {@link #fp},  {@link #maxFp}, and some boolean values used as cheats
 * to test attacks.
 *
 * @author Diego Zuniga.
 */
public abstract class AbstractPlayer extends AbstractAnimantia implements IPlayer {

    /**
     * Max fights points that a Player can have.
     */
    private int maxFp;

    /**
     * Int value needed to perform an attack with a Player.
     */
    private int fp;

    /**
     * Boolean value to hit every attack when is true.
     */
    private boolean perfectPrecision = false;

    /**
     * Boolean value to don't lose FP when perform an attack when is true.
     */
    private boolean infiniteEnergy = false;

    /**
     * Consumable array used as a shared chest between the players to get and use Items.
     * It has initialized three different consumable Items.
     */
    private static Chest chest;

    /**
     * Creates a new Player using parameters like attack, defense maximum hit points, maximum {@link #fp} and level.
     * It also sets the initial fp equal to fpMax.
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

    @Override
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
    public void levelUp(){
        this.setAtk((int)(this.getAtk() * 1.15));
        this.setDef((int)(this.getDef() * 1.15));
        this.setHpMax((int)(this.getMaxHp() * 1.15));
        this.setHp(this.getMaxHp());
        this.maxFp *= 1.15;
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
     * Gets the actual Players' chest.
     *
     * @return chest.
     */
    public static Consumable[] getChestItems(){
        return AbstractPlayer.chest.getItems();
    }

    /**
     * Adds a new chest to all players
     *
     * @param aChest Chest to add.
     */
    public static void setChest(Chest aChest){
        chest=aChest;
    }

    /**
     * Consumes an Item from Players inventory (if it's available)
     * and give its effects to itself.
     *
     * @param anItem Item to be used.
     */
    public void use(ItemEnum anItem){
        this.use(this,anItem);
    }

    @Override
    public void use(IPlayer target, ItemEnum anItem) {
        if (!chest.get(anItem).isAvailable()) {
            throw new AssertionError("Not available!");
        }
        else if (target.isKO()){
            throw new AssertionError("Player is KO!");
        }
        else{
            chest.get(anItem).consume();
            chest.get(anItem).giveEffect(target);
        }
    }

    /**
     * Adds a specific item amount to Players' chest.
     *
     * @param anItem Specific item.
     * @param quantity Amount to add.
     */
    public static void add(ItemEnum anItem, int quantity){
        chest.add(anItem, quantity);
    }

    /**
     * Adds a specific item to Players' chest.
     *
     * @param anItem Specific item.
     */
    public static void add(ItemEnum anItem){
        add(anItem,1);
    }

    /**
     * Sets the Players' chest back to its initials values (empty).
     */
    public static void resetChest(){
        chest.reset();
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

    /**
     * Converts into a string specific stats like lvl, hp and fp.
     *
     * @return the string.
     */
    @Override
    public String toString(){
        return super.toString() + " FP: " + getFp() + "/" + getMaxFp();
    }
}