import animantia.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static animantia.PlayerAttackType.MARTILLO;
import static animantia.PlayerAttackType.SALTO;
import static org.junit.jupiter.api.Assertions.*;

public class TestEnemy{
    private Marcos testMarcos;
    private Luis testLuis;
    private Goomba testGoomba;
    private Goomba testGoomba2;
    private Spiny testSpiny;
    private Spiny testSpiny2;
    private Boo testBoo;
    private Boo testBoo2;

    @BeforeEach
    public void setUp(){
        testMarcos = new Marcos();
        testLuis = new Luis();
        testGoomba = new Goomba();
        testGoomba2 = new Goomba();
        testSpiny = new Spiny();
        testSpiny2 = new Spiny();
        testBoo = new Boo();
        testBoo2 = new Boo();
    }
    @Test
    public void spawnEnemyTest(){
        assertFalse(testGoomba.isDamaged());
        assertEquals(1, testGoomba.getLvl());
        assertFalse(testSpiny.isDamaged());
        assertEquals(1, testSpiny.getLvl());
        assertFalse(testBoo.isDamaged());
        assertEquals(1, testBoo.getLvl());
    }
    @Test
    public void enemiesGetPowerfulTest(){
        assertEquals(testGoomba.getLvl(),testGoomba2.getLvl());
        assertEquals(testSpiny.getLvl(),testSpiny2.getLvl());
        assertEquals(testBoo.getLvl(),testBoo2.getLvl());
        testMarcos.setInfiniteEnergy(true);
        testLuis.setInfiniteEnergy(true);
        testLuis.setPerfectPrecision(true);
        for (int i = 0; i < 100; i++){
            testLuis.attack(testGoomba2, MARTILLO);
            if (testGoomba2.isKO()){
                testGoomba2 = new Goomba();
            }
            testLuis.attack(testSpiny2, MARTILLO);
            if (testSpiny2.isKO()){
                testSpiny2 = new Spiny();
            }
            testMarcos.attack(testBoo2, SALTO);
            if (testBoo2.isKO()){
                testBoo2 = new Boo();
            }
        }
        assertTrue(testGoomba.getLvl()<testGoomba2.getLvl());
        assertTrue(testSpiny.getLvl()<testSpiny2.getLvl());
        assertTrue(testBoo.getLvl()<testBoo2.getLvl());
    }
}//278