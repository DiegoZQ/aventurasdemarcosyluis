import animantia.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEnemyAttack{
    private Marcos testMarcos;
    private Luis testLuis;
    private Goomba testGoomba;
    private Spiny testSpiny;
    private Boo testBoo;

    @BeforeEach
    public void setUp(){
        testGoomba = new Goomba();
        testSpiny = new Spiny();
        testBoo = new Boo();
        testMarcos = new Marcos();
        testLuis = new Luis();
    }
    @Test
    public void attackMarcos(){
        testMarcos = new Marcos();
        testGoomba.attack(testMarcos);
        assertTrue(testMarcos.isDamaged());
        testMarcos = new Marcos();
        testSpiny.attack(testMarcos);
        assertTrue(testMarcos.isDamaged());
    }
    @Test
    public void attackLuis(){
        testLuis = new Luis();
        testGoomba.attack(testLuis);
        assertTrue(testLuis.isDamaged());
        testLuis = new Luis();
        testSpiny.attack(testLuis);
        assertTrue(testLuis.isDamaged());
        testLuis = new Luis();
        testBoo.attack(testLuis);
        assertTrue(testLuis.isDamaged());
    }
}