import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.animator.shapes.LiveShape;
import cs3500.animator.shapes.Oval;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.shapes.Triangle;
import cs3500.animator.util.Posn;
import java.awt.Color;
import org.junit.Test;

/**
 * Test shapefx.
 */
public class ShapeTests {

  private LiveShape oval;
  private LiveShape rect;
  private LiveShape tri;


  void initShapes() {
    oval = new Oval(0,10, 10, 90, new Posn(0, 0), Color.blue, "O1");
    rect = new Rectangle(0, 100, 6, -90, new Posn(-4, 5), Color.red, "R1");
    tri = new Triangle(0, 43, 51, 180, 12, -60, Color.green, "T1");
  }

  @Test
  public void testConstructsAndEquals() {
    initShapes();
    LiveShape ovalClone = new Oval(0, 10, 10, 90, 0, 0, Color.blue, "O1");
    LiveShape rectClone = new Rectangle(0, 100, 6, -90, -4, 5, new Color(255, 0, 0), "R1");
    LiveShape triClone = new Triangle(0, 43, 51, 180, new Posn(12, -60), Color.green, "T1");

    assertEquals(oval,oval);
    assertEquals(rect,rect);
    assertEquals(tri,tri);

    assertEquals(0, oval.compareTo(ovalClone));
    assertEquals(-3, oval.compareTo(rect));
    assertEquals(3, rect.compareTo(oval));
    assertEquals(5, tri.compareTo(oval));


    assertEquals(oval, ovalClone);
    assertEquals(ovalClone, oval);
    assertEquals(rect, rectClone);
    assertEquals(tri, triClone);

    LiveShape evilOvalClone = new Rectangle(0, 10, 10, 90, 0, 0, Color.blue, "O1");
    assertNotEquals(oval, evilOvalClone);
    assertNotEquals(evilOvalClone, oval);
  }

  @Test
  public void testShapeID() {
    initShapes();
    assertEquals("O1 oval", oval.getID());
    assertEquals("R1 rectangle", rect.getID());
    assertEquals("T1 triangle", tri.getID());
  }

  @Test
  public void testMoveMutate() {
    initShapes();
    assertEquals("Oval@[name=O1, width=10, height=10, heading=90, posn={0, 0}, color={0,0,255}]", oval.toString());
    oval.moveTo(new Posn(12, 34));
    assertEquals("Oval@[name=O1, width=10, height=10, heading=90, posn={12, 34}, color={0,0,255}]", oval.toString());

    assertEquals("Rectangle@[name=R1, width=6, height=100, heading=-90, posn={-4, 5}, color={255,0,0}]", rect.toString());
    rect.moveTo(new Posn(-666, -666));
    assertEquals("Rectangle@[name=R1, width=6, height=100, heading=-90, posn={-666, -666}, color={255,0,0}]", rect.toString());

    assertEquals("Triangle@[name=T1, width=51, height=43, heading=180, posn={12, -60}, color={0,255,0}]", tri.toString());
    tri.moveTo(new Posn(0, 0));
    assertEquals("Triangle@[name=T1, width=51, height=43, heading=180, posn={0, 0}, color={0,255,0}]", tri.toString());
  }

  @Test
  public void testTurnMutate() {
    initShapes();
    assertEquals("Oval@[name=O1, width=10, height=10, heading=90, posn={0, 0}, color={0,0,255}]", oval.toString());
    oval.turnTo(123);
    assertEquals("Oval@[name=O1, width=10, height=10, heading=123, posn={0, 0}, color={0,0,255}]", oval.toString());

    assertEquals("Rectangle@[name=R1, width=6, height=100, heading=-90, posn={-4, 5}, color={255,0,0}]", rect.toString());
    rect.turnTo(-361);
    assertEquals("Rectangle@[name=R1, width=6, height=100, heading=-361, posn={-4, 5}, color={255,0,0}]", rect.toString());

    assertEquals("Triangle@[name=T1, width=51, height=43, heading=180, posn={12, -60}, color={0,255,0}]", tri.toString());
    tri.turnTo(280);
    assertEquals("Triangle@[name=T1, width=51, height=43, heading=280, posn={12, -60}, color={0,255,0}]", tri.toString());
  }

  @Test
  public void testRecolorMutate() {
    initShapes();
    assertEquals("Oval@[name=O1, width=10, height=10, heading=90, posn={0, 0}, color={0,0,255}]", oval.toString());
    oval.recolor(new Color(1, 2, 3));
    assertEquals("Oval@[name=O1, width=10, height=10, heading=90, posn={0, 0}, color={1,2,3}]", oval.toString());

    assertEquals("Rectangle@[name=R1, width=6, height=100, heading=-90, posn={-4, 5}, color={255,0,0}]", rect.toString());
    rect.recolor(new Color(81, 238, 113));
    assertEquals("Rectangle@[name=R1, width=6, height=100, heading=-90, posn={-4, 5}, color={81,238,113}]", rect.toString());

    assertEquals("Triangle@[name=T1, width=51, height=43, heading=180, posn={12, -60}, color={0,255,0}]", tri.toString());
    tri.recolor(Color.PINK);
    assertEquals("Triangle@[name=T1, width=51, height=43, heading=180, posn={12, -60}, color={255,175,175}]", tri.toString());
  }

  @Test
  public void testScaleMutate() {
    initShapes();
    assertEquals("Oval@[name=O1, width=10, height=10, heading=90, posn={0, 0}, color={0,0,255}]", oval.toString());
    oval.scale(5, 1);
    assertEquals("Oval@[name=O1, width=50, height=10, heading=90, posn={0, 0}, color={0,0,255}]", oval.toString());

    assertEquals("Rectangle@[name=R1, width=6, height=100, heading=-90, posn={-4, 5}, color={255,0,0}]", rect.toString());
    rect.scale(0, 0.5);
    assertEquals("Rectangle@[name=R1, width=0, height=50, heading=-90, posn={-4, 5}, color={255,0,0}]", rect.toString());

    assertEquals("Triangle@[name=T1, width=51, height=43, heading=180, posn={12, -60}, color={0,255,0}]", tri.toString());
    tri.scale(0.37, 0);
    assertEquals("Triangle@[name=T1, width=18, height=0, heading=180, posn={12, -60}, color={0,255,0}]", tri.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void badScale1(){
    initShapes();
    oval.scale(-1,2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badScale2(){
    initShapes();
    rect.scale(14,-23);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badScale3(){
    initShapes();
    rect.scale(-12,9);
  }



}
