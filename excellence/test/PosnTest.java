import cs3500.animator.util.Posn;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test the posns.
 */
public class PosnTest {

    Posn p0 = new Posn(0, 0);
    Posn p1 = new Posn(12, 34);
    Posn p2 = new Posn(-45, 67);
    Posn p3 = new Posn(-89, -90);
    Posn p4 = new Posn(100, -123);

    @Test
    public void testPosn() {
        assertEquals("{x=12, y=34}", p1.toString());
        assertEquals(1367, p1.hashCode());

        assertEquals(p0, new Posn(0, 0));
        assertEquals(p3, new Posn(-90 + 1, -90));
        assertNotEquals(p1, p2);
        assertNotEquals(p4, p3);

        assertEquals(0, p0.x);
        assertEquals(0, p0.y);
        assertEquals(-45, p2.x);
        assertEquals(-90, p3.y);
    }
}
