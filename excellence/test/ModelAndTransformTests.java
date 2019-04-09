import static junit.framework.TestCase.assertEquals;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.ShapeFXModel;
import cs3500.animator.shapes.IShape;
import cs3500.animator.shapes.Oval;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.transforms.MoveTo;
import cs3500.animator.transforms.Recolor;
import cs3500.animator.transforms.Scale;
import cs3500.animator.transforms.Transform;
import cs3500.animator.util.Posn;
import java.awt.Color;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the public methods offered by the AnimatorModel interface.
 */
public class ModelAndTransformTests {

  private AnimationModel model;


  private IShape rect1;
  private IShape rect2;
  private IShape oval1;

  /**
   * Represents the test setup.
   */
  @Before
  public void setup() {
    this.model = new ShapeFXModel();
    model.setSpeed(model.getTickRate());

    this.rect1 = new Rectangle("R1", new Posn(0, 0), Color.RED,
        0, 50, 25, 15);
    this.rect2 = new Rectangle("R2", new Posn(0, 0), Color.BLUE,
        10, 60, 10, 15);
    this.oval1 = new Oval("C", new Posn(50, 50), Color.GREEN,
        20, 100, 10, 10);
  }

  // tests creating a shape
  @Test
  public void testCreateShape() {
    assertEquals(0, this.model.getShapes().size());
    this.model.createShape(rect1);
    assertEquals(1, this.model.getShapes().size());
    this.model.createShape(rect2);
    assertEquals(2, this.model.getShapes().size());
    this.model.createShape(oval1);
    assertEquals(3, this.model.getShapes().size());
  }

  // tests removing a shape
  @Test
  public void testRemoveShape() {
    assertEquals(0, this.model.getShapes().size());
    this.model.createShape(rect1);
    assertEquals(1, this.model.getShapes().size());
    this.model.removeShape(rect1);
    assertEquals(0, this.model.getShapes().size());
  }

  // tests removing a shape that is not in the animation throws an exception
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveShapeNotInAnimation() {
    this.model.removeShape(rect2);
    this.model.removeShape(rect1);
    this.model.removeShape(oval1);
    this.model.createShape(rect1);
    this.model.removeShape(oval1);
  }


  // tests the intent on moving a shape
  @Test
  public void testMoveTransform() {
    Transform moveRect1 = new MoveTo("R1", 10, 16, new Posn(10, 10));
    Transform moveOval1 = new MoveTo("MoveTo", 30, 50, new Posn(50, 50));

    model.createShape(rect1);
    model.setSpeed(2);
    assertEquals(
        "rectangle:\"R1\" posn=(0.0,0.0); size=(15.000000, 25.000000); color=java.awt.Color[r=255,g=0,b=0]",
        model.getShapes().get(0).toString());
    model.createTransform(moveRect1);
    assertEquals("motion R1 \t5 0 0 25 15 255 0 0\t| 8 10 10 25 15 255 0 0\n",
        model.getShapes().get(0).getOperations().get(0).getDescription(model));
    model.removeShape(rect1);
    model.createShape(oval1);
    moveOval1.apply(oval1);
    assertEquals("motion C \t15 50 50 20 20 0 255 0\t| 25 50 50 20 20 0 255 0\n",
        model.getShapes().get(0).getOperations().get(0).getDescription(model));
  }

  // tests the intent on changing a color
  @Test
  public void testColorApply() {
    Transform cc1 = new Recolor("cc", 10, 16, Color.BLUE);
    Transform cc2 = new Recolor("cc", 24, 30, Color.GREEN);

    model.createShape(rect1);
    model.setSpeed(2);
    assertEquals(50, model.getEndTime());
    cc1.apply(rect1);
    assertEquals(
        "rectangle:\"R1\" posn=(0.0,0.0); size=(15.000000, 25.000000); color=java.awt.Color[r=255,g=0,b=0]",
        model.getShapes().get(0).toString());
    model.removeShape(rect1);

    model.createShape(oval1);
    cc2.apply(oval1);
    assertEquals(
        "oval:\"C\" posn=(50.0,50.0); size=(10.000000, 10.000000); color=java.awt.Color[r=0,g=255,b=0]",
        model.getShapes().get(0).toString());

  }

  // tests the intent on scaling a shape
  @Test
  public void testScaleApply() {
    Transform scale1 = new Scale("scale", 10, 15, 10, 10);
    Transform scale2 = new Scale("scale", 25, 30, 1, -2);

    model.createShape(rect1);
    model.setSpeed(2);
    assertEquals(
        "rectangle:\"R1\" posn=(0.0,0.0); size=(15.000000, 25.000000); color=java.awt.Color[r=255,g=0,b=0]",
        model.getShapes().get(0).toString());
    scale1.apply(rect1);
    assertEquals(
        "rectangle:\"R1\" posn=(0.0,0.0); size=(15.000000, 25.000000); color=java.awt.Color[r=255,g=0,b=0]",
        model.getShapes().get(0).toString());
  }


  // when an operation has not been used, throw an exception
  @Test(expected = IllegalArgumentException.class)
  public void testNoShapesPerOperationError() {
    Transform move1 = new MoveTo("MoveTo", 10, 15, new Posn(10, 10));
    Transform cc1 = new Recolor("cc", 10, 15, Color.BLUE);
    Transform scale = new Scale("scale", 10, 15, 10, 10);

    move1.getDescription(this.model);
    cc1.getDescription(this.model);
    scale.getDescription(this.model);
  }

  // tests altering the shape to an illegal dimension throws an exception
  @Test(expected = IllegalArgumentException.class)
  public void testChangeShapeDimensionsToNonPositiveDimensionsError() {
    Transform scale = new Scale("scale", 10, 15, -25, -15);
    scale.apply(rect1);
    scale.getDescription(this.model);
  }

  // tests invalid start times throw an exception
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartTimesForOperations() {
    Transform scale = new Scale("scale", 200, 205, -25,
        -15);
    Transform move1 = new MoveTo("MoveTo", 15, 20, new Posn(10, 10));
    Transform cc1 = new Recolor("cc", 20, 15, Color.BLUE);

    scale.apply(rect1);
    move1.apply(oval1);
    cc1.apply(rect2);
  }

  @Test
  public void testGetTransforms() {
    this.model.createShape(rect1);
    Transform m = new MoveTo("R1", 10, 10, new Posn(100, 0));
    Transform s = new Scale("R1", 20, 30, 10, 10);
    this.model.createTransform(m);
    this.model.createTransform(s);
    ArrayList<Transform> ops = new ArrayList<>();
    ops.add(m);
    ops.add(s);
    assertEquals(rect1.getOperations(), ops);
  }


  @Test
  public void testPosnTween() {
    IShape r = new Rectangle("R", new Posn(0, 0), Color.RED,
        0, 100, 20, 30);
    Transform m = new MoveTo("R", 10, 12, new Posn(100, 100));
    model.createShape(r);
    model.createTransform(m);
    assertEquals(r.getStateAt(1).getPosn().getX(), 0.0);
    assertEquals(r.getStateAt(1).getPosn().getY(), 0.0);
    assertEquals(r.getStateAt(30).getPosn().getX(), 100.0);
    assertEquals(r.getStateAt(30).getPosn().getY(), 100.0);
    assertEquals(r.getStateAt(10).getPosn().getX(), 0.0);
    assertEquals(r.getStateAt(11).getPosn().getX(), 50.0);
    assertEquals(r.getStateAt(11).getPosn().getY(), 50.0);
  }

  @Test
  public void testColorTween() {
    IShape r = new Rectangle("R", new Posn(0, 0), Color.RED,
        0, 100, 20, 30);
    Transform m = new Recolor("R", 10, 12, Color.BLUE);
    model.createShape(r);
    model.createTransform(m);
    assertEquals(255, r.getStateAt(1).getColor().getRed(), 0.00001);
    assertEquals(0, r.getStateAt(1).getColor().getGreen(), 0.00001);
    assertEquals(0, r.getStateAt(1).getColor().getBlue(), 0.00001);
    assertEquals(128, r.getStateAt(11).getColor().getRed(), 0.00001);
    assertEquals(0, r.getStateAt(11).getColor().getGreen(), 0.00001);
    assertEquals(127, r.getStateAt(11).getColor().getBlue(), 0.00001);
  }

  @Test
  public void testScaleTween() {
    IShape r = new Rectangle("R", new Posn(0, 0), Color.RED,
        0, 100, 20, 30);
    Transform m = new Scale("R", 10, 12, 10, 10);
    model.createShape(r);
    model.createTransform(m);

    assertEquals(20, r.getStateAt(1).getWidth(), 0.0001);
    assertEquals(30, r.getStateAt(1).getHeight(), 0.0001);
    assertEquals(25, r.getStateAt(11).getWidth(), 0.0001);
    assertEquals(35, r.getStateAt(11).getHeight(), 0.0001);
    assertEquals(30, r.getStateAt(13).getWidth(), 0.0001);
    assertEquals(40, r.getStateAt(13).getHeight(), 0.0001);
  }


  @Test
  public void testOpsSVG() {
    Transform s = new Scale("R", 10, 12, 10, 10);
    Transform m = new MoveTo("R", 10, 12, new Posn(100, 100));
    Transform c = new Recolor("R", 10, 12, Color.BLUE);

    IShape r = new Rectangle("R", new Posn(0, 0), Color.RED,
        0, 100, 20, 30);
    model.createShape(r);

    model.createTransform(s);
    model.createTransform(m);
    model.createTransform(c);
    model.setSpeed(2);

    assertEquals("<animate attributeType=\"xml\" begin=\"1000ms\"" +
        " dur=\"200ms\" attributeName=\"width\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" /" +
        ">\n" +
        "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"200ms\" attributeName=" +
        "\"height\" from=\"30.0\" to=\"40.0\" fill=\"freeze\" />\n", s.printSVG());
    assertEquals("<animate attributeType=\"xml\" begin=\"1000ms\" dur=" +
        "\"200ms\" attributeName=\"x\" from=\"0\" to=\"100\" fill=\"freeze\" />\n" +
        "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"200ms\" attributeName=\"y\"" +
        " from=\"0\" to=\"100\" fill=\"freeze\" />\n", m.printSVG());
    assertEquals(
        "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"200ms\" attributeName=\"fill\" " +
            "from=\"RGB(65025,0,0)\" to=\"RGB(0,0,65025)\" fill=\"freeze\" />\n", c.printSVG());
  }

}