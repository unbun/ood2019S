import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.ShapeFXController;
import cs3500.animator.controller.handlers.AddShapeHandler;
import cs3500.animator.controller.handlers.DeleteShapeHandler;
import cs3500.animator.controller.handlers.LoopHandler;
import cs3500.animator.controller.handlers.PlayButtonHandler;
import cs3500.animator.controller.handlers.RestartButtonHandler;
import cs3500.animator.controller.handlers.SlowDownHandler;
import cs3500.animator.controller.handlers.SpeedUpHandler;
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
import cs3500.animator.view.AnimationPanel;
import cs3500.animator.view.ControllableView;
import cs3500.animator.view.VisualView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests for the Controller and the Handlers it employs.
 */
public class ControllerandHandlerTest {

  AnimationController ctrl;
  private AnimationModel model;
  private ControllableView controlView;
  private VisualView visualView;
  private ShapeFXController visualShapeFXController;
  private ShapeFXController editShapeFXController;

  @Before
  public void setup() {
    this.model = new ShapeFXModel();
    model.setSpeed(2);
    this.controlView = new ControllableView();
    this.visualView = new VisualView();
    String[] visualArgs = new String[4];
    visualArgs[0] = "-view";
    visualArgs[1] = "visual";
    visualArgs[2] = "-in";
    visualArgs[3] = "hanoi.txt";

    String[] editArgs = new String[4];
    editArgs[0] = "-view";
    editArgs[1] = "edit";
    editArgs[2] = "-in";
    editArgs[3] = "buildings.txt";

    this.visualShapeFXController = new ShapeFXController(model, visualArgs);
    visualShapeFXController.setView(visualView);

    this.editShapeFXController = new ShapeFXController(model, editArgs);
    editShapeFXController.setView(controlView);

    IShape rect1;
    IShape rect2;
    IShape oval1;

    rect1 = new Rectangle("R1", new Posn(0, 0), Color.RED,
        0, 25, 15);
    rect2 = new Rectangle("R2", new Posn(0, 0), Color.BLUE,
        10, 10, 15);
    oval1 = new Oval("C", new Posn(50, 50), Color.GREEN,
        0, 10, 10);

    Transform moveToRect1 = new MoveTo("R1", 10, 15,
        new Posn(10, 10));
    ATransform recolorRect2 = new Recolor("R2", 20, 30, Color.red);
    ATransform scaleOval1 = new Resize("C", 40, 45, 3, 0);

    model.createTransform(moveToRect1);
    model.createTransform(recolorRect2);
    model.createTransform(scaleOval1);

    assertEquals(0, this.model.getShapes().size());
    this.model.createShape(rect1);
    assertEquals(1, this.model.getShapes().size());
    this.model.createShape(rect2);
    assertEquals(2, this.model.getShapes().size());
    this.model.createShape(oval1);
    assertEquals(3, this.model.getShapes().size());

    try {
      String[] args = "-in test/data/smalldemo.txt -view edit".split(" ");
      ctrl = new ShapeFXController(model, args);
      assertEquals("test/data/smalldemo.txt", ctrl.getInputFile());
    } catch (IllegalArgumentException e) {
      fail("controller parsing doesn't work");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void badParse1() {
    String[] args2 = "-in notafile -view text".split(" ");
    ctrl = new ShapeFXController(model, args2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badParse2() {
    String[] args2 = "-in notafile -view text -speed -1".split(" ");
    ctrl = new ShapeFXController(model, args2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badParse3() {
    String[] args2 = "-in notafile -view text -out notafile".split(" ");
    ctrl = new ShapeFXController(model, args2);
  }


  @Test(expected = IllegalArgumentException.class)
  public void badView() {
    String[] args2 = "-in test/data/smalldemo.txt -view notaview".split(" ");
    ctrl = new ShapeFXController(model, args2);
  }


  @Test
  public void testRunAnimation() {
    visualShapeFXController.run();
    assertTrue(visualView.isVisible());
    editShapeFXController.run();
    assertTrue(controlView.isVisible());
    try {
      ctrl.run();
      assertEquals("test/data/smalldemo.txt", ctrl.getInputFile());
    } catch (IllegalStateException se) {
      fail("ctlr can't run");
    }
  }

  @Test
  public void testPlayButtonHandler() {
    ActionListener listener = new PlayButtonHandler(this.controlView);

    ActionEvent event = new ActionEvent(controlView, ActionEvent.ACTION_FIRST,
        "playbutton");
    controlView.getPlayButton().addActionListener(listener);

    assertFalse(((AnimationPanel) controlView.getAnimationPanel()).isPlaying());
    listener.actionPerformed(event);
    assertTrue(((AnimationPanel) controlView.getAnimationPanel()).isPlaying());
    listener.actionPerformed(event);
    assertFalse(((AnimationPanel) controlView.getAnimationPanel()).isPlaying());
  }


  @Test
  public void testDeleteShapeHandler() {
    ActionListener listener = new DeleteShapeHandler(this.controlView, model);
    controlView.setEditAddColor(new JTextField("100 200 50"));
    controlView.setEditAddPosnSize(new JTextField("50 50 100 200"));
    controlView.setEditAddShapeName(new JTextField("R1"));
    controlView.seteditAddTime0(new JTextField("15"));
    controlView.setEditAddShape(new JTextField("rectangle"));
    ActionEvent event = new ActionEvent(controlView, ActionEvent.ACTION_FIRST,
        "delete shape button");
    controlView.getEditDeleteShapeBtn().addActionListener(listener);
    listener.actionPerformed(event);
    boolean foundR1 = false;
    for (IShape s : model.getShapes()) {
      if (s.getName().equals("R1")) {
        foundR1 = true;
      }
    }
    assertFalse(foundR1);
  }

  @Test
  public void testAddShapeHandler() {
    ActionListener listener = new AddShapeHandler(this.controlView);
    controlView.setModel(model);
    controlView.setEditAddColor(new JTextField("100 200 50"));
    controlView.setEditAddPosnSize(new JTextField("50 50 100 200"));
    controlView.setEditAddShapeName(new JTextField("rect"));
    controlView.seteditAddTime0(new JTextField("15"));
    controlView.setEditAddShape(new JTextField("rectangle"));
    ActionEvent event = new ActionEvent(controlView, ActionEvent.ACTION_FIRST,
        "add shape button");
    controlView.getEditAddShapeBtn().addActionListener(listener);
    listener.actionPerformed(event);
    boolean foundrect = false;
    for (IShape s : model.getShapes()) {
      if (s.getName().equals("rect")) {
        foundrect = true;
      }
    }
    assertTrue(foundrect);
  }


  @Test
  public void testRestartButtonHandler() {
    ActionListener listener = new RestartButtonHandler(this.controlView);
    ActionEvent event = new ActionEvent(controlView, ActionEvent.ACTION_FIRST, "restart" +
        " button");
    controlView.getRestartButton().addActionListener(listener);

    listener.actionPerformed(event);
    assertEquals(((AnimationPanel) controlView.getAnimationPanel()).getTime(), 0);
  }


  @Test
  public void testLoopingHandler() {
    ActionListener listener = new LoopHandler(this.controlView);
    ActionEvent event = new ActionEvent(controlView, ActionEvent.ACTION_FIRST,
        "looping " +
            "button");
    controlView.getPlayButton().addActionListener(listener);
    controlView.updateView(model);
    assertFalse(((AnimationPanel) controlView.getAnimationPanel()).isLooping());
    listener.actionPerformed(event);
  }

  @Test
  public void testSlowDownAnimationHandler() {
    ActionListener listener = new SlowDownHandler(this.controlView);

    ActionEvent event = new ActionEvent(controlView, ActionEvent.ACTION_FIRST,
        "slow down " +
            "button");
    controlView.getSlowDownButton().addActionListener(listener);
    controlView.updateView(model);

    assertEquals(((AnimationPanel) controlView.getAnimationPanel()).getRate(), 1.0);
    listener.actionPerformed(event);
    assertEquals(((AnimationPanel) controlView.getAnimationPanel()).getRate(), 0.5);

  }

  @Test
  public void testSpeedUpAnimationHandler() {
    ActionListener listener = new SpeedUpHandler(this.controlView);

    ActionEvent event = new ActionEvent(controlView, ActionEvent.ACTION_FIRST, "speed up"
        + " button");
    controlView.getSpeedUpButton().addActionListener(listener);
    controlView.updateView(model);

    assertEquals(((AnimationPanel) controlView.getAnimationPanel()).getRate(), 1.0);
    listener.actionPerformed(event);
    assertEquals(((AnimationPanel) controlView.getAnimationPanel()).getRate(), 2.0);
  }


}
