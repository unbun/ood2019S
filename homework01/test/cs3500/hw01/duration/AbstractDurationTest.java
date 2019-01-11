package cs3500.hw01.duration;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests for {@link Duration}s.
 */
public abstract class AbstractDurationTest {
  /*
    Leave this section alone: It contains two abstract methods to
    create Durations, and concrete implementations of this testing class
    will supply particular implementations of Duration to be used within 
    your tests.
   */

  static long s1 = 328375982;
  static long s2 = 299699;
  Duration d1_23_45 = hms(1, 23, 45);
  Duration d2_03_00 = hms(2, 3, 0);


  // The interesting tests for Durations begin here.
  // Note how all the tests are defined in terms of hms(...) and sec(...),
  // rather than being defined using "new CompactDuration(...)"
  // or "new HmsDuration(...)".  In this way, they're *abstracted* over
  // which kind of Duration they're testing -- because it shouldn't matter!
  // When JUnit runs, it will find the two classes above, and instantiate them.
  // They in turn specialize the hms(...) and sec(...) methods to produce the
  // two kinds of Durations, but they inherit all the test methods below.
  // That means JUnit will run all the test methods below twice: once for
  // CompactDurations and once for HmsDurations.
  // It's almost like AbstractDurationTest is a "factory" for making tests,
  // and in fact, this is a variation on a pattern we'll see again later
  // in the course.
  Duration d457_03_00 = hms(457, 3, 0);

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

  @Test
  public void zeroIsZero() {
    assertEquals(0, hms(0, 0, 0).inSeconds());
  }

  @Test
  public void asHmsWorks() {
    assertEquals("1:23:45", d1_23_45.asHms());
    assertEquals("2:03:00", d2_03_00.asHms());
    assertEquals("457:03:00", d457_03_00.asHms());
  }

  @Test
  public void equalsWorks() {
    assertEquals(hms(1, 23, 45), d1_23_45);
    assertEquals(d1_23_45, d1_23_45);
    assertEquals(sec(s1), sec(s1));
    assertEquals(sec(s2), sec(s2));

    assertNotEquals(sec(s1), sec(s2));
    assertNotEquals(sec(s2), sec(s1));
    assertNotEquals(d1_23_45, d2_03_00);
    assertNotEquals(d2_03_00, d1_23_45);
    assertNotEquals(hms(1, 23, 44), hms(1, 23, 45));
  }

  @Test
  public void hashCodeIsHashOfSeconds() {
    assertEquals(Long.hashCode(s1), sec(s1).hashCode());
    assertEquals(Long.hashCode(s2), sec(s2).hashCode());
    assertEquals(Long.hashCode(d1_23_45.inSeconds()), d1_23_45.hashCode());
    assertEquals(Long.hashCode(d457_03_00.inSeconds()), d457_03_00.hashCode());
  }

  @Test
  public void hmsConstructorCarriesMinutesAndSeconds() {
    assertEquals("2:33:45", hms(1, 93, 45).asHms());
    assertEquals("2:34:08", hms(2, 33, 68).asHms());
    assertEquals("4:34:34", hms(2, 33, 7294).asHms());
  }

  @Test(expected = IllegalArgumentException.class)
  public void hmsConstructorDisallowsNegative() {
    hms(2, 4, -9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void secondsConstructorDisallowsNegative() {
    sec(-1);
  }

  @Test
  public void inSecondsIsInverseOfSecondsConstructor() {
    assertEquals(s1, sec(s1).inSeconds());
    assertEquals(s2, sec(s2).inSeconds());

    assertEquals(sec(s1),
            sec(sec(s1).inSeconds()));
    assertEquals(sec(s2),
            sec(sec(s2).inSeconds()));

    assertEquals(d1_23_45,
            sec(d1_23_45.inSeconds()));
    assertEquals(d2_03_00,
            sec(d2_03_00.inSeconds()));
    assertEquals(d457_03_00,
            sec(d457_03_00.inSeconds()));
  }

  @Test
  public void _1_23_03_plus_2_14_45_is_3_37_48() {
    assertEquals(hms(3, 37, 48),
            hms(1, 23, 3).plus(hms(2, 14, 45)));
  }

  @Test
  public void plusCarries() {
    assertEquals(hms(3, 38, 18),
            hms(1, 23, 33).plus(hms(2, 14, 45)));
    assertEquals(hms(4, 19, 48),
            hms(1, 23, 3).plus(hms(2, 56, 45)));
    assertEquals(hms(4, 20, 18),
            hms(1, 23, 33).plus(hms(2, 56, 45)));
  }

  @Test
  public void addWorks() {
    assertEquals(s1 + s2, sec(s1).plus(sec(s2)).inSeconds());
    assertEquals(2 * s1 + s2, sec(2 * s1).plus(sec(s2)).inSeconds());
  }

  public static final class HmsDurationTest extends AbstractDurationTest {
    @Override
    protected Duration hms(int hours, int minutes, int seconds) {
      return new HmsDuration(hours, minutes, seconds);
    }

    @Override
    protected Duration sec(long inSeconds) {
      return new HmsDuration(inSeconds);
    }
  }

  public static final class CompactDurationTest extends AbstractDurationTest {
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
