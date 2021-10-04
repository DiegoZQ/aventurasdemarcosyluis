import animantia.Enemy;
import animantia.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import types.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlayer {
    private Player testLuis;
    private Enemy testGoomba;

    @BeforeEach
    public void setUp(){
        testLuis = new Player(PlayerType.LUIS);
        testLuis.levelUp(2);
        testLuis.setPerfectPrecision(true);
        testGoomba = new Enemy(EnemyType.GOOMBA);
    }
    @Test
    public void nextLevelTest(){
        for (int i=0;i<10;i++){
            testLuis.attack(testGoomba, PlayerAttackType.MARTILLO);
            testGoomba = new Enemy(EnemyType.GOOMBA);
        }
        testGoomba.attack(testLuis, EnemyAttackType.BASICATTACK);
        assert testLuis.getHp()<testLuis.getHpMax();
        assert testLuis.getFp()<testLuis.getFpMax();
        testLuis.tryLevelUp();
        assert testLuis.getLvl()>3;
        assertEquals(testLuis.getHp(),testLuis.getHpMax());
        assertEquals(testLuis.getFp(),testLuis.getFpMax());
    }
}