package animantia;

import interfaces.CanMove;

/**
 * Animantia from Latin "living things" is the name of abstract class, superclass of every class
 * that creates living things with properties like having attack (ATK), defense (DEF), hit points (HP),
 * max hit points (HP_MAX) and a level (LVL). Plus it also implements the interface CanBeAttacked which
 * includes methods to interact with others living things.
 *
 * @author Diego Zuniga.
 */
public abstract class AbstractAnimantia implements CanMove {

    private int atk;
    private int def;
    private int maxHp;
    private int hp;
    private int lvl;

    /**
     * Creates an Animantia with a specific attack, defense, max health points and level.
     * It also put implicitly initial hit points equal to maximum hit points.
     *
     * @param ATK attack.
     * @param DEF defense.
     * @param MAX_HP maximum hit points.
     * @param LVL level.
     */
    protected AbstractAnimantia(int ATK, int DEF, int MAX_HP, int LVL){
        this.atk = ATK;
        this.def = DEF;
        this.maxHp = MAX_HP;
        this.hp = MAX_HP;
        this.lvl = LVL;
    }

    /**
     * Gets the attack.
     *
     * @return the attack.
     */
    protected int getAtk(){
        return this.atk;
    }

    /**
     * Sets the attack.
     *
     * @param ATK attack to set.
     */
    protected void setAtk(int ATK){
        this.atk = ATK;
    }

    /**
     * Gets the defense.
     *
     * @return the defense.
     */
    protected int getDef(){
        return this.def;
    }

    /**
     * Sets the defense.
     *
     * @param DEF defense to set.
     */
    protected void setDef(int DEF){
        this.def = DEF;
    }

    /**
     * Gets the maximum hit points.
     *
     * @return the maximum hit points.
     */
    public int getMaxHp(){
        return this.maxHp;
    }

    /**
     * Sets the maximum hit points.
     *
     * @param MAX_HP maximum hit points to set.
     */
    protected void setHpMax(int MAX_HP){
        this.maxHp = MAX_HP;
    }

    /**
     * Gets the hit points.
     *
     * @return the hit points.
     */
    public int getHp(){
        return this.hp;
    }

    /**
     * Sets the hit points with the restriction than
     * hp to set can't be lower than zero or higher than hpMax.
     *
     * @param hp hit points.
     */
    protected void setHp(int hp){
        if (hp<0){
            this.hp = 0;
        }
        else{
            this.hp = Math.min(hp, getMaxHp());
        }
    }

    /**
     * Gets the level.
     *
     * @return the level.
     */
    public int getLvl(){
        return this.lvl;
    }

    /**
     * Sets the level.
     *
     * @param LVL level.
     */
    protected void setLvl(int LVL){
        this.lvl = LVL;
    }

    @Override
    public boolean isDamaged(){
        return this.getHp() < this.getMaxHp();
    }

    /**
     * Subtracts {@link #hp}.
     *
     * @param damage hp to subtract.
     */
    protected void receiveDamage(double damage){
        this.setHp(this.getHp() - (int)damage);
    }

    /**
     * Sets the hp equals to zero (used for testing).
     */
    public void setKO(){
        this.setHp(0);
    }

    @Override
    public boolean isKO(){
        return this.getHp() == 0;
    }

    /**
     * Checks if both attacked and attacker aren't knockout.
     *
     * @param anAttacked An attacked.
     * @return true if both aren't knockout;
     *         false otherwise.
     */
    public boolean canAttack(CanMove anAttacked){
        return !this.isKO() && !anAttacked.isKO();
    }

    /**
     * Converts into a string some specific stats like hp and lvl.
     *
     * @return the string.
     */
    @Override
    public String toString(){
        return "lvl: " + this.getLvl() + " HP: " + this.getHp() + "/"  + this.getMaxHp();
    }
}//985-2356-2869