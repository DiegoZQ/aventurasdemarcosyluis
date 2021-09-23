
import animantia.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import types.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAttack {
    private Player testMarcos;
    private Player testLuis;
    private Enemy testGoomba;
    private Enemy testSpiny;
    private Enemy testBoo;

    @BeforeEach
    public void setUp() {
        testMarcos = new Player(PlayerType.MARCOS);
        testLuis = new Player(PlayerType.LUIS);
        testGoomba = new Enemy(EnemyType.GOOMBA);
        testSpiny = new Enemy(EnemyType.SPINY);
        testBoo = new Enemy(EnemyType.BOO);
    }
    @Test
    public void PlayersLifeCantBeLowerThanZeroTest(){
        Enemy.setPower(20);
        testGoomba = new Enemy(EnemyType.GOOMBA);
        testGoomba.attack(testLuis,EnemyAttackType.BASICATTACK);
        assertEquals(testLuis.getHp(),0);
        assert testLuis.isKO();
    }
    @Test
    public void KnockOutPlayersCantAttackTest(){
        while (!testLuis.isKO()){
            testGoomba.attack(testLuis,EnemyAttackType.BASICATTACK);
        }
        testLuis.attack(testGoomba,PlayerAttackType.SALTO);
        assertEquals(testGoomba.getHp(),testGoomba.getHpMax());
    }
    @Test
    public void KnockOutEnemiesCantAttackTest(){
        while (!testGoomba.isKO()){
            testLuis.attack(testGoomba,PlayerAttackType.SALTO);
        }
        testGoomba.attack(testLuis,EnemyAttackType.BASICATTACK);
        assertEquals(testLuis.getHp(),testLuis.getHpMax());
    }
    @Test
    public void PlayerJumpAttack() {
        testMarcos.attack(testGoomba, PlayerAttackType.SALTO);
        assert testGoomba.getHp()<testGoomba.getHpMax();
        testLuis.attack(testBoo,PlayerAttackType.SALTO);
        assertEquals(testBoo.getHp(),testBoo.getHpMax());
        testMarcos.attack(testBoo, PlayerAttackType.SALTO);
        assert testBoo.getHp()<testBoo.getHpMax();
        testLuis.attack(testSpiny,PlayerAttackType.SALTO);
        assertEquals(testSpiny.getHp(),testSpiny.getHpMax());
        assert testLuis.getHp()<testLuis.getHpMax();
    }
    @Test
    public void PlayerHammerAttack() {
        boolean hit = testMarcos.hit(PlayerAttackType.MARTILLO);
        while (hit==testMarcos.hit(PlayerAttackType.MARTILLO));
        testMarcos.setPerfectPrecision(true);
        testLuis.setPerfectPrecision(true);
        testMarcos.attack(testGoomba,PlayerAttackType.MARTILLO);
        assert testGoomba.getHp()<testGoomba.getHpMax();
        testLuis.attack(testBoo,PlayerAttackType.MARTILLO);
        assertEquals(testBoo.getHp(),testBoo.getHpMax());
        testMarcos.attack(testBoo,PlayerAttackType.MARTILLO);
        assertEquals(testBoo.getHp(),testBoo.getHpMax());
        testLuis.attack(testSpiny,PlayerAttackType.MARTILLO);
        assert testSpiny.getHp()<testSpiny.getHpMax();
    }
    @Test
    public void EnemyAttack() {
        testGoomba.attack(testLuis, EnemyAttackType.BASICATTACK);
        assert testLuis.getHp()<testLuis.getHpMax();
        testBoo.attack(testMarcos,EnemyAttackType.BASICATTACK);
        assertEquals(testMarcos.getHp(),testMarcos.getHpMax());
        testSpiny.attack(testMarcos,EnemyAttackType.BASICATTACK);
        assert testMarcos.getHp()<testMarcos.getHpMax();
    }
}