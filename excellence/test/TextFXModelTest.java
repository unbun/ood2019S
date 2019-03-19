import cs3500.animator.model.ShapeFXModel;
import cs3500.animator.model.TextFXModel;
import cs3500.animator.shapes.LiveShape;
import cs3500.animator.shapes.Oval;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.transforms.*;
import cs3500.animator.util.Posn;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test the model.
 */
public class TextFXModelTest {

    private static final int TEST_RATE = 50;
    private static final int TEST_WAIT = 10;

    @Test(expected = IllegalArgumentException.class)
    public void repeatedShape() {
        LiveShape r1 = new Rectangle(0, 0, 0, 0, 0, Color.RED, "R");
        LiveShape r2 = new Rectangle(1, 1, 1, 1, 1, Color.BLUE, "R");
        ArrayList<LiveShape> shapes = new ArrayList<>(Arrays.asList(r1, r2));
        ShapeFXModel bad = new TextFXModel(TEST_RATE, shapes);
    }

    @Test
    public void animationStateTest() {

        LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200), Color.RED,
                "R");
        LiveShape c = new Oval(60, 120, 90, new Posn(440, 70), Color.BLUE,
                "C");

        ATransform r1 = new MoveTo(1, 10, new Posn(10, 200));
        ATransform r2 = new MoveTo(10, 50, new Posn(300, 300));
        ATransform r3 = new Freeze(50, 51);
        ATransform r4 = new Resize(51, 70, 51, 70);
        ATransform r5 = new MoveTo(70, 100, new Posn(200, 200));
        r.addTransforms(r1, r2, r3, r4, r5);

        ATransform c1 = new Freeze(6, 20);
        ATransform c2 = new MoveTo(20, 50, new Posn(440, 250));
        ATransform c3 = new MoveTo(50, 70, new Posn(440, 370));
        ATransform c4 = new Recolor(50, 70, new Color(0, 170, 85));
        ATransform c5 = new Recolor(70, 80, Color.GREEN);
        ATransform c6 = new Freeze(80, 100);
        ATransform c7 = new TurnTo(100, 120, 270);
        c.addTransforms(c1, c2, c3, c4, c5, c6, c7);

        TextFXModel m = new TextFXModel(TEST_RATE, r, c);

        String expectedOut = "canvas 300 300\n\n"
                + "shape R rectangle\n"
                + "motion R:\t 1 200 200 0 50 100 255 0 0\t| 10 10 200 0 50 100 255 0 0\n"
                + "motion R:\t 10 10 200 0 50 100 255 0 0\t| 50 300 300 0 50 100 255 0 0\n"
                + "motion R:\t 50 300 300 0 50 100 255 0 0\t| 51 300 300 0 50 100 255 0 0\n"
                + "alter R:\t 51 300 300 0 50 100 255 0 0\t| 70 300 300 0 70 51 255 0 0\n"
                + "motion R:\t 70 300 300 0 70 51 255 0 0\t| 100 200 200 0 70 51 255 0 0\n"
                + "shape C oval\n"
                + "motion C:\t 6 440 70 90 120 60 0 0 255\t| 20 440 70 90 120 60 0 0 255\n"
                + "motion C:\t 20 440 70 90 120 60 0 0 255\t| 50 440 250 90 120 60 0 0 255\n"
                + "motion C:\t 50 440 250 90 120 60 0 0 255\t| 70 440 370 90 120 60 0 0 255\n"
                + "alter C:\t 50 440 370 90 120 60 0 0 255\t| 70 440 370 90 120 60 0 170 85\n"
                + "alter C:\t 70 440 370 90 120 60 0 170 85\t| 80 440 370 90 120 60 0 255 0\n"
                + "motion C:\t 80 440 370 90 120 60 0 255 0\t| 100 440 370 90 120 60 0 255 0\n"
                + "motion C:\t 100 440 370 90 120 60 0 255 0\t| 120 440 370 270 120 60 0 255 0\n";

        m.start();
        testWait(TEST_WAIT);
        assertEquals(expectedOut, m.viewNow());
    }


    @Test
    public void timerStuff() {
        ShapeFXModel m = new TextFXModel(1000);

        assertEquals(-1, m.getTime());
        testWait(2);
        assertEquals(-1, m.getTime()); //timer hasn't started

        m.start();
        testWait(3);
        assertEquals(3, m.getTime());

        testWait(1);
        assertEquals(4, m.getTime());

        m.restart();
        assertEquals(0, m.getTime());
        testWait(1);
        assertEquals(1, m.getTime());
        testWait(1);
        m.stop();
        assertEquals(2, m.getTime());
        testWait(4);
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