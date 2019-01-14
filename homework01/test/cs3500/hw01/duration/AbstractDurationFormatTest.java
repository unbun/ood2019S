package cs3500.hw01.duration;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

//TODO: test negatives(?)
//TODO: check variables that are too big

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

  @Test
  public void testAllChars() {
    ArrayList specifiers = new ArrayList<>(Arrays.asList('%', 't', 's', 'S', 'm', 'M', 'h', 'H'));
    for (char curr = 32; curr < 128; curr++) {
      if (specifiers.contains(curr)) {
        //make sure specifiers are identified
        assertNotEquals("%" + curr, sec(123).format("%" + curr));
      } else {
        try {
          hms(1, 2, 3).format("%" + curr);
          fail("non-specifier should throw error");
          // line throws an error to be caught
        } catch (IllegalArgumentException e) {
          assertEquals("Malformed format: % must be followed by a special char",
                  e.getMessage());
        }
      }
    }
  }

  @Test
  public void testFormatting() {
    assertEquals("", hms(12,14,13).format(""));

    assertEquals("nothing is formatted", hms(12, 14, 13).format("nothing is formatted"));

    assertEquals("3 hours, 5 minutes, 8 seconds.",
            hms(3, 5, 8).format("%h hours, %m minutes, %s seconds."));
    assertEquals("03:05:09 with leading zeros",
            hms(3, 5, 9).format("%H:%M:%S with leading zeros"));

    assertEquals("time's up: 0:00:0, total:0", sec(0).format("time's up: %h:%M:%s, total:%t"));
    assertEquals("time's up: 00:0:00, total:0",
            hms(0, 0, 0).format("time's up: %H:%m:%S, total:%t"));
  }

  @Test
  public void testZerofill1() {
    assertEquals(hms(11, 22, 33).format("%h %m %s"),
            hms(11, 22, 33).format("%H %M %S"));
    assertEquals(sec(11 * 3600 + 22 * 60 + 33).format("%h %m %s"),
            sec(11 * 3600 + 22 * 60 + 33).format("%H %M %S"));
    assertEquals(hms(1, 2, 3).format("0%h 0%m 0%s"),
            hms(1, 2, 3).format("%H %M %S"));
    assertEquals(hms(1, 2, 3).format("0%h 0%m 0%s"),
            hms(1, 2, 3).format("%H %M %S"));
  }

  @Test
  public void testPrecedence() {
    assertEquals("s14seconds", hms(12, 13, 14).format("s%Sseconds"));
    assertEquals("123sec = 0m:02m:3S %H",
            hms(0, 2, 3).format("%tsec = %hm:%Mm:%sS %%H"));
    assertEquals("723sec = 0m:12m:3S %H",
            hms(0, 12, 3).format("%tsec = %hm:%Mm:%sS %%H"));
    assertEquals("723sec = 0m:12m:3S %H",
            hms(0, 12, 3).format("%tsec = %hm:%mm:%sS %%H"));
  }

  @Test
  public void formatsAtEdges() {
    assertEquals("03, s:3, s:3", hms(3, 3, 3).format("%S, s:%s, s:%s"));
    assertEquals("3, s:3, s:3", hms(3, 3, 3).format("%s, s:%m, s:%m"));
    assertEquals("3, s:3, s:03", sec(3 * 3600 + 3 * 60 + 3).format("%m, s:%s, s:%M"));
    assertEquals("03, m:47, h:03", sec(3 * 3600 + 47 * 60 + 3).format("%H, m:%m, h:%H"));
  }


  @Test
  public void percentLiterals1() {
    assertEquals("so%methi%ng % i%s for%Ma%t%ted",
            hms(12, 14, 13).format("so%%methi%%ng %% i%%s for%%Ma%%t%%ted"));
    assertEquals("so%methi%ng % i%s for14a4405344053ed",
            hms(12, 14, 13).format("so%%methi%%ng %% i%%s for%Ma%t%ted"));
    assertEquals("%:4:05:2", hms(4, 5, 2).format("%%:%h:%M:%s"));
    assertEquals("%:04:5:02", hms(4, 5, 2).format("%%:%H:%m:%S"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void badSymbolWithGood() {
    hms(0, 1, 2).format("%tsec: %H%n%s");
  }

  @Test(expected = IllegalArgumentException.class)
  public void percentAtEnd1() {
    hms(7, 23, 12).format("end of string: %");
  }

  @Test(expected = IllegalArgumentException.class)
  public void spaceAfterPerc() {
    hms(7, 23, 12).format("%% % : beg of string");
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
