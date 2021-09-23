package animantia;

/**
 * AttackTable is a class used only to define two public statics constants matrices filled with
 * boolean values, the rows represent the Players and the columns represent the Enemies. Each
 * Player/Enemy type has one unique index defined in PlayerType/EnemyType, and it's index
 * combination in the matrices are used to determine if a specific Player/Enemy can attack
 * a specific Enemy/Player.
 *
 * @author Diego Zuniga.
 */
public class AttackTable {
    /**
     * Default constructor without utility in this class.
     */
    private AttackTable(){
    }
    /**
     * Matrix in which each boolean value i,j determines if a specific Player located in the i-row
     * can attack a specific Enemy located in the j-column.
     */
    public static final boolean[][] PlayerAttackTable = { {true, true, true},
                                                          {true, true, false} };
    /**
     * Matrix in which each boolean value i,j determines if a specific Enemy located in the j-column
     * can attack a specific Player located in the i-row.
     */
    public static final boolean[][] EnemyAttackTable = { {true, true, false},
                                                         {true, true, true} };
}