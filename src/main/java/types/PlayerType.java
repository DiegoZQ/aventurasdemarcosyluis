package types;

/**
 * Player types, their base stats (stats at level 1) and indexes in Attack Table.
 *
 * @author Diego Zuniga.
 */
public enum PlayerType {
    /**
     * A defense-focused player.
     */
    MARCOS(2,3,10,0),
    /**
     * An attack-focused player.
     * This player type won't attack enemies type BOO.
     */
    LUIS(5,2,7,1);
    /**
     * Attack at level 1.
     */
    private final int baseAtk;
    /**
     * Defense at level 1.
     */
    private final int baseDef;
    /**
     * Maximum hit points at level 1.
     */
    private final int baseHpMax;
    /**
     * Unique int number among the different Player types used to locate a specific Player in the
     * AttackTable matrices.
     */
    private final int index;
    /**
     * Creates a PlayerType using base stats and an index.
     *
     * @param baseAtk {@link #baseAtk}.
     * @param baseDef {@link #baseDef}.
     * @param baseHpMax {@link #baseHpMax}.
     * @param index {@link #index}.
     */
    PlayerType(int baseAtk, int baseDef, int baseHpMax, int index){
        this.baseAtk = baseAtk;
        this.baseDef = baseDef;
        this.baseHpMax = baseHpMax;
        this.index = index;
    }
    /**
     * Gets the {@link #baseAtk}.
     *
     * @return the baseAtk.
     */
    public int getBaseAtk(){
        return baseAtk;
    }
    /**
     * Gets the {@link #baseDef}.
     *
     * @return the baseDef.
     */
    public int getBaseDef(){
        return baseDef;
    }
    /**
     * Gets the {@link #baseHpMax}.
     *
     * @return the baseHpMax.
     */
    public int getBaseMaxHp(){
        return baseHpMax;
    }
    /**
     * Gets the {@link #index}.
     *
     * @return the index.
     */
    public int getIndex(){
        return index;
    }
}