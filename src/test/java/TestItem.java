import animantia.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static animantia.PlayerAttackType.SALTO;
import static items.ItemEnum.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestItem{
    private Marcos testMarcos;
    private Luis testLuis;
    private Goomba testGoomba;

    @BeforeEach
    public void setUp(){
        testMarcos = new Marcos();
        testLuis = new Luis();
        testGoomba = new Goomba();
    }
    @Test
    public void tryToUseUnavailableItemTest(){
        testLuis.attack(testGoomba, SALTO);
        testLuis.use(HONEYSYRUP);
        assertTrue(testLuis.isTired());
    }
    @Test
    public void useStarTest(){
        testMarcos.get(STAR);
        testMarcos.use(STAR);
        testGoomba.attack(testMarcos);
        assertFalse(testMarcos.isDamaged());
    }
    @Test
    public void useRedMushroomTest(){
        testGoomba.attack(testLuis);
        int lastHp = testLuis.getHp();
        testLuis.get(REDMUSHROOM);
        testLuis.use(REDMUSHROOM);
        assertTrue(testLuis.getHp() > lastHp);
    }
    @Test
    public void useHoneySyrupTest(){
        testLuis.attack(testGoomba, SALTO);
        assertTrue(testLuis.isTired());
        testLuis.get(HONEYSYRUP);
        testLuis.use(HONEYSYRUP);
        assertFalse(testLuis.isTired());
    }
    @Test
    public void maxHpTest(){
        for (int i = 0; i < 5 ; i++){
            testLuis.get(REDMUSHROOM);
            testLuis.use(REDMUSHROOM);
        }
        assertEquals(testLuis.getMaxHp(), testLuis.getHp());
    }
    @Test
    public void maxFpTest(){
        for (int i = 0; i < 5 ; i++){
            testLuis.get(HONEYSYRUP);
            testLuis.use(HONEYSYRUP);
        }
        assertEquals(testLuis.getMaxFp(), testLuis.getFp());
    }
    @Test
    public void useItemOnAnotherPlayer(){
        testLuis.attack(testGoomba, SALTO);
        testMarcos.get(HONEYSYRUP);
        testMarcos.use(HONEYSYRUP, testLuis);
        assertFalse(testLuis.isTired());
    }
    @Test
    public void sharedInventoryTest(){
        testLuis.get(HONEYSYRUP);
        testMarcos.attack(testGoomba, SALTO);
        assertTrue(testMarcos.isTired());
        testMarcos.use(HONEYSYRUP);
        assertFalse(testMarcos.isTired());
    }
}