import cs3500.animation.animations.printers.PrintIdle;
import cs3500.animation.animations.printers.PrintMove;
import cs3500.animation.animations.printers.PrintRecolor;
import cs3500.animation.animations.printers.PrintScale;
import cs3500.animation.animations.printers.PrintTurn;
import cs3500.animation.animations.printers.PrintCreate;
import cs3500.animation.shapes.Rectangle;
import cs3500.animation.utils.Posn;
import cs3500.animation.animations.ShapeAction;
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
public class ShapeActionTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTimeInterval() {
    LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200),
        Color.RED, "R");
    ShapeAction r1 = new PrintMove(new StringBuilder(), r, 50, 10,
        new Posn(300, 300));
  }

  @Test(expected = NullPointerException.class)
  public void testNullShape() {
    ShapeAction r1 = new PrintTurn(new StringBuilder(), null, 10, 20, 15);
  }

  @Test
  public void conflictTest() {
    LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200),
        Color.RED, "R");
    LiveShape c = new Oval(50, 50, 90, new Posn(100, 150),
        Color.GREEN, "C");
    ShapeAction r1 = new PrintMove(new StringBuilder(), r, 10, 20,
        new Posn(300, 300));
    ShapeAction differenttype = new PrintScale(new StringBuilder(), r, 10, 20,
        2, 1);
    ShapeAction differentshape = new PrintMove(new StringBuilder(), c, 10, 20,
        new Posn(300, 300));
    ShapeAction create = new PrintCreate(new StringBuilder(), r, 10);
    ShapeAction overlapconflict1 = new PrintMove(new StringBuilder(), r, 8, 12,
        new Posn(150, 200));
    ShapeAction overlapconflict2 = new PrintMove(new StringBuilder(), r, 15, 20,
        new Posn(200, 200));
    ShapeAction overlapconflict3 = new PrintMove(new StringBuilder(), r, 19, 25,
        new Posn(300, 300));
    assertTrue(r1.conflict(overlapconflict1));
    assertTrue(r1.conflict(overlapconflict2));
    assertTrue(r1.conflict(overlapconflict3));
    assertFalse(r1.conflict(differenttype));
    assertFalse(r1.conflict(create));
    assertFalse(r1.conflict(differentshape));
  }

  @Test
  public void applyActionTest() {
    LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200),
        Color.RED, "R");
    ShapeAction move = new PrintMove(new StringBuilder(), r, 10, 20,
        new Posn(300, 300));
    ShapeAction scale = new PrintScale(new StringBuilder(), r, 10, 20,
        2, 1);
    ShapeAction recolor = new PrintRecolor(new StringBuilder(), r, 40, 60,
        Color.BLUE);
    ShapeAction turn = new PrintTurn(new StringBuilder(), r, 50, 60,
        60);
    ShapeAction idle = new PrintIdle(new StringBuilder(), r, 70, 80);
    ShapeAction create = new PrintCreate(new StringBuilder(), r, 10);
    create.apply(10);
    assertTrue(r.isInitalized());

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
  public void alterShapeTest() {
    LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200),
        Color.RED, "R");
    ShapeAction move = new PrintMove(new StringBuilder(), r, 10, 20,
        new Posn(300, 300));
    ShapeAction scale = new PrintScale(new StringBuilder(), r, 10, 20,
        2, 1);
    ShapeAction recolor = new PrintRecolor(new StringBuilder(), r, 40, 60,
        Color.BLUE);
    ShapeAction turn = new PrintTurn(new StringBuilder(), r, 50, 60,
        60);
    ShapeAction idle = new PrintIdle(new StringBuilder(), r, 70, 80);
    ShapeAction create = new PrintCreate(new StringBuilder(), r, 10);
    ((PrintCreate) create).alterShape();
    assertTrue(r.isInitalized());
    ((PrintMove) move).alterShape();
    LiveShape rmoved = new Rectangle(100, 50, 0, new Posn(300, 300),
        Color.RED, "R");
    assertEquals(rmoved, r);
    ((PrintScale) scale).alterShape();
    LiveShape rscaled = new Rectangle(100, 100, 0, new Posn(300, 300),
        Color.RED, "R");
    assertEquals(rscaled, r);
    LiveShape rcolored = new Rectangle(100, 100, 0, new Posn(300, 300),
        Color.BLUE, "R");
    ((PrintRecolor) recolor).alterShape();
    assertEquals(rcolored, r);
    LiveShape rturned = new Rectangle(100, 100, 60, new Posn(300, 300),
        Color.BLUE, "R");
    ((PrintTurn) turn).alterShape();
    assertEquals(rturned, r);
    ((PrintIdle) idle).alterShape();
    assertEquals(rturned, r);
  }

  @Test
  public void printCreateTest() { //tests instant action
    StringBuilder output = new StringBuilder();
    LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200),
        Color.RED, "R");
    ShapeAction create = new PrintCreate(output, r, 10);
    create.apply(9);
    assertEquals("", output.toString());
    assertFalse(((PrintCreate) create).occured());
    create.apply(10);
    assertTrue(((PrintCreate) create).occured());
    assertTrue(r.isInitalized());
    assertTrue(create.finished(10));
    assertEquals("shape R rectangle\n", output.toString());
    create.apply(12);
    assertEquals("shape R rectangle\n", output.toString()); //nothing new happened
  }

}