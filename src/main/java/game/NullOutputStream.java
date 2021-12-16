package game;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Class used to create an OutputStream which doesn't print anything.
 *
 * @author Diego Zuniga.
 */
public class NullOutputStream extends OutputStream {
    /**
     * Creates a NullOutputStream.
     */
    public NullOutputStream() {
        super();
    }
    /**
     * Does nothing.
     */
    public void write(int b) throws IOException{
    }
}