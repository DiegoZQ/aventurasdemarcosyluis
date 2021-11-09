import animantia.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static animantia.PlayerAttackType.SALTO;
import static org.junit.jupiter.api.Assertions.*;

public class TestEnemy{

    private Luis testLuis;
    private Goomba testGoomba;
    private Spiny testSpiny;
    private Boo testBoo;

    @BeforeEach
    public void setUp(){
        testLuis = Luis.getInstance();
        testGoomba = new Goomba();
        testSpiny = new Spiny();
        testBoo = new Boo();
    }

    @AfterEach
    public void reset(){
        AbstractEnemy.resetPower();
        Marcos.reset();
        Luis.reset();

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
    public void LifeCantBeLowerThanZero(){
        testLuis.setInfiniteEnergy(true);
        testLuis.levelUp(100);
        testLuis.attack(testGoomba, SALTO);
        assertTrue(testGoomba.isKO());
    }

    @Test
    public void cantAttackWhenKO(){
        testGoomba.setKO();
        testSpiny.setKO();
        testBoo.setKO();
        testGoomba.attack(testLuis);
        testSpiny.attack(testLuis);
        testBoo.attack(testLuis);
        assertFalse(testLuis.isDamaged());
    }
}