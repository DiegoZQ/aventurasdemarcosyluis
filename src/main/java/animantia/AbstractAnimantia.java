package animantia;

/**
 * Animantia from Latin "living things" is the name of abstract class, superclass of every class
 * that creates living things with properties like having attack (ATK), defense (DEF), hit points (HP),
 * max hit points (HP_MAX), level (LVL) and a type (not set in this class). Plus it also
 * includes methods to interact with others living things, methods like canAttack or attack.
 *
 * @param <T> anonymous class for attacked.
 * @param <R> anonymous class for attack type.
 * @param <Q> anonymous class for  Type (set in the final subclasses Player/Enemy).
 * @author Diego Zuniga.
 */
public abstract class AbstractAnimantia<T, R, Q> {
    private int atk;
    private int def;
    private int hpMax;
    private int hp;
    private int lvl;
    /**
     * Kind of tag used to identify between the different Enemies and Players to define their
     * interactions when attack or get attacked.
     */
    private Q type;
    /**
     * Creates an Animantia with a specific attack, defense, max health points and level.
     * It also put implicitly initial hit points equal to maximum hit points.
     *
     * @param ATK attack.
     * @param DEF defense.
     * @param HP_MAX maximum hit points.
     * @param LVL level.
     */
    public AbstractAnimantia(int ATK, int DEF, int HP_MAX, int LVL){
        this.atk = ATK;
        this.def = DEF;
        this.hpMax = HP_MAX;
        this.hp = HP_MAX;
        this.lvl = LVL;
    }
    /**
     * Gets the attack.
     *
     * @return the attack.
     */
    public int getAtk(){
        return this.atk;
    }
    /**
     * Sets the attack.
     *
     * @param ATK attack to set.
     */
    public void setAtk(int ATK){
        this.atk = ATK;
    }
    /**
     * Gets the defense.
     *
     * @return the defense.
     */
    public int getDef(){
        return this.def;
    }
    /**
     * Sets the defense.
     *
     * @param DEF defense to set.
     */
    public void setDef(int DEF){
        this.def = DEF;
    }
    /**
     * Gets the maximum hit points.
     *
     * @return the maximum hit points.
     */
    public int getHpMax(){
        return this.hpMax;
    }
    /**
     * Sets the maximum hit points.
     *
     * @param HP_MAX maximum hit points to set.
     */
    public void setHpMax(int HP_MAX){
        this.hpMax = HP_MAX;
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
     * Sets the hit points.
     *
     * @param HP hit points.
     */
    public void setHp(int HP){
        this.hp = HP;
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
    public void setLvl(int LVL){
        this.lvl = LVL;
    }
    /**
     * Gets the type.
     *
     * @return the type.
     */
    public Q getType(){
        return this.type;
    }
    /**
     * Sets the type.
     *
     * @param type a type.
     */
    public void setType(Q type){
        this.type = type;
    }
    /**
     * Checks if it's Knock out.
     *
     * @return true if its hit points are equal to zero;
     *         false otherwise.
     */
    public boolean isKO(){
        return this.getHp() == 0;
    }
    /**
     * Checks if it meets all the requirements to carry out an attack on an Animantia.
     *
     * @param anAnimantia Animantia to be attacked.
     * @return true if all the requirements are meet;
     *         false otherwise.
     */
    public abstract boolean canAttack(T anAnimantia);
    /**
     * Attacks an Animantia lowering his hit points using a specific attack.
     *
     * @param anAnimantia Animantia who gets its hit point lowered.
     * @param anAttack Attack chosen from all possible attacks that the Animantia can do.
     */
    public abstract void attack(T anAnimantia, R anAttack);
}