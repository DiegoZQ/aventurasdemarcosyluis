package game;

import java.io.IOException;

/**
 * Public class used to run "Las flipantes aventuras de Marcos & Luis".
 *
 * @author Diego Zuniga.
 */
public class GameRunner {

    public static void main(String[] args) throws IOException {
        GameController controller = new GameController();
        controller.runGame();
    }
}
