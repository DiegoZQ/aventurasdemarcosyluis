import animantia.*;
import org.junit.jupiter.api.AfterEach;
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
        testMarcos = Marcos.getInstance();
        testLuis = Luis.getInstance();
    }
    @AfterEach
    public void reset(){
        Marcos.reset();
        Luis.reset();
    }

    @Test
    public void attackMarcos(){
        testGoomba.attack(testMarcos);
        assertTrue(testMarcos.isDamaged());
        Marcos.reset();
        testSpiny.attack(testMarcos);
        assertTrue(testMarcos.isDamaged());
    }

    @Test
    public void attackLuis(){
        testGoomba.attack(testLuis);
        assertTrue(testLuis.isDamaged());
        Luis.reset();
        testSpiny.attack(testLuis);
        assertTrue(testLuis.isDamaged());
        Luis.reset();
        testBoo.attack(testLuis);
        assertTrue(testLuis.isDamaged());
    }
}