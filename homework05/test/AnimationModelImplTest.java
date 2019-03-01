import static org.junit.Assert.assertEquals;

import cs3500.animation.animations.ShapeAction;
import cs3500.animation.animations.printers.PrintCreate;
import cs3500.animation.animations.printers.PrintIdle;
import cs3500.animation.animations.printers.PrintMove;
import cs3500.animation.animations.printers.PrintRecolor;
import cs3500.animation.animations.printers.PrintScale;
import cs3500.animation.animations.printers.PrintTurn;
import cs3500.animation.model.AnimationModelImpl;
import cs3500.animation.shapes.LiveShape;
import cs3500.animation.utils.Posn;
import cs3500.animation.shapes.Oval;
import cs3500.animation.shapes.Rectangle;

import java.awt.Color;

import org.junit.Test;


public class AnimationModelImplTest {

  private static final int TEST_RATE = 50;
  private static final int TEST_WAIT = 10;

  @Test
  public void testAnimationState() {
    AnimationModelImpl m = new AnimationModelImpl(500, 500, Color.WHITE, TEST_RATE);

    LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200), Color.RED,
        "R");
    LiveShape c = new Oval(60, 120, 90, new Posn(440, 70), Color.BLUE,
        "C");

    Appendable actualOut = new StringBuilder();

    ShapeAction r0 = new PrintCreate(actualOut, r,0);
    ShapeAction r1 = new PrintMove(actualOut, r, 1, 10, new Posn(10, 200));
    ShapeAction r2 = new PrintMove(actualOut, r, 10, 50, new Posn(300, 300));
    ShapeAction r3 = new PrintIdle(actualOut, r, 50, 51);
    ShapeAction r4 = new PrintScale(actualOut, r, 51, 70, 0.5, 1);
    ShapeAction r5 = new PrintMove(actualOut, r, 70, 100, new Posn(200, 200));

    ShapeAction c0 = new PrintCreate(actualOut, c,0);
    ShapeAction c1 = new PrintIdle(actualOut, c, 6, 20);
    ShapeAction c2 = new PrintMove(actualOut, c,20, 50, new Posn(440, 250));
    ShapeAction c3 = new PrintMove(actualOut, c, 50, 70, new Posn(440, 370));
    ShapeAction c4 = new PrintRecolor(actualOut, c,50, 70, new Color(0, 170, 85));
    ShapeAction c5 = new PrintRecolor(actualOut, c, 70, 80,Color.GREEN);
    ShapeAction c6 = new PrintIdle(actualOut, c,80, 100);
    ShapeAction c7 = new PrintTurn(actualOut, c, 100, 120, 270);

    m.addMotions(r0,r1,r2,r3,r4,r5);
    m.addMotions(c0,c1,c2,c3,c4,c5,c6,c7);

    m.start();
    wait(TEST_WAIT);
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
    AnimationModelImpl m = new AnimationModelImpl(500, 500, Color.WHITE, TEST_RATE);

    LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200), Color.RED,
        "R");
    LiveShape c = new Oval(60, 120, 90, new Posn(440, 70), Color.BLUE,
        "C");

    Appendable actualOut = new StringBuilder();

    ShapeAction r0 = new PrintCreate(actualOut, r,0);
    ShapeAction r1 = new PrintMove(actualOut, r, 1, 10, new Posn(10, 200));
    ShapeAction r2 = new PrintMove(actualOut, r, 10, 50, new Posn(300, 300));
    ShapeAction r3 = new PrintIdle(actualOut, r, 50, 51);
    ShapeAction r4 = new PrintScale(actualOut, r, 51, 70, 0.5, 1);
    ShapeAction r5 = new PrintMove(actualOut, r, 70, 100, new Posn(200, 200));

    m.addMotions(r0,r1,r2,r3,r4,r5);
    m.start();
    wait(TEST_WAIT);

    StringBuilder expectedOut = new StringBuilder();
    expectedOut.append("shape R rectangle\n");
    expectedOut.append("motion R:\t 1 200 200 0 50 100 255 0 0\t| 10 10 200 0 50 100 255 0 0\n"
        + "motion R:\t 10 10 200 0 50 100 255 0 0\t| 50 300 300 0 50 100 255 0 0\n"
        + "still R:\t 50 300 300 0 50 100 255 0 0\t| 51 300 300 0 50 100 255 0 0\n"
        + "scale R:\t 51 300 300 0 50 100 255 0 0\t| 70 300 300 0 25 100 255 0 0\n"
        + "motion R:\t 70 300 300 0 25 100 255 0 0\t| 100 200 200 0 25 100 255 0 0\n");

    assertEquals(expectedOut.toString(), actualOut.toString());

    ShapeAction c0 = new PrintCreate(actualOut, c,0);
    ShapeAction c1 = new PrintIdle(actualOut, c, 6, 20);
    ShapeAction c2 = new PrintMove(actualOut, c,20, 50, new Posn(440, 250));
    ShapeAction c3 = new PrintMove(actualOut, c, 50, 70, new Posn(440, 370));
    ShapeAction c4 = new PrintRecolor(actualOut, c,50, 70, new Color(0, 170, 85));
    ShapeAction c5 = new PrintRecolor(actualOut, c, 70, 80,Color.GREEN);
    ShapeAction c6 = new PrintIdle(actualOut, c,80, 100);
    ShapeAction c7 = new PrintTurn(actualOut, c, 100, 120, 270);

    m.addMotions(c0,c1,c2,c3,c4,c5,c6,c7);

    m.restart();
    wait(TEST_WAIT);
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


  private void wait(int secs){
    System.out.println("[INFO]\tTest is running in 'real' time....");
    try {
      Thread.sleep(secs * 1000);
    } catch (InterruptedException e){
      e.printStackTrace();
    }
    System.out.println("[INFO]\t...Test is over.");
  }
}