import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animation.shapes.LiveShape;
import cs3500.animation.shapes.Oval;
import cs3500.animation.shapes.Rectangle;
import cs3500.animation.transforms.Transform;
import cs3500.animation.transforms.shapefx.Create;
import cs3500.animation.transforms.shapefx.Idle;
import cs3500.animation.transforms.shapefx.Move;
import cs3500.animation.transforms.shapefx.Recolor;
import cs3500.animation.transforms.shapefx.Scale;
import cs3500.animation.transforms.shapefx.Turn;
import cs3500.animation.utils.Posn;
import java.awt.Color;
import org.junit.Test;


/**
 * Test myShape transforms.
 */
public class PrintersTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTimeInterval() {
    LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200),
        Color.RED, "R");
    Transform r1 = new Move(r, 50, 10,
        new Posn(300, 300));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullShape() {
    Transform r1 = new Turn(null, 10, 20, 15);
  }

  @Test
  public void conflictTest() {
    LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200),
        Color.RED, "R");
    LiveShape c = new Oval(50, 50, 90, new Posn(100, 150),
        Color.GREEN, "C");
    Transform r1 = new Move(r, 10, 20,
        new Posn(300, 300));
    Transform differenttype = new Scale(r, 10, 20,
        2, 1);
    Transform differentshape = new Move(c, 10, 20,
        new Posn(300, 300));
    Transform create = new Create(r, 10);
    Transform overlapconflict1 = new Move(r, 8, 12,
        new Posn(150, 200));
    Transform overlapconflict2 = new Move(r, 15, 20,
        new Posn(200, 200));
    Transform overlapconflict3 = new Move(r, 19, 25,
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
    Transform move = new Move(r, 10, 20,
        new Posn(300, 300));
    Transform scale = new Scale(r, 10, 20,
        2, 1);
    Transform recolor = new Recolor(r, 40, 60,
        Color.BLUE);
    Transform turn = new Turn(r, 50, 60,
        60);
    Transform idle = new Idle(r, 70, 80);
    Transform create = new Create(r, 10);
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
  public void testStateString() {
    LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200),
        Color.RED, "R");
    Transform move = new Move(r, 10, 20,
        new Posn(300, 300));
    Transform scale = new Scale(r, 10, 20,
        2, 1);
    Transform recolor = new Recolor(r, 40, 60,
        Color.BLUE);
    Transform turn = new Turn(r, 39, 40,
        60);
    Transform idle = new Idle(r, 39, 41);
    Transform create = new Create(r, 10);

    assertEquals("motion R:\t 10 null\t| 20 null", move.textualView());
    move.apply(10);
    assertEquals("motion R:\t 10 200 200 0 50 100 255 0 0\t| 20 300 300 0 50 100 255 0 0",
        move.textualView());

    assertEquals("scale R:\t 10 null\t| 20 null", scale.textualView());
    scale.apply(11);
    assertEquals("scale R:\t 10 300 300 0 50 100 255 0 0\t| 20 300 300 0 100 100 255 0 0",
        scale.textualView());

    assertEquals("color R:\t 40 null\t| 60 null", recolor.textualView());
    recolor.apply(59);
    assertEquals("color R:\t 40 300 300 0 100 100 255 0 0\t| 60 300 300 0 100 100 0 0 255",
        recolor.textualView());

    turn.apply(40);
    assertEquals("rotate R:\t 39 null\t| 40 null", turn.textualView());
    turn.apply(39);
    assertEquals("rotate R:\t 39 300 300 0 100 100 0 0 255\t| 40 300 300 60 100 100 0 0 255",
        turn.textualView());

    assertEquals("still R:\t 39 null\t| 41 null", idle.textualView());
    idle.apply(40);
    assertEquals("still R:\t 39 300 300 60 100 100 0 0 255\t| 41 300 300 60 100 100 0 0 255",
        idle.textualView());

    assertEquals("shape R rectangle", create.textualView());
    create.apply(10);
    assertEquals("shape R rectangle", create.textualView());
  }
}