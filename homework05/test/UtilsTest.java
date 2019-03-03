import static org.junit.Assert.assertEquals;

import cs3500.animation.utils.Utils;
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


}
