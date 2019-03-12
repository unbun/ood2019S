import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs3500.animation.actions.Transform;
import cs3500.animation.actions.printers.*;
import cs3500.animation.model.AnimationModelImpl;
import cs3500.animation.shapes.LiveShape;
import cs3500.animation.utils.Posn;
import cs3500.animation.shapes.Oval;
import cs3500.animation.shapes.Rectangle;

import java.awt.Color;

import org.junit.Test;

/**
 * Test the model.
 */
public class AnimationModelImplTest {

  private static final int TEST_RATE = 50;
  private static final int TEST_WAIT = 10;

  @Test
  public void testAnimationState() {
    AnimationModelImpl m = new AnimationModelImpl(TEST_RATE);

    LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200), Color.RED,
        "R");
    LiveShape c = new Oval(60, 120, 90, new Posn(440, 70), Color.BLUE,
        "C");

    Appendable actualOut = new StringBuilder();

    Transform r0 = new PrintCreate(actualOut, r, 0);
    Transform r1 = new PrintMove(actualOut, r, 1, 10, new Posn(10, 200));
    Transform r2 = new PrintMove(actualOut, r, 10, 50, new Posn(300, 300));
    Transform r3 = new PrintIdle(actualOut, r, 50, 51);
    Transform r4 = new PrintScale(actualOut, r, 51, 70, 0.5, 1);
    Transform r5 = new PrintMove(actualOut, r, 70, 100, new Posn(200, 200));

    Transform c0 = new PrintCreate(actualOut, c, 0);
    Transform c1 = new PrintIdle(actualOut, c, 6, 20);
    Transform c2 = new PrintMove(actualOut, c, 20, 50, new Posn(440, 250));
    Transform c3 = new PrintMove(actualOut, c, 50, 70, new Posn(440, 370));
    Transform c4 = new PrintRecolor(actualOut, c, 50, 70, new Color(0, 170, 85));
    Transform c5 = new PrintRecolor(actualOut, c, 70, 80, Color.GREEN);
    Transform c6 = new PrintIdle(actualOut, c, 80, 100);
    Transform c7 = new PrintTurn(actualOut, c, 100, 120, 270);

    m.addMotions(r0, r1, r2, r3, r4, r5);
    m.addMotions(c0, c1, c2, c3, c4, c5, c6, c7);

    m.start();
    testWait(TEST_WAIT);
    m.stop();

    StringBuilder expectedOut = new StringBuilder();
    expectedOut.append("shape R rectangle\n");
    expectedOut.append("shape C oval\n");
    expectedOut.append("motion R:\t 1 200 200 0 50 100 255 0 0\t| 10 10 200 0 50 100 255 0 0\n"
        + "still C:\t 6 440 70 90 120 60 0 0 255\t| 20 440 70 90 120 60 0 0 255\n"
        + "motion R:\t 10 10 200 0 50 100 255 0 0\t| 50 300 300 0 50 100 255 0 0\n"
        + "motion C:\t 20 440 70 90 120 60 0 0 255\t| 50 440 250 90 120 60 0 0 255\n"
        + "still R:\t 50 300 300 0 50 100 255 0 0\t| 51 300 300 0 50 100 255 0 0\n"
        + "motion C:\t 50 440 250 90 120 60 0 0 255\t| 70 440 370 90 120 60 0 0 255\n"
        + "color C:\t 50 440 370 90 120 60 0 0 255\t| 70 440 370 90 120 60 0 170 85\n"
        + "scale R:\t 51 300 300 0 50 100 255 0 0\t| 70 300 300 0 25 100 255 0 0\n"
        + "motion R:\t 70 300 300 0 25 100 255 0 0\t| 100 200 200 0 25 100 255 0 0\n"
        + "color C:\t 70 440 370 90 120 60 0 170 85\t| 80 440 370 90 120 60 0 255 0\n"
        + "still C:\t 80 440 370 90 120 60 0 255 0\t| 100 440 370 90 120 60 0 255 0\n"
        + "rotate C:\t 100 440 370 90 120 60 0 255 0\t| 120 440 370 270 120 60 0 255 0\n");

    assertEquals(expectedOut.toString(), actualOut.toString());
  }

  @Test
  public void testAnimationState2() {
    AnimationModelImpl m = new AnimationModelImpl(TEST_RATE);

    LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200), Color.RED,
        "R");
    LiveShape c = new Oval(60, 120, 90, new Posn(440, 70), Color.BLUE,
        "C");

    Appendable actualOut = new StringBuilder();

    Transform r0 = new PrintCreate(actualOut, r, 0);
    Transform r1 = new PrintMove(actualOut, r, 1, 10, new Posn(10, 200));
    Transform r2 = new PrintMove(actualOut, r, 10, 50, new Posn(300, 300));
    Transform r3 = new PrintIdle(actualOut, r, 50, 51);
    Transform r4 = new PrintScale(actualOut, r, 51, 70, 0.5, 1);
    Transform r5 = new PrintMove(actualOut, r, 70, 100, new Posn(200, 200));

    m.addMotions(r0, r1, r2, r3, r4, r5);
    m.start();
    testWait(TEST_WAIT);

    StringBuilder expectedOut = new StringBuilder();
    expectedOut.append("shape R rectangle\n");
    expectedOut.append("motion R:\t 1 200 200 0 50 100 255 0 0\t| 10 10 200 0 50 100 255 0 0\n"
        + "motion R:\t 10 10 200 0 50 100 255 0 0\t| 50 300 300 0 50 100 255 0 0\n"
        + "still R:\t 50 300 300 0 50 100 255 0 0\t| 51 300 300 0 50 100 255 0 0\n"
        + "scale R:\t 51 300 300 0 50 100 255 0 0\t| 70 300 300 0 25 100 255 0 0\n"
        + "motion R:\t 70 300 300 0 25 100 255 0 0\t| 100 200 200 0 25 100 255 0 0\n");

    assertEquals(expectedOut.toString(), actualOut.toString());

    Transform c0 = new PrintCreate(actualOut, c, 0);
    Transform c1 = new PrintIdle(actualOut, c, 6, 20);
    Transform c2 = new PrintMove(actualOut, c, 20, 50, new Posn(440, 250));
    Transform c3 = new PrintMove(actualOut, c, 50, 70, new Posn(440, 370));
    Transform c4 = new PrintRecolor(actualOut, c, 50, 70, new Color(0, 170, 85));
    Transform c5 = new PrintRecolor(actualOut, c, 70, 80, Color.GREEN);
    Transform c6 = new PrintIdle(actualOut, c, 80, 100);
    Transform c7 = new PrintTurn(actualOut, c, 100, 120, 270);

    m.addMotions(c0, c1, c2, c3, c4, c5, c6, c7);

    m.resetTime();
    testWait(TEST_WAIT);
    m.stop();

    expectedOut.append("shape C oval\n");
    expectedOut.append("still C:\t 6 440 70 90 120 60 0 0 255\t| 20 440 70 90 120 60 0 0 255\n"
        + "motion C:\t 20 440 70 90 120 60 0 0 255\t| 50 440 250 90 120 60 0 0 255\n"
        + "motion C:\t 50 440 250 90 120 60 0 0 255\t| 70 440 370 90 120 60 0 0 255\n"
        + "color C:\t 50 440 370 90 120 60 0 0 255\t| 70 440 370 90 120 60 0 170 85\n"
        + "color C:\t 70 440 370 90 120 60 0 170 85\t| 80 440 370 90 120 60 0 255 0\n"
        + "still C:\t 80 440 370 90 120 60 0 255 0\t| 100 440 370 90 120 60 0 255 0\n"
        + "rotate C:\t 100 440 370 90 120 60 0 255 0\t| 120 440 370 270 120 60 0 255 0\n");

    assertEquals(expectedOut.toString(), actualOut.toString());
  }

  @Test
  public void timerStuff() {
    AnimationModelImpl m = new AnimationModelImpl(1000);

    assertEquals(-1, m.currTime());
    testWait(2, 0);
    assertEquals(-1, m.currTime()); //timer hasn't started

    m.start();
    testWait(3, 0);
    assertEquals(3, m.currTime());

    testWait(1, 0);
    assertEquals(4, m.currTime());

    m.resetTime();
    assertEquals(0, m.currTime());
    testWait(1, 0);
    assertEquals(1, m.currTime());
    testWait(1, 0);
    m.stop();
    assertEquals(2, m.currTime());
    testWait(4, 0);
    assertEquals(2, m.currTime());
  }

  //Timer Tests

  @Test
  public void startedTimer() {
    AnimationModelImpl m = new AnimationModelImpl(1000);
    m.start();
    testWait(1);
    assertEquals(1, m.currTime());

    try {
      m.start();
      fail("Shouldn't start an already started timer");
    } catch (IllegalStateException se) {
      assertEquals(1, m.currTime());
    }
  }

  @Test
  public void stoppedTimer() {
    AnimationModelImpl m = new AnimationModelImpl(1000);
    m.start();
    testWait(1);
    assertEquals(1, m.currTime());
    m.stop();

    try {
      m.start();
      fail("Shouldn't start a stopped a timer");
    } catch (IllegalStateException se) {
      assertEquals(1, m.currTime());
    }
  }

  @Test
  public void resetTimer() {
    AnimationModelImpl m = new AnimationModelImpl(1000);
    m.start();
    testWait(1);
    assertEquals(1, m.currTime());
    m.resetTime();

    try {
      m.start();
      fail("Shouldn't start an already started timer");
    } catch (IllegalStateException se) {
      assertEquals(0, m.currTime());
    }
  }


  private void testWait(double secs) {
    this.testWait(secs, 0.001);
  }


  private void testWait(double secs, double raceDelay) {
    secs += raceDelay; //prevent race conditions in tests

    System.out.println("[INFO]\tTest is running for " + secs + " seconds...");
    try {
      Thread.sleep((int) (secs * 1000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("[INFO]\t...Test is over.");
  }
}