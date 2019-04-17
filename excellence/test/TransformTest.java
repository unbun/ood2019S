import static junit.framework.TestCase.assertTrue;

import cs3500.animator.shapes.AShape;
import cs3500.animator.shapes.Oval;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.transforms.MoveTo;
import cs3500.animator.transforms.Recolor;
import cs3500.animator.transforms.Resize;
import cs3500.animator.transforms.Transform;
import cs3500.animator.util.Posn;
import java.awt.Color;
import org.junit.Test;


/**
 * Test Shape transforms.
 */
public class TransformTest {

  private AShape oval;
  private AShape rect;


  void initShapes() {
    oval = new Oval("O1", new Posn(1, 2), Color.BLUE, 0, 5, 7);
    rect = new Rectangle("R1", new Posn(8, 9), Color.RED, 0, 12, 13);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTimeInterval() {
    Transform t1 = new MoveTo("motion", 10, 9,
        new Posn(300, 300));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTimeInterval2() {
    Transform t1 = new Resize("scale", -1, 2,
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
    Transform t2 = new Resize("resize", 11, 13,
        2, 3);
    Transform t3 = new Recolor("recolor", 12, 14,
        Color.MAGENTA);
    t1.apply(oval);
    t2.apply(rect);
    t3.apply(oval);
    boolean t1found = false;
    for (Transform t : oval.getTransformations()) {
      if (t.equals(t1)) {
        t1found = true;
      }
    }
    assertTrue(t1found);
    boolean t2found = false;
    for (Transform t : rect.getTransformations()) {
      if (t.equals(t2)) {
        t2found = true;
      }
    }
    assertTrue(t2found);
    boolean t3found = false;
    for (Transform t : oval.getTransformations()) {
      if (t.equals(t3)) {
        t3found = true;
      }
    }
    assertTrue(t3found);
  }

}