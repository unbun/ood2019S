import static org.junit.Assert.assertEquals;

import cs3500.animator.shapes.AShape;
import cs3500.animator.shapes.Oval;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.transforms.MoveTo;
import cs3500.animator.transforms.Recolor;
import cs3500.animator.transforms.Scale;
import cs3500.animator.transforms.Transform;
import cs3500.animator.util.Posn;
import java.awt.Color;
import org.junit.Test;


/**
 * Test myShape transforms.
 */
public class TransformTest {

  private AShape oval;
  private AShape rect;


  void initShapes() {
    oval = new Oval("O1", new Posn(1, 2), Color.BLUE, 0, 100, 5, 7);
    rect = new Rectangle("R1", new Posn(8, 9), Color.RED, 0, 100, 12, 13);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTimeInterval() {
    Transform t1 = new MoveTo("motion", 10, 9,
        new Posn(300, 300));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTimeInterval2() {
    Transform t1 = new Scale("scale", -1, 2,
        2, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTimeInterval3() {
    Transform t1 = new Recolor("recolor", 1, -2,
        new Color(1, 2, 3));
  }


  @Test
  public void applyFuncs() {
    initShapes();

    Transform t1 = new MoveTo("motion", 1, 10,
        new Posn(300, 300));
    Transform t2 = new Scale("resize", 11, 13,
        2, 3);
    Transform t3 = new Recolor("recolor", 12, 14,
        Color.MAGENTA);

    t1.apply(oval);
    assertEquals("oval:\"O1\" posn=(34.222222222222214,35.11111111111111); "
            + "size=(7.000000, 5.000000); color=java.awt.Color[r=0,g=0,b=255]",
        oval.getStateAt(2).toString());

    t2.apply(rect);
    assertEquals("rectangle:\"R1\" posn=(8.0,9.0); size=(16.000000, 15.000000); "
        + "color=java.awt.Color[r=255,g=0,b=0]", rect.getStateAt(13).toString());

    t3.apply(oval);
    assertEquals("oval:\"O1\" posn=(300.0,300.0); size=(7.000000, 5.000000); "
        + "color=java.awt.Color[r=255,g=0,b=255]", oval.getStateAt(15).toString());
  }


}