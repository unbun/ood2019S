import static org.junit.Assert.assertEquals;

import cs3500.animator.shapes.LiveShape;
import cs3500.animator.shapes.Oval;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.shapes.Triangle;
import cs3500.animator.transforms.Freeze;
import cs3500.animator.transforms.MoveTo;
import cs3500.animator.transforms.Recolor;
import cs3500.animator.transforms.Resize;
import cs3500.animator.transforms.Transform;
import cs3500.animator.transforms.TurnTo;
import cs3500.animator.util.Posn;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;


/**
 * Test myShape transforms.
 */
public class TransformTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTimeInterval() {
    LiveShape o = new Oval(100, 50, 0, new Posn(200, 200),
        Color.RED, "R");
    Transform o1 = new MoveTo(50, 10,
        new Posn(300, 300));
  }

  @Test
  public void transformFuncs() {
    LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200),
        Color.RED, "R");

    Transform move = new MoveTo(10, 20,
        new Posn(300, 300));
    Transform resize = new Resize(10, 20,
        50, 51);
    Transform recolor = new Recolor(40, 60,
        Color.BLUE);
    Transform turn = new TurnTo(50, 60,
        60);
    Transform freeze = new Freeze(70, 80);

    LiveShape rmoved = new Rectangle(100, 50, 0, new Posn(300, 300),
        Color.RED, "R");

    assertEquals(r, move.func(9).apply(r));
    assertEquals(rmoved, move.func(11).apply(r));
    assertEquals(r, move.func(20).apply(r));

    LiveShape rresized = new Rectangle(50, 51, 0, new Posn(200, 200),
        Color.RED, "R");

    assertEquals(r, resize.func(9).apply(r));
    assertEquals(rresized, resize.func(10).apply(r));
    assertEquals(r, resize.func(21).apply(r));

    LiveShape rcolored = new Rectangle(100, 50, 0, new Posn(200, 200),
        Color.BLUE, "R");

    assertEquals(r, recolor.func(0).apply(r));
    assertEquals(rcolored, recolor.func(40).apply(r));
    assertEquals(r, recolor.func(60).apply(r));

    LiveShape rturned = new Rectangle(100, 50, 60, new Posn(200, 200),
        Color.RED, "R");

    assertEquals(r, turn.func(45).apply(r));
    assertEquals(rturned, turn.func(55).apply(r));
    assertEquals(r, turn.func(100).apply(r));

    assertEquals(r, freeze.func(56).apply(r));
    assertEquals(r, freeze.func(70).apply(r));
    assertEquals(r, freeze.func(80).apply(r));
  }


  @Test
  public void iterateTest() {
    LiveShape t = new Triangle(100, 50, 0, new Posn(200, 200),
        Color.RED, "T");

    LiveShape t2 = new Triangle(50, 51, 60, 300, 300, Color.BLUE, "T");

    Transform move = new MoveTo(10, 20,
        new Posn(300, 300));
    Transform resize = new Resize(10, 20,
        50, 51);
    Transform recolor = new Recolor(40, 60,
        Color.BLUE);
    Transform turn = new TurnTo(50, 60,
        60);
    Transform freeze = new Freeze(70, 80);

    List<Transform> list = new ArrayList<>(Arrays.asList(move, resize, recolor, turn, freeze));

    LiveShape test = t.copy();

    for (Transform trnsfm : list) {
      test = trnsfm.func(trnsfm.sortRank()).apply(test);
    }

    assertEquals(t2, test);
  }

}