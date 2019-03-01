package cs3500.marblesolitaire;

import java.util.Arrays;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestMSRunner {

  @Test
  public void goodSizeParse() {
    String[] nothingExtra = {"european"};
    assertEquals(MarbleSolitaire.parseForSize(nothingExtra, -1), -1);

    String[] noSizePresent = {"english", " -hole", "5", "4"};
    assertEquals(MarbleSolitaire.parseForSize(noSizePresent, -1), -1);

    String[] sizePresent1 = {"triangular", "-size", "6"};
    assertEquals(MarbleSolitaire.parseForSize(sizePresent1, -1), 6);

    String[] sizePresent2 = {"triangular", "-size", "-1"};
    assertEquals(MarbleSolitaire.parseForSize(sizePresent2, -1), -1);

    String[] sizePresent3 = {"triangular", "-hole", "2", "3", "-size", "12"};
    assertEquals(MarbleSolitaire.parseForSize(sizePresent3, -1), 12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badSizeParse1() {
    String[] mArgs = {"triangular", "-size"};
    MarbleSolitaire.parseForSize(mArgs, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badSizeParse2() {
    String[] mArgs = {"triangular", "-size", "K"};
    MarbleSolitaire.parseForSize(mArgs, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badSizeParse3() {
    String[] mArgs = {"triangular", "-size", "-hole"};
    MarbleSolitaire.parseForSize(mArgs, -1);
  }

  @Test
  public void goodHoleParse() {
    String[] nothingExtra = {"european"};
    assertFalse(MarbleSolitaire.parseForHole(nothingExtra).isPresent());

    String[] noHolePresent = {"english", " -size", "4"};
    assertFalse(MarbleSolitaire.parseForHole(noHolePresent).isPresent());

    String[] holePresent1 = {"triangular", "-hole", "1", "2"};
    assertEquals(MarbleSolitaire.parseForHole(holePresent1).get(),
        Arrays.asList(1, 2));

    String[] holePresent2 = {"triangular", "-hole", "-1", "-2"};
    assertEquals(MarbleSolitaire.parseForHole(holePresent2).get(),
        Arrays.asList(-1, -2));

    String[] holePresent3 = {"triangular", "-size", "3", "-hole", "123", "4"};
    assertEquals(MarbleSolitaire.parseForHole(holePresent3).get(),
        Arrays.asList(123, 4));
  }

  @Test(expected = IllegalArgumentException.class)
  public void badHoleParse1() {
    String[] mArgs = {"european", "-hole", "1"};
    MarbleSolitaire.parseForHole(mArgs);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badHoleParse2() {
    String[] mArgs = {"european", "-hole", "a"};
    MarbleSolitaire.parseForHole(mArgs);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badHoleParse3() {
    String[] mArgs = {"european", "-hole", "1", "a"};
    MarbleSolitaire.parseForHole(mArgs);
  }


  @Test(expected = IllegalArgumentException.class)
  public void badHoleParse4() {
    String[] mArgs = {"european", "-hole", "-size"};
    MarbleSolitaire.parseForHole(mArgs);
  }


  @Test(expected = IllegalArgumentException.class)
  public void badHoleParse5() {
    String[] mArgs = {"european", "-size", "1", "-hole", " 5"};
    MarbleSolitaire.parseForHole(mArgs);
  }
}
