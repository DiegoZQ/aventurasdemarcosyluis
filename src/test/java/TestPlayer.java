import animantia.Goomba;
import animantia.Luis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static animantia.PlayerAttackType.MARTILLO;
import static org.junit.jupiter.api.Assertions.*;

public class TestPlayer{
    private Luis testLuis;
    private Goomba testGoomba;

    @BeforeEach
    public void setUp(){
        testLuis = new Luis();
        testGoomba = new Goomba();
    }
    @Test
    public void spawnPlayerTest(){
        assertFalse(testLuis.isTired());
        assertFalse(testLuis.isDamaged());
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