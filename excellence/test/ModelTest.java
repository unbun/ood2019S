import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.ShapeFXModel;
import cs3500.animator.shapes.AShape;
import cs3500.animator.shapes.IShape;
import cs3500.animator.shapes.Oval;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.transforms.MoveTo;
import cs3500.animator.transforms.Recolor;
import cs3500.animator.transforms.Resize;
import cs3500.animator.transforms.Transform;
import cs3500.animator.util.Posn;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the public methods offered by the PAnimatorModel interface.
 */
public class ModelTest {

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

    this.rect1 = new Rectangle("R1", new Posn(0, 0), new Color(255, 255, 255),
        0, 25, 15);
    this.rect2 = new Rectangle("R2", new Posn(0, 0), Color.BLUE,
        10, 10, 15);
    this.oval1 = new Oval("C", new Posn(50, 50), Color.GREEN,
        20, 10, 10);

    model.createShape(rect1);
    model.createShape(rect2);
    model.createShape(oval1);
  }

  @Test
  public void testCreateKeyFrame() {
    AnimationModel m = new ShapeFXModel();
    m.createShape(this.rect1);
    Transform move = new MoveTo("move key frame 5", 4, 5,
        new Posn(10, 20));
    m.createKeyFrame("R1", 5, 10, 20, 25, 15, 255, 255, 255);
    List<Transform> transforms = m.getTransforms();
    boolean keyframecreated = false;
    for (Transform t : transforms) {
      if (t.getName().equals(move.getName())) {
        keyframecreated = true;
      }
    }
    assertTrue(keyframecreated);
  }

  @Test
  public void testDeleteKeyFrame() {
    AnimationModel m = new ShapeFXModel();
    m.createShape(this.rect1);
    Transform recolor = new Recolor("R1", 6, 8, Color.BLUE);
    Transform move = new MoveTo("move key frame 5", 4, 5,
        new Posn(10, 20));
    m.createKeyFrame("R1", 5, 10, 20, 25, 15, 255, 255, 255);
    m.createTransform(recolor);
    m.removeKeyFrame("R1", 5);
    List<Transform> transforms = m.getTransforms();
    boolean deletedKeyFrameStillThere = false;
    for (Transform t : transforms) {
      if (t.equals(move)) {
        deletedKeyFrameStillThere = true;
      }
    }
    assertFalse(deletedKeyFrameStillThere);
  }

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

  @Test
  public void testRemoveShape() {
    assertEquals(0, this.model.getShapes().size());
    this.model.createShape(rect1);
    assertEquals(1, this.model.getShapes().size());
    this.model.removeShape(rect1);
    assertEquals(0, this.model.getShapes().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveShapeNotInAnimation() {
    this.model.removeShape(rect2);
    this.model.removeShape(rect1);
    this.model.removeShape(oval1);
    this.model.createShape(rect1);
    this.model.removeShape(oval1);
  }


  @Test
  public void testMoveTransform() {
    Transform moveRect1 = new MoveTo("R1", 10, 16,
        new Posn(10, 10));
    Transform moveOval1 = new MoveTo("MoveTo", 30, 50,
        new Posn(50, 50));

    model.createShape(rect1);
    model.setSpeed(2);
    assertEquals(
        "rectangle:\"R1\" posn=(0.0,0.0); size=(15.000000, 25.000000); "
            + "color=java.awt.Color[r=255,g=0,b=0]",
        model.getShapes().get(0).toString());
    model.createTransform(moveRect1);
    assertEquals("motion R1 \t5 0 0 25 15 255 0 0\t| 8 10 10 25 15 255 0 0\n",
        model.getShapes().get(0).getTransformations().get(0).toText(model));
    model.removeShape(rect1);
    model.createShape(oval1);
    moveOval1.apply(oval1);
    assertEquals("motion C \t15 50 50 20 20 0 255 0\t| 25 50 50 20 20 0 255 0\n",
        model.getShapes().get(0).getTransformations().get(0).toText(model));
  }

  @Test
  public void testColorApply() {
    Transform cc1 = new Recolor("cc", 10, 16, Color.BLUE);
    Transform cc2 = new Recolor("cc", 24, 30, Color.GREEN);
    model.createShape(rect1);
    model.setSpeed(2);
    assertEquals(50, model.getEndTime());
    cc1.apply(rect1);
    assertEquals(
        "rectangle:\"R1\" posn=(0.0,0.0); size=(15.000000, 25.000000);"
            + " color=java.awt.Color[r=255,g=0,b=0]",
        model.getShapes().get(0).toString());
    model.removeShape(rect1);

    model.createShape(oval1);
    cc2.apply(oval1);
    assertEquals(
        "oval:\"C\" posn=(50.0,50.0); size=(10.000000, 10.000000);"
            + " color=java.awt.Color[r=0,g=255,b=0]",
        model.getShapes().get(0).toString());

  }

  @Test
  public void testScaleApply() {
    Transform scale1 = new Resize("scale", 10, 15, 10, 10);
    model.createShape(rect1);
    model.setSpeed(2);
    assertEquals(
        "rectangle:\"R1\" posn=(0.0,0.0); size=(15.000000, 25.000000);"
            + " color=java.awt.Color[r=255,g=0,b=0]",
        model.getShapes().get(0).toString());
    scale1.apply(rect1);
    assertEquals(
        "rectangle:\"R1\" posn=(0.0,0.0); size=(15.000000, 25.000000);"
            + " color=java.awt.Color[r=255,g=0,b=0]",
        model.getShapes().get(0).toString());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShapeDimensions() {
    Transform scale = new Resize("scale", 10, 15, -25, -15);
    scale.apply(rect1);
    scale.toText(this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartTime() {
    Transform scale = new Resize("scale", 200, 205, -25,
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
    Transform s = new Resize("R1", 20, 30, 10, 10);
    this.model.createTransform(m);
    this.model.createTransform(s);
    ArrayList<Transform> transforms = new ArrayList<>();
    transforms.add(m);
    transforms.add(s);
    assertEquals(rect1.getTransformations(), transforms);
  }


  @Test
  public void testPosnTween() {
    IShape r = new Rectangle("R", new Posn(0, 0), Color.RED,
        0, 20, 30);
    Transform m = new MoveTo("R", 10, 12, new Posn(100, 100));
    model.createShape(r);
    model.createTransform(m);
    assertEquals(r.currState(1).getPosn().getX(), 0.0);
    assertEquals(r.currState(1).getPosn().getY(), 0.0);
    assertEquals(r.currState(30).getPosn().getX(), 100.0);
    assertEquals(r.currState(30).getPosn().getY(), 100.0);
    assertEquals(r.currState(10).getPosn().getX(), 0.0);
    assertEquals(r.currState(11).getPosn().getX(), 50.0);
    assertEquals(r.currState(11).getPosn().getY(), 50.0);
  }

  @Test
  public void testColorTween() {
    IShape r = new Rectangle("R", new Posn(0, 0), Color.RED,
        0, 20, 30);
    Transform m = new Recolor("R", 10, 12, Color.BLUE);
    model.createShape(r);
    model.createTransform(m);
    assertEquals(255, r.currState(1).getColor().getRed(), 0.00001);
    assertEquals(0, r.currState(1).getColor().getGreen(), 0.00001);
    assertEquals(0, r.currState(1).getColor().getBlue(), 0.00001);
    assertEquals(128, r.currState(11).getColor().getRed(), 0.00001);
    assertEquals(0, r.currState(11).getColor().getGreen(), 0.00001);
    assertEquals(127, r.currState(11).getColor().getBlue(), 0.00001);
  }

  @Test
  public void testResizeTween() {
    IShape r = new Rectangle("R", new Posn(0, 0), Color.RED,
        0, 20, 30);
    Transform m = new Resize("R", 10, 12, 10, 10);
    model.createShape(r);
    model.createTransform(m);

    assertEquals(20, r.currState(1).getWidth(), 0.0001);
    assertEquals(30, r.currState(1).getHeight(), 0.0001);
    assertEquals(25, r.currState(11).getWidth(), 0.0001);
    assertEquals(35, r.currState(11).getHeight(), 0.0001);
    assertEquals(30, r.currState(13).getWidth(), 0.0001);
    assertEquals(40, r.currState(13).getHeight(), 0.0001);
  }


  @Test
  public void testTransformcommandToSVG() {
    Transform s = new Resize("R", 10, 12, 10, 10);
    Transform m = new MoveTo("R", 10, 12, new Posn(100, 100));
    Transform c = new Recolor("R", 10, 12, Color.BLUE);

    AShape r = new Rectangle("R", new Posn(0, 0), Color.RED,
        0, 20, 30);
    model.createShape(r);

    model.createTransform(s);
    model.createTransform(m);
    model.createTransform(c);
    model.setSpeed(2);

    assertEquals("    <animate attributeType=\"xml \"begin=\"10000ms"
        + " dur=\"\"2000ms\" attributeName=\"width\"\n"
        + "from=\"20.0\" to=\"30.0\"fill=\"freeze\"\n"
        + "/>\n"
        + "    <animate attributeType=\"xml \"begin=\"10000ms dur=\"\"2000ms\" "
        + "attributeName=\"height\"\n"
        + "from=\"30.0\" to=\"40.0\"fill=\"freeze\"\n"
        + "/>\n", s.commandToSVG(r));
    assertEquals("    <animate attributeType=\"xml \"begin=\"10000ms dur=\"\"2000ms\""
        + " attributeName=\"x\"\n"
        + "from=\"0.0\" to=\"100.0\"fill=\"freeze\"\n"
        + "/>\n"
        + "    <animate attributeType=\"xml \"begin=\"10000ms dur=\"\"2000ms\" "
        + "attributeName=\"y\"\n"
        + "from=\"0.0\" to=\"100.0\"fill=\"freeze\"\n"
        + "/>\n", m.commandToSVG(r));
    assertEquals(
        "<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"2000ms\" "
            + "attributeName=\"fill\" from=\"RGB(255,0,0)\" to=\"RGB(0,0,255)\" "
            + "fill=\"freeze\" />\n",
        c.commandToSVG(r));
  }

}