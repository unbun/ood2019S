import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs3500.animator.model.TextFXModel;
import cs3500.animator.model.ShapeFXModel;
import cs3500.animator.shapes.LiveShape;
import cs3500.animator.shapes.Oval;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.transforms.Transform;
import cs3500.animator.transforms.shapefx.Idle;
import cs3500.animator.transforms.shapefx.Move;
import cs3500.animator.transforms.shapefx.Recolor;
import cs3500.animator.transforms.shapefx.Scale;
import cs3500.animator.transforms.shapefx.Turn;
import cs3500.animator.util.Posn;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

/**
 * Test the model.
 */
public class TextFXModelTest {

  private static final int TEST_RATE = 50;
  private static final int TEST_WAIT = 10;

  @Test(expected = IllegalArgumentException.class)
  public void repeatedShape(){
    LiveShape r1 = new Rectangle(0,0,0,0,0,0,Color.RED,"R");
    LiveShape r2 = new Rectangle(1,1,1,1,1,1,Color.BLUE,"R");
    ArrayList<LiveShape> shapes = new ArrayList<>(Arrays.asList(r1,r2));
    ShapeFXModel bad = new TextFXModel(TEST_RATE, shapes);
  }

  @Test
  public void addShapes() {
    ShapeFXModel m = new TextFXModel(TEST_RATE);

    LiveShape r1 = new Rectangle(0,0,0,0,0,0,Color.RED,"R");
    LiveShape o1 = new Oval(1,1,1,1,1,1,Color.BLUE,"R");

    assertEquals(0, m.currentShapes().size());
    m.addShapes(r1, o1);
    assertEquals(2, m.currentShapes().size());

    ShapeFXModel m2 = new TextFXModel(TEST_RATE, new ArrayList<>(Arrays.asList(r1)));
    assertEquals(1, m2.currentShapes().size());
    m2.addShapes(o1);
    assertEquals(2, m2.currentShapes().size());

  }

  @Test
  public void animationStateTest() {

    LiveShape r = new Rectangle(0, 100, 50, 0, new Posn(200, 200), Color.RED,
        "R");
    LiveShape c = new Oval(0, 60, 120, 90, new Posn(440, 70), Color.BLUE,
        "C");

    Transform r1 = new Move(1, 10, new Posn(10, 200));
    Transform r2 = new Move(10, 50, new Posn(300, 300));
    Transform r3 = new Idle(50, 51);
    Transform r4 = new Scale(51, 70, 0.5, 1);
    Transform r5 = new Move(70, 100, new Posn(200, 200));
    r.addTransforms(r1, r2, r3, r4, r5);


    Transform c1 = new Idle(6, 20);
    Transform c2 = new Move(20, 50, new Posn(440, 250));
    Transform c3 = new Move(50, 70, new Posn(440, 370));
    Transform c4 = new Recolor(50, 70, new Color(0, 170, 85));
    Transform c5 = new Recolor(70, 80, Color.GREEN);
    Transform c6 = new Idle(80, 100);
    Transform c7 = new Turn(100, 120, 270);
    c.addTransforms(c1, c2, c3, c4, c5, c6, c7);

    TextFXModel m = new TextFXModel(TEST_RATE, r, c);

    String expectedOut = "shape R rectangle\n" +
        "motion R:\t 1 200 200 0 50 100 255 0 0\t| 10 10 200 0 50 100 255 0 0\n"
        + "motion R:\t 10 10 200 0 50 100 255 0 0\t| 50 300 300 0 50 100 255 0 0\n"
        + "freeze R:\t 50 300 300 0 50 100 255 0 0\t| 51 300 300 0 50 100 255 0 0\n"
        + "scale R:\t 51 300 300 0 50 100 255 0 0\t| 70 300 300 0 25 100 255 0 0\n"
        + "motion R:\t 70 300 300 0 25 100 255 0 0\t| 100 200 200 0 25 100 255 0 0\n"
        + "shape C oval\n"
        + "freeze C:\t 6 440 70 90 120 60 0 0 255\t| 20 440 70 90 120 60 0 0 255\n"
        + "motion C:\t 20 440 70 90 120 60 0 0 255\t| 50 440 250 90 120 60 0 0 255\n"
        + "motion C:\t 50 440 250 90 120 60 0 0 255\t| 70 440 370 90 120 60 0 0 255\n"
        + "color C:\t 50 440 370 90 120 60 0 0 255\t| 70 440 370 90 120 60 0 170 85\n"
        + "color C:\t 70 440 370 90 120 60 0 170 85\t| 80 440 370 90 120 60 0 255 0\n"
        + "freeze C:\t 80 440 370 90 120 60 0 255 0\t| 100 440 370 90 120 60 0 255 0\n"
        + "rotate C:\t 100 440 370 90 120 60 0 255 0\t| 120 440 370 270 120 60 0 255 0\n";

    m.start();
    testWait(TEST_WAIT);
    assertEquals(expectedOut, m.getView());
  }


  @Test
  public void timerStuff() {
    ShapeFXModel m = new TextFXModel(1000);

    assertEquals(-1, m.getTime());
    testWait(2, 0);
    assertEquals(-1, m.getTime()); //timer hasn't started

    m.start();
    testWait(3, 0);
    assertEquals(3, m.getTime());

    testWait(1, 0);
    assertEquals(4, m.getTime());

    m.restart();
    assertEquals(0, m.getTime());
    testWait(1, 0);
    assertEquals(1, m.getTime());
    testWait(1, 0);
    m.stop();
    assertEquals(2, m.getTime());
    testWait(4, 0);
    assertEquals(2, m.getTime());
  }

  // Timer Tests

  @Test
  public void startedTimer() {
    ShapeFXModel m = new TextFXModel(1000);
    m.start();
    testWait(1);
    assertEquals(1, m.getTime());

    try {
      m.start();
      fail("Shouldn't start an already started timer");
    } catch (IllegalStateException se) {
      assertEquals(1, m.getTime());
    }
  }

  @Test
  public void stoppedTimer() {
    ShapeFXModel m = new TextFXModel(1000);
    m.start();
    testWait(1);
    assertEquals(1, m.getTime());
    m.stop();

    try {
      m.start();
      fail("Shouldn't start a stopped a timer");
    } catch (IllegalStateException se) {
      assertEquals(1, m.getTime());
    }
  }

  @Test
  public void resetTimer() {
    ShapeFXModel m = new TextFXModel(1000);
    m.start();
    testWait(1);
    assertEquals(1, m.getTime());
    m.restart();

    try {
      m.start();
      fail("Shouldn't start an already started timer");
    } catch (IllegalStateException se) {
      assertEquals(0, m.getTime());
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