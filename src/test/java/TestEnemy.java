
import animantia.Enemy;
import animantia.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import types.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEnemy {
    private Player testLuis;
    private Enemy testGoomba;
    private Enemy testGoomba2;

    @BeforeEach
    public void setUp() {
        testLuis = new Player(PlayerType.LUIS);
        testLuis.levelUp(20);
        testLuis.setInfiniteEnergy(true);
        testLuis.setPerfectPrecision(true);
        testGoomba = new Enemy(EnemyType.GOOMBA);
        testGoomba2 = new Enemy(EnemyType.GOOMBA);
    }
    @Test
    public void enemiesGetPowerfulTest() {
        assertEquals(testGoomba.getLvl(),testGoomba2.getLvl());
        for (int i=0;i<20;i++){
            testLuis.attack(testGoomba2, PlayerAttackType.MARTILLO);
            testGoomba2 = new Enemy(EnemyType.GOOMBA);
        }
        assert testGoomba.getLvl()<testGoomba2.getLvl();
    }
}