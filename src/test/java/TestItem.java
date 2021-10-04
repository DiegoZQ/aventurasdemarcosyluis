import animantia.Enemy;
import animantia.Player;
import items.Items;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import types.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestItem{
    private Player testMarcos;
    private Player testLuis;
    private Enemy testGoomba;

    @BeforeEach
    public void setUp() {
        testMarcos = new Player(PlayerType.MARCOS);
        testLuis = new Player(PlayerType.LUIS);
        testGoomba = new Enemy(EnemyType.GOOMBA);
        testMarcos.getItem(Items.STAR);
        testMarcos.getItem(Items.REDMUSHROOM);
        testMarcos.getItem(Items.REDMUSHROOM);
        testMarcos.getItem(Items.HONEYSYRUP);
        testMarcos.getItem(Items.HONEYSYRUP);
    }
    @Test
    public void sharedInventoryTest() {
        testLuis.attack(testGoomba,PlayerAttackType.SALTO);
        testMarcos.attack(testGoomba,PlayerAttackType.SALTO);
        testLuis.useItem(Items.HONEYSYRUP);
        assertEquals(testLuis.getFp(),testLuis.getFpMax());
        testMarcos.useItem(Items.HONEYSYRUP);
        assertEquals(testMarcos.getFp(),testMarcos.getFpMax());
    }
    @Test
    public void useStarTest() {
        testMarcos.useItem(Items.STAR);
        testGoomba.attack(testMarcos, EnemyAttackType.BASICATTACK);
        assertEquals(testMarcos.getHp(), testMarcos.getHpMax());
    }
    @Test
    public void useRedMushroomTest() {
        testMarcos.useItem(Items.REDMUSHROOM);
        assertEquals(testMarcos.getHp(), testMarcos.getHpMax());
        testGoomba.attack(testMarcos, EnemyAttackType.BASICATTACK);
        int lastHp = testMarcos.getHp();
        testMarcos.useItem(Items.REDMUSHROOM);
        assert testMarcos.getHp() > lastHp;
        assert testMarcos.getHp() <= testMarcos.getHpMax();
    }
    @Test
    public void useHoneySyrupTest() {
        testMarcos.useItem(Items.HONEYSYRUP);
        assertEquals(testMarcos.getFp(), testMarcos.getFpMax());
        testMarcos.attack(testGoomba,PlayerAttackType.SALTO);
        testMarcos.useItem(Items.HONEYSYRUP);
        assertEquals(testMarcos.getFp(),testMarcos.getFpMax());
    }
}