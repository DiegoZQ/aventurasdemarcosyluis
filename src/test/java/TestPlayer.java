import animantia.AbstractEnemy;
import animantia.Goomba;
import animantia.Luis;
import animantia.Marcos;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static animantia.PlayerAttackType.MARTILLO;
import static animantia.PlayerAttackType.SALTO;
import static org.junit.jupiter.api.Assertions.*;

public class TestPlayer{
    private Marcos testMarcos;
    private Luis testLuis;
    private Goomba testGoomba;

    @BeforeEach
    public void setUp(){
        testLuis = new Luis();
        testGoomba = new Goomba();
        testMarcos = new Marcos();
    }

    @AfterEach
    public void reset(){
        AbstractEnemy.resetDifficulty();
        AbstractEnemy.resetPower();
    }

    @Test
    public void spawnPlayerTest(){
        assertFalse(testLuis.isTired());
        assertFalse(testLuis.isDamaged());
    }

    @Test
    public void LifeCantBeLowerThanZero(){
        for (int i = 0; i < 200; i++){
            testGoomba.attack(testLuis);
        }
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

    @Test
    public void nextLevelTest(){
        testLuis.levelUp(8);
        testLuis.setInfiniteEnergy(true);
        testLuis.setPerfectPrecision(true);
        for (int i = 0; i < 10 ; i++){
            testLuis.attack(testGoomba, MARTILLO);
            if (testGoomba.isKO()){
                testGoomba = new Goomba();
            }
        }
        testLuis.tryLevelUp();
        assertTrue(testLuis.getLvl() > 9);
    }
}