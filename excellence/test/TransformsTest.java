import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animator.shapes.LiveShape;
import cs3500.animator.shapes.Oval;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.transforms.Transform;
import cs3500.animator.transforms.shapefx.Create;
import cs3500.animator.transforms.shapefx.Idle;
import cs3500.animator.transforms.shapefx.Move;
import cs3500.animator.transforms.shapefx.Recolor;
import cs3500.animator.transforms.shapefx.Scale;
import cs3500.animator.transforms.shapefx.Turn;
import cs3500.animator.util.Posn;
import java.awt.Color;
import org.junit.Test;


/**
 * Test myShape transforms.
 */
public class TransformsTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTimeInterval() {
    LiveShape r = new Rectangle(0,100, 50, 0, new Posn(200, 200),
        Color.RED, "R");
    Transform r1 = new Move(50, 10,
        new Posn(300, 300));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullShape() {
    Transform r1 = new Turn(10, 20, 15);
    ((Turn) r1).apply(null,0);
  }

  @Test
  public void transforming() {
    LiveShape r = new Rectangle(0, 100, 50, 0, new Posn(200, 200),
        Color.RED, "R");
    Transform<LiveShape> move = new Move(10, 20,
        new Posn(300, 300));
    Transform<LiveShape> scale = new Scale(10, 20,
        2, 1);
    Transform<LiveShape> recolor = new Recolor(40, 60,
        Color.BLUE);
    Transform<LiveShape> turn = new Turn( 50, 60,
        60);
    Transform<LiveShape> idle = new Idle(70, 80);
    Transform<LiveShape> create = new Create(10);

    create.apply(r,10);
    assertEquals("shape R rectangle", create.textualStr());

    LiveShape rmoved = new Rectangle(0,100, 50, 0, new Posn(300, 300),
        Color.RED, "R");
    assertEquals(rmoved, move.apply(r, 11));

    LiveShape rscaled = new Rectangle(0,100, 100, 0, new Posn(300, 300),
        Color.RED, "R");
    assertEquals(rscaled, scale.apply(r,10));

    LiveShape rcolored = new Rectangle(0, 100, 100, 0, new Posn(300, 300),
        Color.BLUE, "R");
    assertEquals(rcolored, recolor.apply(r,59));

    LiveShape rturned = new Rectangle(0,100, 100, 60, new Posn(300, 300),
        Color.BLUE, "R");
    assertEquals(rturned, turn.apply(r,55));

    assertEquals(rturned, idle.apply(r, 70));


  }

  @Test
  public void testTextual() {
    LiveShape r = new Rectangle(0, 100, 50, 0, new Posn(200, 200),
        Color.RED, "R");
    Transform<LiveShape> move = new Move(10, 20,
        new Posn(300, 300));
    Transform<LiveShape> scale = new Scale(10, 20,
        2, 1);
    Transform<LiveShape> recolor = new Recolor( 40, 60,
        Color.BLUE);
    Transform<LiveShape> turn = new Turn( 39, 40,
        60);
    Transform<LiveShape> idle = new Idle( 39, 41);
    Transform<LiveShape> create = new Create(10);

    assertEquals("motion queued\t| queued", move.textualStr());
    move.apply(r, 10);
    assertEquals("motion R:\t 10 200 200 0 50 100 255 0 0\t| 20 300 300 0 50 100 255 0 0",
        move.textualStr());

    assertEquals("scale queued\t| queued", scale.textualStr());
    scale.apply(r,11);
    assertEquals("scale R:\t 10 300 300 0 50 100 255 0 0\t| 20 300 300 0 100 100 255 0 0",
        scale.textualStr());

    assertEquals("color queued\t| queued", recolor.textualStr());
    recolor.apply(r, 59);
    assertEquals("color R:\t 40 300 300 0 100 100 255 0 0\t| 60 300 300 0 100 100 0 0 255",
        recolor.textualStr());

    turn.apply(r, 40);
    assertEquals("rotate queued\t| queued", turn.textualStr());
    turn.apply(r, 39);
    assertEquals("rotate R:\t 39 300 300 0 100 100 0 0 255\t| 40 300 300 60 100 100 0 0 255",
        turn.textualStr());

    assertEquals("freeze queued\t| queued", idle.textualStr());
    idle.apply(r, 40);
    assertEquals("freeze R:\t 39 300 300 60 100 100 0 0 255\t| 41 300 300 60 100 100 0 0 255",
        idle.textualStr());

    assertEquals("shape null", create.textualStr());
    create.apply(r,10);
    assertEquals("shape R rectangle", create.textualStr());
  }
}