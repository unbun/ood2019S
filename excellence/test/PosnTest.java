import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.animator.util.Posn;
import org.junit.Test;

/**
 * Test the posns.
 */
public class PosnTest {

  Posn p0 = new Posn(0, 0);
  Posn p1 = new Posn(12, 34);
  Posn p2 = new Posn(-45, 67.1);
  Posn p3 = new Posn(-89.3, -90.2);
  Posn p4 = new Posn(100.1, -123);

  @Test
  public void testPosns() {
    assertEquals(p0, new Posn(0, 0));
    assertEquals(p3, new Posn(-90.3 + 1, -90 - 0.2));
    ;
    assertNotEquals(p1, p2);
    assertNotEquals(p3, p4);
    p1.setX(-45);
    p1.setY(67.1);
    assertEquals(-45.0, p1.getX(), 0.001);
    assertEquals(67.1, p1.getY(), 0.001);
    assertEquals(p1, p2);
    p3.setX(100.1);
    p3.setY(-123);
    assertEquals(p3, p4);

    assertEquals("(-45.0,67.1)", p2.getDescription());
    assertEquals("(100.1,-123.0)", p4.getDescription());
  }
}
