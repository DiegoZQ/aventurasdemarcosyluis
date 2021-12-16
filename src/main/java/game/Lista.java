package game;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Lista is a subclass of ArrayList, it just modifies the toString method.
 *
 * @param <i> type of elements in collection.
 * @author Diego Zuniga.
 */
public class Lista<i> extends ArrayList<i> {

    /**
     * Creates a list.
     * @param c type of element.
     */
    public Lista(Collection<? extends i> c) {
        super(c);
    }

    /**
     * Convert into a String the list including an index starting with 1 before the element
     * converted into a string.
     *
     * @return the list converted into a string.
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i<size(); i++) {
             string.append(" [").append(i+1).append("] ").append(get(i).toString());
        }
        return string.toString();
    }
}