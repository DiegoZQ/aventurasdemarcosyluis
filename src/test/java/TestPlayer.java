import animantia.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static animantia.PlayerAttackType.SALTO;
import static org.junit.jupiter.api.Assertions.*;

public class TestPlayer{

    private Marcos testMarcos;
    private Luis testLuis;
    private Goomba testGoomba;

    @BeforeEach
    public void setUp(){
        testMarcos = Marcos.getInstance();
        testLuis = Luis.getInstance();
        testGoomba = new Goomba();
    }

    @AfterEach
    public void reset(){
        Marcos.reset();
        Luis.reset();
        AbstractEnemy.resetPower();
    }

    @Test
    public void spawnPlayerTest(){
        assertFalse(testMarcos.isTired());
        assertFalse(testMarcos.isDamaged());
        assertFalse(testLuis.isTired());
        assertFalse(testLuis.isDamaged());
        assertEquals(1, testMarcos.getLvl());
        assertEquals(1,testLuis.getLvl());
    }

    @Test
    public void LifeCantBeLowerThanZero(){
        for (int i = 0; i < 200; i++){
            AbstractEnemy.increasePower();
        }
        testGoomba = new Goomba();
        testGoomba.attack(testLuis);
        assertTrue(testLuis.isKO());
    }

    @Test
    public void cantAttackWhenKO(){
        testMarcos.setKO();
        testLuis.setKO();
        testMarcos.attack(testGoomba, SALTO);
        testLuis.attack(testGoomba, SALTO);
        assertFalse(testGoomba.isDamaged());
    }
}