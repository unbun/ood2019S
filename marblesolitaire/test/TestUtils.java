import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.posn.BoardPosn;
import cs3500.marblesolitaire.model.posn.PosnState;
import cs3500.marblesolitaire.util.Utils;

import static org.junit.Assert.assertEquals;


public class TestUtils {

  @Test
  public void checkRequiresNull() {
    assertEquals("apple", Utils.requireNonNull("apple"));
    Integer i = new Integer(1);
    assertEquals(i, Utils.requireNonNull(i));
    MarbleSolitaireModel msm = new MarbleSolitaireModelImpl();
    assertEquals(msm, Utils.requireNonNull(msm));
    BoardPosn bp = new BoardPosn(6, 7, PosnState.FILLED);
    assertEquals(bp, Utils.requireNonNull(bp));
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkRequiresNull2() {
    Utils.requireNonNull(null);
  }

  @Test
  public void checkValidInt() {
    assertEquals("12", Utils.requireValidInteger("12", 0));
    assertEquals("12", Utils.requireValidInteger("12", 11));
    assertEquals("-15", Utils.requireValidInteger("-15", -20));
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkInvalidInts1() {
    Utils.requireValidInteger("-1", 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkInvalidInts2() {
    Utils.requireValidInteger("12", 13);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkInvalidInts3() {
    Utils.requireValidInteger("q", 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkInvalidInts5() {
    Utils.requireValidInteger("r", 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkInvalidInts6() {
    Utils.requireValidInteger("%", 0);
  }
}
