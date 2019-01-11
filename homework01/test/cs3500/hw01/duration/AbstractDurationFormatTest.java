package cs3500.hw01.duration;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests for the format method of {@link Duration}s. Add your tests to this class to assure that
 * your format method works properly
 */
public abstract class AbstractDurationFormatTest {
  @Test
  public void formatExample1() {
    assertEquals("4 hours, 0 minutes, and 9 seconds",
            hms(4, 0, 9)
                    .format("%h hours, %m minutes, and %s seconds"));
  }

  @Test
  public void formatExample2() {
    assertEquals("4:05:17",
            hms(4, 5, 17).format("%h:%M:%S"));
  }

  // ADD MORE TESTS HERE
  // Your tests must only use hms(...) and sec(...) to construct new Durations
  // and must *not* directly say "new CompactDuration(...)" or
  // "new HmsDuration(...)"

  @Test
  public void testSpecifierChars() {
    ArrayList specialKeys = new ArrayList<>(Arrays.asList('%', 't', 's', 'S', 'm', 'M', 'h', 'H'));
    for (char curr = 32; curr < 127; curr++) {
      if (specialKeys.contains(curr)) {
        //testing to ensure that the specifier character is recognized and replaced
        assertNotEquals("%" + curr, hms(33, 33, 33).format("%" + curr));
      }
    }
  }

  @Test
  public void testFixedChars() {
    ArrayList specialKeys = new ArrayList<>(Arrays.asList('%', 't', 's', 'S', 'm', 'M', 'h', 'H'));
    for (char curr = 32; curr < 127; curr++) {
      if (!specialKeys.contains(curr)) {
        try {
          hms(1, 2, 3).format("%" + curr);
          // testing to ensure that the previous line throws and error
          assertNotEquals(1, 1);
        } catch (IllegalArgumentException e) {
          // testing to ensure that the exception is some non-null message
          assertNotEquals(null, e.getMessage());
        }
      }
    }
  }

  @Test
  public void nonFormating() {
    assertEquals("nothing is formatted", hms(12, 14, 13).format("nothing is formatted"));

    assertEquals("time's up: 0:00:0, total:0", sec(0).format("time's up: %h:%M:%s, total:%t"));
    assertEquals("time's up: 00:0:00, total:0",
            hms(0, 0, 0).format("time's up: %H:%m:%S, total:%t"));
  }

  @Test
  public void durationFormating() {
    assertEquals("12345seconds", hms(3, 25, 45).format("%tseconds"));
    assertEquals("12346 seconds", sec(12346).format("%t seconds"));
  }

  @Test
  public void basicHMSFormating() {
    assertEquals("3 hours, 37 minutes, 18 seconds.",
            hms(3, 37, 18).format("%h hours, %m minutes, %s seconds."));
    assertEquals("03:05:09 with leading zeros",
            hms(3, 5, 9).format("%H:%M:%S with leading zeros"));
    assertEquals("13:55:39 without leading zeros",
            hms(13, 55, 39).format("%H:%M:%S without leading zeros"));
    assertEquals("% percents are fine.", hms(0, 0, 0).format("%% percents are"
            + " fine."));

    assertEquals("%:4:05:2", hms(4, 5, 2).format("%%:%h:%M:%s"));
    assertEquals("%:04:5:02", hms(4, 5, 2).format("%%:%H:%m:%S"));
    assertEquals("%:4:5:2", sec(14702).format("%%:%h:%m:%s"));
    assertEquals("%:04:05:02", sec(14702).format("%%:%H:%M:%S"));

    assertEquals("04:05:17", sec(14717).format("%H:%M:%S"));
    assertEquals("14717: total duration", hms(4, 5, 17).format("%t: total duration"));
    assertEquals("04 hours, 5 minutes, and 17 seconds", sec(14717).format("%H "
            + "hours, %m minutes, and %s seconds"));
  }

  @Test
  public void specifierOrderFormatting() {
    assertEquals("m2 minutes", sec(124).format("m%m minutes"));
    assertEquals("m02Minutes", hms(0, 2, 4).format("m%MMinutes"));
    assertEquals("56", hms(9, 56, 2).format("%m"));

    assertEquals("m12 hours", sec(43205).format("m%H hours"));
    assertEquals("s12hours", hms(12, 13, 14).format("s%hhours"));
    assertEquals("9", hms(9, 56, 2).format("%h"));

    assertEquals("m12 hours", sec(43205).format("m%H hours"));
    assertEquals("s12hours", hms(12, 13, 14).format("s%hhours"));
    assertEquals("s12minutes", hms(12, 13, 14).format("s%hminutes"));
    assertEquals("s12seconds", hms(12, 13, 14).format("s%hseconds"));
    assertEquals("h05 seconds", sec(43205).format("h%S seconds"));
    assertEquals("s14seconds", hms(12, 13, 14).format("s%sseconds"));
    assertEquals("2", hms(9, 56, 2).format("%s"));
    assertEquals("9", hms(9, 56, 2).format("%h"));

  }

  @Test
  public void randomCharFormating() {
    assertEquals("25 $3c0nd$ 13 m1nut3$ 03 h0ur$",
            hms(3, 13, 25).format("%s $3c0nd$ %M m1nut3$ %H h0ur$"));
  }

  @Test
  public void checkPrecedence() {
    assertEquals("123sec = 0m:02m:3S %H",
            hms(0, 2, 3).format("%tsec = %hm:%Mm:%sS %%H"));
    assertEquals("09h56m02s", hms(9, 56, 2).format("%Hh%Mm%Ss"));
  }

  @Test
  public void overflowFormatting() {
    assertEquals("3962", hms(0, 65, 62).format("%t"));
    assertEquals("5 $3c0nd$ 05 m1nut3$ 04 h0ur$",
            hms(3, 63, 125).format("%s $3c0nd$ %M m1nut3$ %H h0ur$"));
  }

  @Test
  public void formatsAtEdges() {
    assertEquals("s:3, s:3, s:3", hms(5, 47, 3).format("s:%s, s:%s, s:%s"));
    assertEquals("s:3, s:3, s:03", sec(20823).format("s:%s, s:%s, s:%S"));
    assertEquals("s:3, m:47, s:3", hms(5, 47, 3).format("s:%s, m:%M, s:%s"));
    assertEquals("s:3, m:47, h:05", sec(20823).format("s:%s, m:%m, h:%H"));
  }

  @Test
  public void percentLiterals() {
    assertEquals("so%methi%ng % i%s for%Ma%t%ted",
            hms(12, 14, 13).format("so%%methi%%ng %% i%%s for%%Ma%%t%%ted"));
    assertEquals("so%methi%ng % i%s for14a4405344053ed",
            hms(12, 14, 13).format("so%%methi%%ng %% i%%s for%Ma%t%ted"));
    assertEquals("%%11169%%", hms(3, 6, 9).format("%%%%%t%%%%"));

  }

  @Test(expected = IllegalArgumentException.class)
  public void badSymbolWithGood() {
    sec(25234).format("%tsec: %H%n%s");
  }

  @Test(expected = IllegalArgumentException.class)
  public void badSymbol() {
    sec(25234).format("bad format: %D %K %d %k");
  }

  @Test(expected = IllegalArgumentException.class)
  public void randomBadChar() {
    sec(25234).format("random char: %^");
  }

  @Test(expected = IllegalArgumentException.class)
  public void percentAtEnd() {
    hms(7, 23, 12).format("end of string: %");
  }

  @Test(expected = IllegalArgumentException.class)
  public void nothingAfterPerc() {
    hms(7, 23, 12).format("%% % : beg of string");
  }

  @Test(expected = IllegalArgumentException.class)
  public void nothingAfterPercAtBeg() {
    hms(7, 23, 12).format("% : beg of string");
  }

  @Test(expected = IllegalArgumentException.class)
  public void multipleBads() {
    sec(25234).format("%t sec: %j%n%s");
  }

  @Test(expected = IllegalArgumentException.class)
  public void wrongCaseForT() {
    sec(123).format("%T sec: %j%n%s");
  }

  

  /*
    Leave this section alone: It contains two abstract methods to
    create Durations, and concrete implementations of this testing class
    will supply particular implementations of Duration to be used within 
    your tests.
   */

  /**
   * Constructs an instance of the class under test representing the duration given in hours,
   * minutes, and seconds.
   *
   * @param hours   the hours in the duration
   * @param minutes the minutes in the duration
   * @param seconds the seconds in the duration
   * @return an instance of the class under test
   */
  protected abstract Duration hms(int hours, int minutes, int seconds);

  /**
   * Constructs an instance of the class under test representing the duration given in seconds.
   *
   * @param inSeconds the total seconds in the duration
   * @return an instance of the class under test
   */
  protected abstract Duration sec(long inSeconds);

  public static final class HmsDurationTest extends AbstractDurationFormatTest {
    @Override
    protected Duration hms(int hours, int minutes, int seconds) {
      return new HmsDuration(hours, minutes, seconds);
    }

    @Override
    protected Duration sec(long inSeconds) {
      return new HmsDuration(inSeconds);
    }
  }

  public static final class CompactDurationTest extends AbstractDurationFormatTest {
    @Override
    protected Duration hms(int hours, int minutes, int seconds) {
      return new CompactDuration(hours, minutes, seconds);
    }

    @Override
    protected Duration sec(long inSeconds) {
      return new CompactDuration(inSeconds);
    }
  }
}
