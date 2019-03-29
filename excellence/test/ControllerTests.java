import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.ShapeFXController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.ShapeFXModel;
import cs3500.animator.shapes.IShape;
import cs3500.animator.shapes.Oval;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.util.Posn;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;

public class ControllerTests {


  private IShape rect1;
  private IShape rect2;
  private IShape oval1;
  AnimationModel model;
  AnimationController ctrl;

  @Before
  public void setup(){
    model = new ShapeFXModel();
    this.model = new ShapeFXModel();
    model.setSpeed(model.getTickRate());

    this.rect1 = new Rectangle("R1", new Posn(0, 0), Color.RED,
        0, 50, 25, 15);
    this.rect2 = new Rectangle("R2", new Posn(0, 0), Color.BLUE,
        10, 60, 10, 15);
    this.oval1 = new Oval("C", new Posn(50, 50), Color.GREEN,
        20, 100, 10, 10);
    assertEquals(0, this.model.getShapes().size());
    this.model.createShape(rect1);
    assertEquals(1, this.model.getShapes().size());
    this.model.createShape(rect2);
    assertEquals(2, this.model.getShapes().size());
    this.model.createShape(oval1);
    assertEquals(3, this.model.getShapes().size());

    try{
      String[] args = "-in test/data/smalldemo.txt -view edit".split(" ");
      ctrl = new ShapeFXController(model,args);
      assertEquals("test/data/smalldemo.txt", ctrl.getInputFilePath());
    } catch (IllegalArgumentException e){
      fail("controller parsing doesn't work");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void badParse1(){
    String[] args2 = "-in notafile -view text".split(" ");
    ctrl = new ShapeFXController(model, args2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badParse2(){
    String[] args2 = "-in notafile -view text -speed -1".split(" ");
    ctrl = new ShapeFXController(model, args2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badParse3(){
    String[] args2 = "-in notafile -view text -out notafile".split(" ");
    ctrl = new ShapeFXController(model, args2);
  }


  @Test(expected = IllegalArgumentException.class)
  public void badView(){
    String[] args2 = "-in test/data/smalldemo.txt -view notaview".split(" ");
    ctrl = new ShapeFXController(model, args2);
  }

  @Test
  public void testRun(){
    try {
      ctrl.runAnimation();
      assertEquals("test/data/smalldemo.txt", ctrl.getInputFilePath());
    } catch (IllegalStateException se){
      fail("ctlr can't run");
    }
  }

}
