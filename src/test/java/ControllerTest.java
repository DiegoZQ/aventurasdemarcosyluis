import animantia.*;
import game.GameController;
import interfaces.CanMove;
import interfaces.Consumable;
import items.HoneySyrup;
import items.RedMushroom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    private Goomba goomba;
    private Spiny spiny;
    private Boo boo;
    private GameController controller;

    @BeforeEach
    public void setUp() {
        controller= new GameController();
        marcos = controller.generateMarcos();
        luis = controller.generateLuis();
        goomba = controller.generateGoomba();
        spiny = controller.generateSpiny();
        boo = controller.generateBoo();
        controller.addEnemy(goomba);
        controller.addEnemy(spiny);
        controller.addEnemy(boo);
        controller.setChest();
        controller.addItem(REDMUSHROOM, 3);
        controller.addItem(HONEYSYRUP, 3);
    }

    @AfterEach
    public void reset(){
        controller.resetPlayers();
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
        List<CanMove> listOfEnemies = new ArrayList<>();
        List<CanMove> listOfCharacters = new ArrayList<>();
        listOfPlayers.add(marcos);
        listOfPlayers.add(luis);
        listOfEnemies.add(goomba);
        listOfEnemies.add(spiny);
        listOfEnemies.add(boo);
        listOfCharacters.addAll(listOfPlayers);
        listOfCharacters.addAll(listOfEnemies);
        assertEquals(listOfPlayers,controller.getListOfPlayers());
        assertEquals(listOfEnemies, controller.getListOfEnemies());
        assertEquals(listOfCharacters, controller.getListOfCharacters());
    }

    @Test
    public void turnOwnerTest(){
        assertEquals(marcos,controller.getOwner());
        controller.pass();
        assertEquals(luis,controller.getOwner());
        controller.pass();
        assertEquals(goomba,controller.getOwner());
        controller.pass();
        assertEquals(spiny,controller.getOwner());
        controller.pass();
        assertEquals(boo,controller.getOwner());
        controller.pass();
        assertEquals(marcos,controller.getOwner());
    }

    @Test
    public void nextTurnOwnerTest(){
        assertEquals(luis,controller.getNextOwner());
        controller.pass();
        assertEquals(goomba,controller.getNextOwner());
        controller.pass();
        assertEquals(spiny,controller.getNextOwner());
        controller.pass();
        assertEquals(boo,controller.getNextOwner());
        controller.pass();
        assertEquals(marcos,controller.getNextOwner());
        controller.pass();
        assertEquals(luis,controller.getNextOwner());
    }

    @Test
    public void useItemTest(){
        controller.playerTryToAttack(1,SALTO);
        controller.finishTurn();
        controller.playerTryToAttack(0,SALTO);
        controller.finishTurn();
        controller.enemyTryToAttack();
        controller.enemyTryToAttack();
        controller.enemyTryToAttack();
        controller.tryToUseAnItem(0,REDMUSHROOM);
        controller.finishTurn();
        controller.tryToUseAnItem(1,HONEYSYRUP);
        controller.finishTurn();
    }

    @Test
    public void attackTest(){
        controller.playerTryToAttack(0, SALTO);
        controller.finishTurn();
        controller.playerTryToAttack(1, MARTILLO);
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
    public void BattleTest(){
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
        controller.playerTryToAttack(0, SALTO); //muere goomba
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
    }
}//529