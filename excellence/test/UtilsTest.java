import cs3500.animator.util.Utils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the util class.
 */
public class UtilsTest {

    @Test
    public void nonNeg1() {
        assertEquals(new Integer(0), Utils.requireNonNegative(0, "zero"));
        assertEquals(new Integer(12), Utils.requireNonNegative(12));
        assertEquals(new Double(56.789), Utils.requireNonNegative(56.789, "56 and change"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonNeg2() {
        Utils.requireNonNegative(-5, "bad");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonNeg3() {
        Utils.requireNonNegative(-123.213);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonNeg4() {
        Utils.requireNonNegative(-0.00001, "bad");
    }

}
