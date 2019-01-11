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

  @Test
  public void testAllSpecifierChars() {
    ArrayList hmsKeys = new ArrayList<>(Arrays.asList('s', 'S', 'm', 'M', 'h', 'H'));
    char percKey = '%';
    char durKey = 't';

    for (char curr = 32; curr < 127; curr++) {
      //testing to ensure that the specifier character is recognized and replaced
      if (hmsKeys.contains(curr)) {
        assertNotEquals("%" + curr + 't', sec(123).format("%" + curr + 't'));
        assertEquals("33t", hms(33, 33, 33).format("%" + curr) + 't');
      }

      if (curr == percKey) {
        assertNotEquals("%" + curr + 't', sec(123).format("%" + curr + 't'));
        assertEquals("%t", hms(33, 33, 33).format("%" + curr) + 't');
      }

      if (curr == durKey) {
        assertNotEquals("%" + curr + 't', sec(123).format("%" + curr + 't'));
        assertEquals("120813t", hms(33, 33, 33).format("%" + curr + 't'));
      }
    }
  }

  @Test
  public void testAllFixedChars() {
    ArrayList specialKeys = new ArrayList<>(Arrays.asList('%', 't', 's', 'S', 'm', 'M', 'h', 'H'));
    for (char curr = 32; curr < 127; curr++) {
      if (!specialKeys.contains(curr)) {
        try {
          hms(1, 2, 3).format("%" + curr);

          // testing to ensure that the previous line throws an error to be caught
          assertNotEquals(1, 1);
        } catch (IllegalArgumentException e) {

          // testing to ensure that the exception is some non-null message
          // (server message may be different from mine)
          assertNotEquals(null, e.getMessage());
        }
      }
    }
  }

  @Test
  public void nonFormatting1() {
    assertEquals("nothing is formatted", hms(12, 14, 13).format("nothing is formatted"));
  }

  @Test
  public void nonFormatting2() {
    assertEquals("time's up: 0:00:0, total:0", sec(0).format("time's up: %h:%M:%s, total:%t"));
  }

  @Test
  public void nonFormatting3() {
    assertEquals("time's up: 00:0:00, total:0",
            hms(0, 0, 0).format("time's up: %H:%m:%S, total:%t"));
  }


  @Test
  public void basicFormatting() {
    assertEquals("12345seconds", hms(3, 25, 45).format("%tseconds"));
  }

  @Test
  public void basicHMSFormatting1() {
    assertEquals("3 hours, 37 minutes, 18 seconds.",
            hms(3, 37, 18).format("%h hours, %M minutes, %s seconds."));
  }

  @Test
  public void basicHMSFormatting2() {
    assertEquals("03:05:09 with leading zeros",
            hms(3, 5, 9).format("%H:%M:%S with leading zeros"));
  }

  @Test
  public void basicHMSFormatting3() {
    assertEquals("04 hours, 5 minutes, and 17 seconds", sec(14717).format("%H "
            + "hours, %m minutes, and %s seconds"));
  }


  @Test
  public void basicHMSFormatting4() {
    assertEquals("13:55:39 without leading zeros",
            hms(13, 55, 39).format("%h:%m:%s without leading zeros"));
  }

  @Test
  public void basicHMSFormatting5() {
    assertEquals("13:55:39 without leading zeros",
            hms(13, 55, 39).format("%H:%M:%S without leading zeros"));
  }

  @Test
  public void consistentZerofill1() {
    assertEquals(hms(11, 22, 33).format("%h %m %s"),
            hms(11, 22, 33).format("%H %M %S"));
  }

  @Test
  public void consistentZerofill2() {
    assertEquals(sec(11 * 3600 + 22 * 60 + 33).format("%h %m %s"),
            sec(11 * 3600 + 22 * 60 + 33).format("%H %M %S"));
  }

  @Test
  public void consistentZerofill3() {
    assertEquals(hms(1, 2, 3).format("0%h 0%m 0%s"),
            hms(1, 2, 3).format("%H %M %S"));
  }

  @Test
  public void consistentZerofill4() {
    assertEquals(hms(1, 2, 3).format("0%h 0%m 0%s"),
            hms(1, 2, 3).format("%H %M %S"));
  }

  @Test
  public void randomCharFormating() {
    assertEquals("25 $3c0nd$ 13 m1nut3$ 03 h0ur$",
            hms(3, 13, 25).format("%s $3c0nd$ %M m1nut3$ %H h0ur$"));
  }

  @Test
  public void checkPrecedence() {
    assertEquals("s14seconds", hms(12, 13, 14).format("s%Sseconds"));
  }


  @Test
  public void checkPrecedence1() {
    assertEquals("123sec = 0m:02m:3S %H",
            hms(0, 2, 3).format("%tsec = %hm:%Mm:%sS %%H"));
  }

  @Test
  public void checkPrecedence2() {
    assertEquals("09h56m02s", hms(9, 56, 2).format("%Hh%Mm%Ss"));
  }

  @Test
  public void overflowFormatting1() {
    assertEquals("3962", hms(0, 65, 62).format("%t"));
  }

  @Test
  public void overflowFormatting2() {
    assertEquals("5 $3c0nd$ 05 m1nut3$ 04 h0ur$",
            hms(3, 63, 125).format("%s $3c0nd$ %M m1nut3$ %H h0ur$"));
  }

  @Test
  public void formatsAtEdges() {
    assertEquals("03, s:3, s:3", hms(3, 3, 3).format("%S, s:%s, s:%s"));
  }

  @Test
  public void formatsAtEdges1() {
    assertEquals("3, s:3, s:3", hms(3, 3, 3).format("%s, s:%m, s:%m"));
  }

  @Test
  public void formatsAtEdges2() {
    assertEquals("3, s:3, s:03", sec(3 * 3600 + 3 * 60 + 3).format("%m, s:%s, s:%M"));
  }

  @Test
  public void formatsAtEdges3() {
    assertEquals("3, m:47, s:03", hms(3, 47, 3).format("%h, m:%M, s:%H"));
  }

  @Test
  public void formatsAtEdges4() {
    assertEquals("03, m:47, h:03", sec(3 * 3600 + 47 * 60 + 3).format("%H, m:%m, h:%H"));
  }

  @Test
  public void formatsAtEdges5() {
    assertEquals("03, m:47, h:03", sec(3 * 3600 + 47 * 60 + 3).format("%H, m:%M, h:%H"));
  }

  @Test
  public void formatsAtEdges6() {
    assertEquals("03, s:3, s:03", sec(3 * 3600 + 3 * 60 + 3).format("%M, s:%s, s:%M"));
  }

  @Test
  public void percentLiterals1() {
    assertEquals("so%methi%ng % i%s for%Ma%t%ted",
            hms(12, 14, 13).format("so%%methi%%ng %% i%%s for%%Ma%%t%%ted"));
  }

  @Test
  public void percentLiterals2() {
    assertEquals("so%methi%ng % i%s for14a4405344053ed",
            hms(12, 14, 13).format("so%%methi%%ng %% i%%s for%Ma%t%ted"));
  }

  @Test
  public void percentLiterals3() {
    assertEquals("%%11169%%", hms(3, 6, 9).format("%%%%%t%%%%"));
  }

  @Test
  public void percentLiterals4() {
    assertEquals("%:4:05:2", hms(4, 5, 2).format("%%:%h:%M:%s"));
  }

  @Test
  public void percentLiterals5() {
    assertEquals("%:04:5:02", hms(4, 5, 2).format("%%:%H:%m:%S"));
  }

  //IllegalArgument tests

  @Test(expected = IllegalArgumentException.class)
  public void badSymbolWithGood() {
    hms(0, 1, 2).format("%tsec: %H%n%s");
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
  public void percentAtEnd2() {
    sec(72312).format("end of string: %");
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
