import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.animation.shapes.AbstractShape;
import cs3500.animation.shapes.LiveShape;
import cs3500.animation.shapes.Oval;
import cs3500.animation.shapes.Rectangle;
import cs3500.animation.shapes.Triangle;
import cs3500.animation.utils.Posn;
import java.awt.Color;
import org.junit.Test;

/**
 * Test shapes.
 */
public class ShapeTests {

  private LiveShape oval;
  private LiveShape rect;
  private LiveShape tri;


  void initShapes() {
    oval = new Oval(10, 10, 90, new Posn(0, 0), Color.blue, "O1");
    rect = new Rectangle(100, 6, -90, new Posn(-4, 5), Color.red, "R1");
    tri = new Triangle(43, 51, 180, 12, -60, Color.green, "T1");
  }

  @Test
  public void testConstructsAndEquals() {
    initShapes();
    AbstractShape ovalClone = new Oval(10, 10, 90, 0, 0, Color.blue, "O1");
    AbstractShape rectClone = new Rectangle(100, 6, -90, -4, 5, new Color(255, 0, 0), "R1");
    AbstractShape triClone = new Triangle(43, 51, 180, new Posn(12, -60), Color.green, "T1");

    assertEquals(oval, ovalClone);
    assertEquals(ovalClone, oval);
    assertEquals(rect, rectClone);
    assertEquals(tri, triClone);

    AbstractShape evilOvalClone = new Rectangle(10, 10, 90, 0, 0, Color.blue, "O1");
    assertNotEquals(oval, evilOvalClone);
    assertNotEquals(evilOvalClone, oval);
  }

  @Test
  public void testShapeToString() {
    initShapes();
    assertEquals("O1 oval", oval.shapeToString());
    assertEquals("R1 rectangle", rect.shapeToString());
    assertEquals("T1 triangle", tri.shapeToString());
  }

  @Test
  public void testDescrip() {
    initShapes();
    assertEquals("0 0 90 10 10 0 0 255", oval.getDescription());
    assertEquals("-4 5 -90 6 100 255 0 0", rect.getDescription());
    assertEquals("12 -60 180 51 43 0 255 0", tri.getDescription());
  }

  @Test
  public void testMove() {
    initShapes();
    assertEquals("0 0 90 10 10 0 0 255", oval.getDescription());
    oval.moveTo(new Posn(12, 34));
    assertEquals("12 34 90 10 10 0 0 255", oval.getDescription());

    assertEquals("-4 5 -90 6 100 255 0 0", rect.getDescription());
    rect.moveTo(new Posn(-666, -666));
    assertEquals("-666 -666 -90 6 100 255 0 0", rect.getDescription());

    assertEquals("12 -60 180 51 43 0 255 0", tri.getDescription());
    tri.moveTo(new Posn(0, 0));
    assertEquals("0 0 180 51 43 0 255 0", tri.getDescription());
  }

  @Test
  public void testTurn() {
    initShapes();
    assertEquals("0 0 90 10 10 0 0 255", oval.getDescription());
    oval.turnTo(123);
    assertEquals("0 0 123 10 10 0 0 255", oval.getDescription());

    assertEquals("-4 5 -90 6 100 255 0 0", rect.getDescription());
    rect.turnTo(-361);
    assertEquals("-4 5 -361 6 100 255 0 0", rect.getDescription());

    assertEquals("12 -60 180 51 43 0 255 0", tri.getDescription());
    tri.turnTo(280);
    assertEquals("12 -60 280 51 43 0 255 0", tri.getDescription());
  }

  @Test
  public void testRecolor() {
    initShapes();
    assertEquals("0 0 90 10 10 0 0 255", oval.getDescription());
    oval.recolor(new Color(1, 2, 3));
    assertEquals("0 0 90 10 10 1 2 3", oval.getDescription());

    assertEquals("-4 5 -90 6 100 255 0 0", rect.getDescription());
    rect.recolor(new Color(81, 238, 113));
    assertEquals("-4 5 -90 6 100 81 238 113", rect.getDescription());

    assertEquals("12 -60 180 51 43 0 255 0", tri.getDescription());
    tri.recolor(Color.PINK);
    assertEquals("12 -60 180 51 43 255 175 175", tri.getDescription());
  }

  @Test
  public void testScale() {
    initShapes();
    assertEquals("0 0 90 10 10 0 0 255", oval.getDescription());
    oval.scale(5, 1);
    assertEquals("0 0 90 50 10 0 0 255", oval.getDescription());

    assertEquals("-4 5 -90 6 100 255 0 0", rect.getDescription());
    rect.scale(2, 0.5);
    assertEquals("-4 5 -90 12 50 255 0 0", rect.getDescription());

    assertEquals("12 -60 180 51 43 0 255 0", tri.getDescription());
    tri.scale(1, 0.5);
    assertEquals("12 -60 180 51 21 0 255 0", tri.getDescription());
  }

}
