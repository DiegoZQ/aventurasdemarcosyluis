import animantia.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static animantia.PlayerAttackType.MARTILLO;
import static animantia.PlayerAttackType.SALTO;
import static org.junit.jupiter.api.Assertions.*;

public class TestPlayerAttack{

    private Marcos testMarcos;
    private Luis testLuis;
    private Goomba testGoomba;
    private Spiny testSpiny;
    private Boo testBoo;

    @BeforeEach
    public void setUp(){
        testMarcos = Marcos.getInstance();
        testLuis = Luis.getInstance();
    }

    @AfterEach
    public void reset(){
        Marcos.reset();
        Luis.reset();
    }

    @Test
    public void attackGoombaWithSALTO(){
        testGoomba = new Goomba();
        testMarcos.attack(testGoomba, SALTO);
        assertTrue(testGoomba.isDamaged());
        testGoomba = new Goomba();
        testLuis.attack(testGoomba, SALTO);
        assertTrue(testGoomba.isDamaged());
    }

    @Test
    public void attackSpinyWithSALTO(){
        testSpiny = new Spiny();
        testMarcos.attack(testSpiny, SALTO);
        assertFalse(testSpiny.isDamaged());
        assertTrue(testMarcos.isDamaged());
        testLuis.attack(testSpiny, SALTO);
        assertFalse(testSpiny.isDamaged());
        assertTrue(testLuis.isDamaged());
    }

    @Test
    public void attackBooWithSALTO(){
        testBoo = new Boo();
        testMarcos.attack(testBoo, SALTO);
        assertTrue(testBoo.isDamaged());
    }

    @Test
    public void attackGoombaWithMARTILLO(){
        testMarcos.setPerfectPrecision(true);
        testLuis.setPerfectPrecision(true);
        testGoomba = new Goomba();
        testMarcos.attack(testGoomba, MARTILLO);
        assertTrue(testGoomba.isDamaged());
        testGoomba = new Goomba();
        testLuis.attack(testGoomba, MARTILLO);
        assertTrue(testGoomba.isDamaged());
    }

    @Test
    public void attackSpinyWithMARTILLO(){
        testMarcos.setPerfectPrecision(true);
        testLuis.setPerfectPrecision(true);
        testSpiny = new Spiny();
        testMarcos.attack(testSpiny, MARTILLO);
        assertTrue(testSpiny.isDamaged());
        testSpiny = new Spiny();
        testLuis.attack(testSpiny, MARTILLO);
        assertTrue(testSpiny.isDamaged());
    }

    @Test
    public void attackBooWithMARTILLO(){
        testMarcos.setPerfectPrecision(true);
        testBoo = new Boo();
        testMarcos.attack(testBoo, MARTILLO);
        assertFalse(testBoo.isDamaged());
    }
}