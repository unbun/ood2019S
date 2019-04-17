import static junit.framework.TestCase.assertEquals;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.ShapeFXModel;
import cs3500.animator.shapes.IShape;
import cs3500.animator.shapes.Oval;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.transforms.ATransform;
import cs3500.animator.transforms.MoveTo;
import cs3500.animator.transforms.Recolor;
import cs3500.animator.transforms.Resize;
import cs3500.animator.transforms.Transform;
import cs3500.animator.util.Posn;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the methods offered by each type of view in the animator.
 */
public class ViewTest {

  private AnimationModel model;
  private IView textview;
  private IView svgview;

  private IShape rect1;
  private IShape rect2;
  private IShape oval1;


  @Before
  public void setup() {
    this.model = new ShapeFXModel();
    model.setSpeed(2);
    this.textview = new TextView();
    this.svgview = new SVGView();

    this.rect1 = new Rectangle("R1", new Posn(0, 0), Color.RED,
        0, 25, 15);
    this.rect2 = new Rectangle("R2", new Posn(0, 0), Color.BLUE,
        10, 10, 15);
    this.oval1 = new Oval("C", new Posn(50, 50), Color.GREEN,
        0, 10, 10);
  }

  @Test
  public void testTextualView() {
    model.createShape(rect1);
    model.createShape(rect2);
    model.createShape(oval1);

    Transform moveToRect1 = new MoveTo("R1", 10, 15,
        new Posn(10, 10));
    ATransform recolorRect2 = new Recolor("R2", 20, 30, Color.RED);
    ATransform scaleOval1 = new Resize("C", 40, 45, 3, 0);

    model.createTransform(moveToRect1);
    model.createTransform(recolorRect2);
    model.createTransform(scaleOval1);

    assertEquals("shape R1 rectangle\n" +
            "motion R1 \t5 0 0 25 15 255 0 0\t| 7 10 10 25 15 255 0 0\n" +
            "\n" +
            "shape R2 rectangle\n" +
            "recolor R2 \t10 0 0 10 15 0 0 255\t| 15 0 0 10 15 255 0 0\n" +
            "\n" +
            "shape C oval\n" +
            "scale C \t20 50 50 20 20 0 255 0\t| 22 50 50 23 20 0 255 0\n\n",
        textview.updateView(this.model));
  }


  @Test
  public void testSVGView() {
    model.createShape(rect1);
    model.createShape(rect2);
    model.createShape(oval1);

    Transform moveToRect1 = new MoveTo("R1", 10, 15, new Posn(10,
        10));
    ATransform recolorRect2 = new Recolor("R2", 20, 30, Color.red);
    ATransform scaleOval1 = new Resize("C", 40, 45, 3, 0);

    model.createTransform(moveToRect1);
    model.createTransform(recolorRect2);
    model.createTransform(scaleOval1);

    assertEquals("<svg width=\"800\" height=\"800\" version=\"1.1\"\n" +
        "xmlns=\"http://www.w3.org/2000/svg\">\n" +
        "<rect id=\"R1\" x=\"0.0\" y=\"0.0\" width=\"25.0\" height=\"15.0\" "
        + "fill=\"RGB(65025,0,0)\" "
        + "visibility=\"visible\" >\n"
        + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"500ms\" attributeName=\"x\" "
        + "from=\"0\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"500ms\" attributeName=\"y\" "
        + "from=\"0\" to=\"10\" fill=\"freeze\" />\n"
        + "</rect>\n" + "\n" +
        "<rect id=\"R2\" x=\"0.0\" y=\"0.0\" width=\"10.0\" height=\"15.0\" "
        + "fill=\"RGB(0,0,65025)\" "
        + "visibility=\"visible\" >\n"
        + "<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"1000ms\" attributeName=\"fill\" "
        + "from=\"RGB(0,0,65025)\" to=\"RGB(65025,0,0)\" fill=\"freeze\" />\n"
        + "</rect>\n" + "\n" +
        "<ellipse id=\"C\" cx=\"50.0\" cy=\"50.0\" rx=\"10.0\" ry=\"10.0\" fill=\"RGB(0,65025,0)\""
        + " visibility=\"visible\" >\n"
        + "<animate attributeType=\"xml\" begin=\"4000ms\" dur=\"500ms\" attributeName=\"rx\" "
        + "from=\"10.0\" to=\"11.5\" fill=\"freeze\" />\n"
        + "</ellipse>\n" + "\n" + "</svg>", svgview.updateView(model));

  }
}