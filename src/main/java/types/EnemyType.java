package types;

import animantia.Player;
import static types.PlayerAttackType.MARTILLO;
import static types.PlayerAttackType.SALTO;

/**
 * Enemy types, their base stats (stats at level 1), indexes in Attack Table and their particularities
 * when they're attacked with different Player Attack Types.
 *
 * @author Diego Zuniga.
 */
public enum EnemyType {
    /**
     * A generic enemy.
     *
     */
    GOOMBA(8,2,5,0) {
        /**
         * Gets the resulting damage from attacking Goomba with a specific attack.
         *
         * @param aPlayer Player who attacks him.
         * @param anAttack Player attack type that a Player performs.
         * @return the resulting raw damage towards the enemy without the enemy defense applied in attack formula.
         */
        @Override
        public double beAttackedBy(Player aPlayer, PlayerAttackType anAttack) {
            return aPlayer.getRawDamage(anAttack);
        }
    },
    /**
     * A defense-focused enemy and immune to player attack type SALTO
     * because of the spikes in its shell.
     */
    SPINY(6,2,2,1) {
        /**
         * Checks if the Player attack type is valid to attack Spiny.
         *
         * @param anAttack Player attack type that a Player performs.
         * @return true if the attack Type is MARTILLO;
         *         false otherwise.
         */
        boolean isAttackableWith(PlayerAttackType anAttack) {
            return anAttack == MARTILLO;
        }
        /**
         * Checks if Spiny is attackable with the Player attack type. If he is, then returns the resulting damage;
         * else, the Player gets its hit points lowered by 5% of him maximum hit points and the resulting damage to
         * Spiny is zero.
         *
         * @param aPlayer Player who attacks him.
         * @param anAttack Player attack type that a Player performs.
         * @return the resulting raw damage towards the enemy without the enemy defense applied in attack formula.
         */
        @Override
        public double beAttackedBy(Player aPlayer, PlayerAttackType anAttack){
            if (isAttackableWith(anAttack)){
                return aPlayer.getRawDamage(anAttack);
            }
            double damage = -aPlayer.getHpMax()*0.1*(anAttack.getK()-1.5);
            damage = Math.min(damage,aPlayer.getHp());
            aPlayer.setHp((int)(aPlayer.getHp()-damage));
            return 0;
        }
    },
    /**
     * An attack-focused enemy and immune to player attack type MARTILLO.
     * This enemy will only attack players type LUIS.
     */
    BOO(3,1,2,2) {
        /**
         * Checks if the Player attack type is valid to attack Boo.
         *
         * @param anAttack Player attack type that a Player performs.
         * @return true if the attack Type is SALTO;
         *         false otherwise.
         */
        boolean isAttackableWith(PlayerAttackType anAttack){
            return anAttack == SALTO;
        }
        /**
         * Checks if Boo is attackable with the Player attack type. If he is, then returns the resulting damage;
         * else, returns zero.
         *
         * @param aPlayer Player who attacks him.
         * @param anAttack Player attack type that a Player performs.
         * @return the resulting raw damage towards the enemy without the enemy defense applied in attack formula.
         */
        @Override
        public double beAttackedBy(Player aPlayer, PlayerAttackType anAttack){
            if (isAttackableWith(anAttack)){
                return aPlayer.getRawDamage(anAttack);
            }
            return 0;
        }
    };
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
     * Unique int number among the different Enemy types used to locate a specific Enemy in the
     * AttackTable matrices.
     */
    private final int index;
    /**
     * Creates an EnemyType using base stats and an index.
     *
     * @param baseAtk {@link #baseAtk}.
     * @param baseDef {@link #baseDef}.
     * @param baseHpMax {@link #baseHpMax}.
     * @param index {@link #index}.
     */
    EnemyType(int baseAtk, int baseDef, int baseHpMax, int index){
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
        return this.baseAtk;
    }
    /**
     * Gets the {@link #baseDef}.
     *
     * @return the baseDef.
     */
    public int getBaseDef(){
        return this.baseDef;
    }
    /**
     * Gets the {@link #baseHpMax}.
     *
     * @return the baseHpMax.
     */
    public int getBaseMaxHp(){
        return this.baseHpMax;
    }
    /**
     * Gets the {@link #index}.
     *
     * @return the index.
     */
    public int getIndex(){
        return this.index;
    }
    /**
     * Gets the damage from attacking a specific enemy with a correct Player attack type.
     * If the Player attack type isn't correct, the resulting damage is 0 and the Player
     * gets penalized (or not).
     *
     * @param aPlayer Player who attacks him.
     * @param anAttack Player attack type that a Player performs.
     * @return the resulting damage towards the enemy without the enemy defense applied in attack formula.
     */
    public abstract double beAttackedBy(Player aPlayer, PlayerAttackType anAttack);
}