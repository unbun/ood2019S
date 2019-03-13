import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import cs3500.animation.shapes.Rectangle;
import cs3500.animation.utils.Posn;
import cs3500.animation.utils.Utils;
import java.awt.Color;
import java.util.HashMap;
import org.junit.Test;

/**
 * Test the utils class.
 */
public class UtilsTest {

  @Test
  public void nonNeg1() {
    assertEquals(0, Utils.requireNonNegative(0, "zero"));
    assertEquals(12, Utils.requireNonNegative(12, "twelve"));
    assertEquals(56.789, Utils.requireNonNegative(56.789, "56 and change"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void nonNeg2() {
    Utils.requireNonNegative(-5, "bad");
  }

  @Test(expected = IllegalArgumentException.class)
  public void nonNeg3() {
    Utils.requireNonNegative(-123.213, "bad");
  }

  @Test(expected = IllegalArgumentException.class)
  public void nonNeg4() {
    Utils.requireNonNegative(-0.00001, "bad");
  }

  @Test
  public void testReflection() {
    HashMap<String, Object> test1 = null;

    try {
      test1 = Utils.reflexiveCopy(
          new Rectangle(100, 6, -90, new Posn(-4, 5), Color.red, "R1"),
          1);
    } catch (IllegalAccessException e) {
      fail("test shouldn't fail");
    }

    assertEquals(100, test1.get("height"));
    assertEquals(6, test1.get("width"));
    assertEquals(-90, test1.get("heading"));
    assertEquals(new Posn(-4, 5), test1.get("posn"));
    assertEquals(Color.red, test1.get("color"));
    assertEquals("R1", test1.get("name"));
  }

  @Test
  public void testReflection2() {
    HashMap<String, Object> test1 = null;
    // test big superLvl
    try {
      test1 = Utils.reflexiveCopy(
          new Rectangle(100, 6, -90, new Posn(-4, 5), Color.red, "R1"),
          3);
    } catch (IllegalAccessException e) {
      fail("test shouldn't fail");
    }

    assertEquals(100, test1.get("height"));
    assertEquals(6, test1.get("width"));
    assertEquals(-90, test1.get("heading"));
    assertEquals(new Posn(-4, 5), test1.get("posn"));
    assertEquals(Color.red, test1.get("color"));
    assertEquals("R1", test1.get("name"));
  }

  @Test
  public void testReflection3() {
    HashMap<String, Object> test1 = null;
    //test ignoring
    try {
      test1 = Utils.reflexiveCopy(
          new Rectangle(100, 6, -90, new Posn(-4, 5), Color.red, "R1"),
          2, "height", "posn", "name");
    } catch (IllegalAccessException e) {
      fail("test shouldn't fail");
    }

    assertNull(test1.get("height"));
    assertEquals(6, test1.get("width"));
    assertEquals(-90, test1.get("heading"));
    assertNull(test1.get("posn"));
    assertEquals(Color.red, test1.get("color"));
    assertNull(test1.get("name"));
  }

  @Test
  public void testReflection4() {
    HashMap<String, Object> test1 = null;
    //test ignoring again
    try {
      test1 = Utils.reflexiveCopy(new Posn(-4, 5), 0, "x");
    } catch (IllegalAccessException e) {
      fail("test shouldn't fail");
    }

    assertNull(test1.get("x"));
    assertEquals(5, test1.get("y"));
  }


}
