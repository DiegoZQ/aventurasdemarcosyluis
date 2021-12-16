import animantia.*;
import game.GameController;
import game.NullOutputStream;
import interfaces.CanMove;
import interfaces.Consumable;
import items.HoneySyrup;
import items.RedMushroom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import static animantia.PlayerAttackType.MARTILLO;
import static animantia.PlayerAttackType.SALTO;
import static items.ItemEnum.HONEYSYRUP;
import static items.ItemEnum.REDMUSHROOM;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    private Marcos marcos;
    private Luis luis;
    private GameController controller;

    @BeforeEach
    public void setUp() {
        controller = new GameController();
        controller.setOut(new PrintStream(new NullOutputStream()));
        marcos = controller.generateMarcos();
        luis = controller.generateLuis();
    }

    @AfterEach
    public void reset(){
        controller.resetPlayers();
        controller.resetDifficulty();
    }

    @Test
    public void chestTest(){
        RedMushroom item1 = new RedMushroom();
        item1.add(3);
        HoneySyrup item2 = new HoneySyrup();
        item2.add(3);
        Consumable[] itemList = controller.getChestItems();
        assertEquals(item1, itemList[0]);
        assertEquals(item2, itemList[1]);
    }

    @Test
    public void listsTest(){
        List<CanMove> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(marcos);
        listOfPlayers.add(luis);
        assertEquals(listOfPlayers,controller.getListOfPlayers());
        assertEquals(3,controller.getListOfEnemies().size());
        assertEquals(5,controller.getListOfCharacters().size());

    }

    @Test
    public void turnOwnerTest(){
        assertEquals(marcos,controller.getOwner());
        controller.finishTurn();
        assertEquals(luis,controller.getOwner());
        controller.finishTurn();
        controller.finishTurn();
        controller.finishTurn();
        controller.finishTurn();
        assertEquals(marcos,controller.getOwner());
    }

    @Test
    public void nextTurnOwnerTest(){
        assertEquals(luis,controller.getNextOwner());
        controller.finishTurn();
        controller.finishTurn();
        controller.finishTurn();
        controller.finishTurn();
        assertEquals(marcos,controller.getNextOwner());
        controller.finishTurn();
        assertEquals(luis,controller.getNextOwner());
    }

    @Test
    public void useItemTest(){
        controller.playerTryToAttack(1,SALTO);
        assertTrue(marcos.isTired());
        controller.finishTurn();
        controller.playerTryToAttack(0,SALTO);
        assertTrue(luis.isTired());
        controller.finishTurn();
        controller.enemyTryToAttack();
        controller.enemyTryToAttack();
        controller.enemyTryToAttack();
        controller.tryToUseAnItem(0,REDMUSHROOM);
        assertEquals(2, controller.getChestItems()[0].getQuantity());
        controller.finishTurn();
        controller.tryToUseAnItem(0,HONEYSYRUP);
        assertEquals(2, controller.getChestItems()[1].getQuantity());
        controller.finishTurn();
    }

    @Test
    public void attackTest(){
        controller.playerTryToAttack(0, SALTO);
        assertTrue(marcos.isTired());
        controller.finishTurn();
        controller.playerTryToAttack(1, MARTILLO);
        assertTrue(luis.isTired());
        controller.finishTurn();
        controller.enemyTryToAttack();
        controller.enemyTryToAttack();
        controller.enemyTryToAttack();
        controller.playerTryToAttack(0, MARTILLO);
        controller.finishTurn();
        controller.playerTryToAttack(1, SALTO);
        controller.finishTurn();
    }

    @Test
    public void loseByPassTest(){
        controller.pass();
        controller.pass();
        controller.enemyTryToAttack();
        controller.enemyTryToAttack();
        controller.enemyTryToAttack();
        controller.pass();
        assertFalse(controller.notOver());
    }

    @Test
    public void BattleTest(){
        assertEquals(1,controller.getRound());
        controller.activePerfectPrecision();
        controller.playerTryToAttack(0, SALTO);
        controller.finishTurn();
        controller.playerTryToAttack(0, SALTO);
        controller.finishTurn();
        controller.enemyTryToAttack(0);
        controller.enemyTryToAttack(0);
        controller.enemyTryToAttack();
        controller.playerTryToAttack(1, MARTILLO);
        controller.finishTurn();
        controller.playerTryToAttack(0, SALTO); //muere goomba no muere realmente wtf
        controller.finishTurn();
        controller.enemyTryToAttack(0);
        controller.enemyTryToAttack();
        controller.tryToUseAnItem(0, HONEYSYRUP);
        controller.finishTurn();
        controller.playerTryToAttack(0, MARTILLO);
        controller.finishTurn();
        controller.enemyTryToAttack(0);
        controller.enemyTryToAttack();
        controller.playerTryToAttack(0, MARTILLO);//muere spiny
        controller.finishTurn();
        controller.tryToUseAnItem(1, HONEYSYRUP);
        controller.finishTurn();
        controller.enemyTryToAttack(0);
        controller.enemyTryToAttack();
        controller.playerTryToAttack(0,MARTILLO);
        controller.finishTurn();
        controller.tryToUseAnItem(1,REDMUSHROOM);
        controller.finishTurn();
        controller.enemyTryToAttack();
        controller.tryToUseAnItem(0, HONEYSYRUP);
        controller.finishTurn();
        controller.pass();
        controller.enemyTryToAttack();
        controller.playerTryToAttack(0,SALTO);
        controller.finishTurn();
        controller.pass();
        controller.enemyTryToAttack();
        controller.playerTryToAttack(0, SALTO);
        controller.finishTurn();
        assertTrue(controller.notOver());
        assertEquals(2,controller.getRound());
    }

    @Test
    public void runGame1() throws IOException {
        controller.runGame("1\n1\n3\n2\n3\n3\n4\n");
    }

    @Test
    public void runGame2() throws IOException {
        controller.runGame("1\n1\n1\n1\n1\n1\n4\n");
        controller.runGame("1\n1\n1\n1\n2\n2\n1\n1\n2\n1\n2\n2\n1\n1\n2\n1\n1\n2\n1\n2\n3\n4\n");
    }

    @Test
    public void runGame3() throws IOException {
        controller.runGame("1\n1\n1\n1\n2\n2\n1\n1\n2\n1\n2\n2\n1\n1\n2\n1\n1\n2\n1\n2\n3\n4\n");
    }
}//513