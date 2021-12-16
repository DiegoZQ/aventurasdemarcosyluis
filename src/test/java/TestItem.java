import animantia.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static animantia.PlayerAttackType.MARTILLO;
import static animantia.PlayerAttackType.SALTO;
import static items.ItemEnum.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestItem{

    private Marcos testMarcos;
    private Luis testLuis;
    private Goomba testGoomba;

    @BeforeEach
    public void setUp(){
        testMarcos = Marcos.getInstance();
        testLuis = Luis.getInstance();
        testGoomba = new Goomba();
        AbstractPlayer.setChest(new Chest());
    }

    @AfterEach
    public void reset(){
        Marcos.reset();
        Luis.reset();
        AbstractPlayer.resetChest();
    }

    @Test
    public void tryToUseUnavailableItemTest(){
        testLuis.attack(testGoomba, SALTO);
        assertThrows(AssertionError.class,
                () -> testLuis.use(HONEYSYRUP));
        assertTrue(testLuis.isTired());
    }

    @Test
    public void tryToUseItemWhenKO(){
        testLuis.setKO();
        AbstractPlayer.add(REDMUSHROOM);
        assertThrows(AssertionError.class,
                () -> testLuis.use(REDMUSHROOM));
        assertTrue(testLuis.isKO());
    }

    @Test
    public void useRedMushroomTest(){
        testGoomba.attack(testLuis);
        int lastHp = testLuis.getHp();
        AbstractPlayer.add(REDMUSHROOM);
        testLuis.use(REDMUSHROOM);
        assertTrue(testLuis.getHp() > lastHp);
    }

    @Test
    public void useHoneySyrupTest(){
        testLuis.attack(testGoomba, SALTO);
        assertTrue(testLuis.isTired());
        AbstractPlayer.add(HONEYSYRUP);
        testLuis.use(HONEYSYRUP);
        assertFalse(testLuis.isTired());
    }

    @Test
    public void maxHpTest(){
        for (int i = 0; i < 5 ; i++){
            AbstractPlayer.add(REDMUSHROOM);
            testLuis.use(REDMUSHROOM);
        }
        assertEquals(testLuis.getMaxHp(), testLuis.getHp());
    }

    @Test
    public void maxFpTest(){
        for (int i = 0; i < 5 ; i++){
            AbstractPlayer.add(HONEYSYRUP);
            testLuis.use(HONEYSYRUP);
        }
        assertEquals(testLuis.getMaxFp(), testLuis.getFp());
    }

    @Test
    public void useItemOnAnotherPlayerTest(){
        testLuis.attack(testGoomba, SALTO);
        AbstractPlayer.add(HONEYSYRUP);
        testMarcos.use(testLuis, HONEYSYRUP);
        assertFalse(testLuis.isTired());
    }

    @Test
    public void sharedInventoryTest(){
        AbstractPlayer.add(HONEYSYRUP);
        testMarcos.attack(testGoomba, SALTO);
        assertTrue(testMarcos.isTired());
        testMarcos.use(HONEYSYRUP);
        assertFalse(testMarcos.isTired());
    }
}