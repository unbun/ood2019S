import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
import org.junit.Test;

/**
 * Test shapefx.
 */
public class ShapeTests {

  private LiveShape oval;
  private LiveShape rect;
  private LiveShape tri;


  void initShapes() {
    oval = new Oval(1, 2, 3, new Posn(4, 5), Color.blue, "O1");
    rect = new Rectangle(1, 2, -3, new Posn(-4, 5), Color.red, "R1");
    tri = new Triangle(1, 2, 3, 4, -5, Color.green, "T1");
  }

  @Test
  public void testConstructsAndEquals() {
    initShapes();
    LiveShape ovalClone = new Oval(1, 2, 3, new Posn(4, 5), Color.blue, "O1");
    LiveShape rectClone = new  Rectangle(1, 2, -3, -4, 5, new Color(255,0,0), "R1");
    LiveShape triClone = new Triangle(1, 2, 3, new Posn(4, -5), Color.green, "T1");

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

    LiveShape evilOvalClone = new Rectangle(10, 10, 90, 0, 0, Color.blue, "O1");
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
  public void testMutate() {
    initShapes();
    assertEquals("Oval@[name=O1, height=1, width=2, heading=3, posn={4, 5}, color={0,0,255}]", oval.toString());
    assertEquals("Oval@[name=O1, height=6, width=7, heading=8, posn={9, 10}, color={11,12,13}]",
        oval.transform(6,7,8,new Posn(9,10), new Color(11,12,13)).toString());

    assertEquals("Rectangle@[name=R1, height=1, width=2, heading=-3, posn={-4, 5}, color={255,0,0}]", rect.toString());
    assertEquals("Rectangle@[name=R1, height=6, width=7, heading=8, posn={9, 10}, color={11,12,13}]",
        rect.transform(6,7,8,new Posn(9,10), new Color(11,12,13)).toString());

    assertEquals("Triangle@[name=T1, height=1, width=2, heading=3, posn={4, -5}, color={0,255,0}]", tri.toString());
    assertEquals("Triangle@[name=T1, height=6, width=7, heading=8, posn={9, 10}, color={11,12,13}]",
        tri.transform(6,7,8,new Posn(9,10), new Color(11,12,13)).toString());
  }

  @Test
  public void testMoveMutate() {
    initShapes();
    assertEquals("Oval@[name=O1, height=1, width=2, heading=3, posn={4, 5}, color={0,0,255}]", oval.toString());
    assertEquals("Oval@[name=O1, height=1, width=2, heading=3, posn={12, 34}, color={0,0,255}]", oval.moveTo(new Posn(12,34)).toString());

    assertEquals("Rectangle@[name=R1, height=1, width=2, heading=-3, posn={-4, 5}, color={255,0,0}]", rect.toString());
    assertEquals("Rectangle@[name=R1, height=1, width=2, heading=-3, posn={12, -34}, color={255,0,0}]", rect.moveTo(12,-34).toString());

    assertEquals("Triangle@[name=T1, height=1, width=2, heading=3, posn={4, -5}, color={0,255,0}]", tri.toString());
    assertEquals("Triangle@[name=T1, height=1, width=2, heading=3, posn={-12, 34}, color={0,255,0}]", tri.moveTo(-12,34).toString());
  }

  @Test
  public void testTurnMutate() {
    initShapes();
    assertEquals("Oval@[name=O1, height=1, width=2, heading=3, posn={4, 5}, color={0,0,255}]", oval.toString());
    assertEquals("Oval@[name=O1, height=1, width=2, heading=-123, posn={4, 5}, color={0,0,255}]", oval.turnTo(-123).toString());

    assertEquals("Rectangle@[name=R1, height=1, width=2, heading=-3, posn={-4, 5}, color={255,0,0}]", rect.toString());
    assertEquals("Rectangle@[name=R1, height=1, width=2, heading=361, posn={-4, 5}, color={255,0,0}]", rect.turnTo(361).toString());

    assertEquals("Triangle@[name=T1, height=1, width=2, heading=3, posn={4, -5}, color={0,255,0}]", tri.toString());
    assertEquals("Triangle@[name=T1, height=1, width=2, heading=280, posn={4, -5}, color={0,255,0}]", tri.turnTo(280).toString());
  }

  @Test
  public void testRecolorMutate() {
    initShapes();

    assertEquals("Oval@[name=O1, height=1, width=2, heading=3, posn={4, 5}, color={0,0,255}]", oval.toString());
    assertEquals("Oval@[name=O1, height=1, width=2, heading=3, posn={4, 5}, color={1,2,3}]", oval.recolor(1,2,3).toString());

    assertEquals("Rectangle@[name=R1, height=1, width=2, heading=-3, posn={-4, 5}, color={255,0,0}]", rect.toString());
    assertEquals("Rectangle@[name=R1, height=1, width=2, heading=-3, posn={-4, 5}, color={124,62,209}]",
        rect.recolor(new Color(124, 62, 209)).toString());

    assertEquals("Triangle@[name=T1, height=1, width=2, heading=3, posn={4, -5}, color={0,255,0}]", tri.toString());
    assertEquals("Triangle@[name=T1, height=1, width=2, heading=3, posn={4, -5}, color={123,45,5}]",
        tri.recolorRed(123).recolorGreen(45).recolorBlue(5).toString());
  }

  @Test
  public void testScaleMutate() {
    initShapes();
    assertEquals("Oval@[name=O1, height=1, width=2, heading=3, posn={4, 5}, color={0,0,255}]", oval.toString());
    assertEquals("Oval@[name=O1, height=50, width=5, heading=3, posn={4, 5}, color={0,0,255}]", oval.resize(50, 5).toString());

    assertEquals("Rectangle@[name=R1, height=1, width=2, heading=-3, posn={-4, 5}, color={255,0,0}]", rect.toString());
    assertEquals("Rectangle@[name=R1, height=0, width=51, heading=-3, posn={-4, 5}, color={255,0,0}]", rect.resize(0, 51).toString());

    assertEquals("Triangle@[name=T1, height=1, width=2, heading=3, posn={4, -5}, color={0,255,0}]", tri.toString());
    assertEquals("Triangle@[name=T1, height=37, width=0, heading=3, posn={4, -5}, color={0,255,0}]", tri.resizeH(37).resizeW(0).toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void badScale1(){
    initShapes();
    oval.resize(-1,2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badScale2(){
    initShapes();
    rect.resize(14,-23);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badScale3(){
    initShapes();
    tri.resize(-12,-9);
  }
}
