import cs3500.animation.actions.Transform;
import cs3500.animation.actions.printers.PrintIdle;
import cs3500.animation.actions.printers.PrintMove;
import cs3500.animation.actions.printers.PrintRecolor;
import cs3500.animation.actions.printers.PrintScale;
import cs3500.animation.actions.printers.PrintTurn;
import cs3500.animation.actions.printers.PrintCreate;
import cs3500.animation.shapes.Rectangle;
import cs3500.animation.utils.Posn;
import cs3500.animation.shapes.Oval;
import cs3500.animation.shapes.LiveShape;
import java.awt.Color;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


/**
 * Test shape actions.
 */
public class PrintersTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTimeInterval() {
    LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200),
        Color.RED, "R");
    Transform r1 = new PrintMove(new StringBuilder(), r, 50, 10,
        new Posn(300, 300));
  }

  @Test(expected = NullPointerException.class)
  public void testNullShape() {
    Transform r1 = new PrintTurn(new StringBuilder(), null, 10, 20, 15);
  }

  @Test
  public void conflictTest() {
    LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200),
        Color.RED, "R");
    LiveShape c = new Oval(50, 50, 90, new Posn(100, 150),
        Color.GREEN, "C");
    Transform r1 = new PrintMove(new StringBuilder(), r, 10, 20,
        new Posn(300, 300));
    Transform differenttype = new PrintScale(new StringBuilder(), r, 10, 20,
        2, 1);
    Transform differentshape = new PrintMove(new StringBuilder(), c, 10, 20,
        new Posn(300, 300));
    Transform create = new PrintCreate(new StringBuilder(), r, 10);
    Transform overlapconflict1 = new PrintMove(new StringBuilder(), r, 8, 12,
        new Posn(150, 200));
    Transform overlapconflict2 = new PrintMove(new StringBuilder(), r, 15, 20,
        new Posn(200, 200));
    Transform overlapconflict3 = new PrintMove(new StringBuilder(), r, 19, 25,
        new Posn(300, 300));
    assertTrue(r1.conflict(overlapconflict1));
    assertTrue(r1.conflict(overlapconflict2));
    assertTrue(r1.conflict(overlapconflict3));
    assertFalse(r1.conflict(differenttype));
    assertFalse(r1.conflict(create));
    assertFalse(r1.conflict(differentshape));
  }

  @Test
  public void transforming() {
    LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200),
        Color.RED, "R");
    Transform move = new PrintMove(new StringBuilder(), r, 10, 20,
        new Posn(300, 300));
    Transform scale = new PrintScale(new StringBuilder(), r, 10, 20,
        2, 1);
    Transform recolor = new PrintRecolor(new StringBuilder(), r, 40, 60,
        Color.BLUE);
    Transform turn = new PrintTurn(new StringBuilder(), r, 50, 60,
        60);
    Transform idle = new PrintIdle(new StringBuilder(), r, 70, 80);
    Transform create = new PrintCreate(new StringBuilder(), r, 10);
    create.apply(10);

    move.apply(11);
    LiveShape rmoved = new Rectangle(100, 50, 0, new Posn(300, 300),
        Color.RED, "R");
    assertEquals(rmoved, r);

    scale.apply(10);
    LiveShape rscaled = new Rectangle(100, 100, 0, new Posn(300, 300),
        Color.RED, "R");
    assertEquals(rscaled, r);

    LiveShape rcolored = new Rectangle(100, 100, 0, new Posn(300, 300),
        Color.BLUE, "R");
    recolor.apply(59);
    assertEquals(rcolored, r);

    LiveShape rturned = new Rectangle(100, 100, 60, new Posn(300, 300),
        Color.BLUE, "R");
    turn.apply(55);
    assertEquals(rturned, r);

    idle.apply(70);
    assertEquals(rturned, r);


  }


  @Test
  public void applyHelpTest() {
    LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200),
        Color.RED, "R");
    Transform move = new PrintMove(new StringBuilder(), r, 10, 20,
        new Posn(300, 300));
    Transform scale = new PrintScale(new StringBuilder(), r, 10, 20,
        2, 1);
    Transform recolor = new PrintRecolor(new StringBuilder(), r, 40, 60,
        Color.BLUE);
    Transform turn = new PrintTurn(new StringBuilder(), r, 50, 60,
        60);
    Transform idle = new PrintIdle(new StringBuilder(), r, 70, 80);



    Transform create = new PrintCreate(new StringBuilder(), r, 10);
    ((PrintCreate) create).applyHelp();
    ((PrintMove) move).applyHelp();
    LiveShape rmoved = new Rectangle(100, 50, 0, new Posn(300, 300),
        Color.RED, "R");
    assertEquals(rmoved, r);
    ((PrintScale) scale).applyHelp();
    LiveShape rscaled = new Rectangle(100, 100, 0, new Posn(300, 300),
        Color.RED, "R");
    assertEquals(rscaled, r);
    LiveShape rcolored = new Rectangle(100, 100, 0, new Posn(300, 300),
        Color.BLUE, "R");
    ((PrintRecolor) recolor).applyHelp();
    assertEquals(rcolored, r);
    LiveShape rturned = new Rectangle(100, 100, 60, new Posn(300, 300),
        Color.BLUE, "R");
    ((PrintTurn) turn).applyHelp();
    assertEquals(rturned, r);
    ((PrintIdle) idle).applyHelp();
    assertEquals(rturned, r);
  }

  @Test
  public void testStateString(){
    LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200),
        Color.RED, "R");
    Transform move = new PrintMove(new StringBuilder(), r, 10, 20,
        new Posn(300, 300));
    Transform scale = new PrintScale(new StringBuilder(), r, 10, 20,
        2, 1);
    Transform recolor = new PrintRecolor(new StringBuilder(), r, 40, 60,
        Color.BLUE);
    Transform turn = new PrintTurn(new StringBuilder(), r, 39, 40,
        60);
    Transform idle = new PrintIdle(new StringBuilder(), r, 39, 41);
    Transform create = new PrintCreate(new StringBuilder(), r, 10);

    assertEquals("Move R rectangle to {x=d, y=300} :\tqueued\n",move.stateString(9));
    assertEquals("Move R rectangle to {x=d, y=300} :\trunning[0/10 secs]\n",move.stateString(10));
    assertEquals("Move R rectangle to {x=d, y=300} :\tfinished\n",move.stateString(21));

    assertEquals("Scale R rectangle by h=1.0, w=2.0 :\tqueued\n",scale.stateString(9));
    assertEquals("Scale R rectangle by h=1.0, w=2.0 :\trunning[2/10 secs]\n",scale.stateString(12));
    assertEquals("Scale R rectangle by h=1.0, w=2.0 :\tfinished\n",scale.stateString(60));

    assertEquals("Recolor R rectangle to [r=0, g=0, b=255] :\tqueued\n",recolor.stateString(39));
    assertEquals("Recolor R rectangle to [r=0, g=0, b=255] :\trunning[19/20 secs]\n",recolor.stateString(59));
    assertEquals("Recolor R rectangle to [r=0, g=0, b=255] :\tfinished\n",recolor.stateString(60));

    assertEquals("Turn R rectangle to 60 degrees :\tqueued\n",turn.stateString(38));
    assertEquals("Turn R rectangle to 60 degrees :\trunning[0/1 secs]\n",turn.stateString(39));
    assertEquals("Turn R rectangle to 60 degrees :\tfinished\n",turn.stateString(40));

    assertEquals("Freeze R rectangle :\tqueued\n",idle.stateString(38));
    assertEquals("Freeze R rectangle :\trunning[1/2 secs]\n",idle.stateString(40));
    assertEquals("Freeze R rectangle :\tfinished\n",idle.stateString(42));

    assertEquals("Init R rectangle :\tqueued\n",create.stateString(9));
    assertEquals("Init R rectangle :\trunning[0/1 secs]\n",create.stateString(10));
    assertEquals("Init R rectangle :\tfinished\n",create.stateString(11));


  }
}