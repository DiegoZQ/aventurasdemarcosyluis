package items;

/**
 * Items that can be used by a Player with their indexes in the Players inventory.
 *
 * @author Diego Zuniga.
 */
public enum ItemEnum{

    /**
     * @see RedMushroom
     */
    REDMUSHROOM(0),

    /**
     * @see HoneySyrup
     */
    HONEYSYRUP(1);

    /**
     * An int that indicates the position of a certain item in the Players inventory.
     */
    private final int index;

    /**
     * Creates an Item with a specific {@link #index}.
     *
     * @param index an index.
     */
    ItemEnum(int index){
        this.index = index;
    }

    /**
     * Gets the {@link #index}.
     *
     * @return the index.
     */
    public int getIndex(){
        return this.index;
    }
}