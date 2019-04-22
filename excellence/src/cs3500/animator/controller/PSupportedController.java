package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.LocalToProviderModel;
import cs3500.animator.shapes.IShape;
import cs3500.animator.view.ProviderToLocalEditView;
import cs3500.animator.view.ViewType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;

/**
 * A {@code ShapeFXController} that supports the Provider implemented Views.
 */
public class PSupportedController extends ShapeFXController implements ActionListener {

  /**
   * Default constructor for a controller.
   *
   * @param model the desired model to represent the animation
   * @param args arguments received in main method
   * @throws IllegalArgumentException if passed a null model
   */
  public PSupportedController(AnimationModel model, String[] args)
      throws IllegalArgumentException {
    super(model, args);
    if (!(model instanceof LocalToProviderModel)) {
      throw new UnsupportedClassVersionError("Adapted Controller should use the Adapted Model");
    }
  }

  /**
   * Creates the view of the animation based on user specified type; can be one of the following.
   * <ul>
   * <item>visual</item>
   * <item>edit</item>
   * <item>svg</item>
   * <item>text</item>
   * </ul>
   *
   * @param view the view to be made
   */
  @Override
  protected void createView(String view) {
    if (view.equalsIgnoreCase("provider")) {
      this.view = new ProviderToLocalEditView(this);
      setViewPanelModel();
    } else {
      super.createView(view);
    }
  }

  @Override
  public void run() {
    view.init();
    try {
      outputFile.append(view.updateView(model));
    } catch (IOException e) {
      throw new IllegalArgumentException("Error in creating view.");
    }
    if (!isDefaultOutput) {
      try {
        ((FileWriter) outputFile).flush();
        ((FileWriter) outputFile).close();
      } catch (IOException e) {
        throw new IllegalStateException("Cannot close file.");
      }
    }
  }



  @Override
  public void actionPerformed(ActionEvent e) {
    if (this.view.getViewType() == ViewType.PROVIDED_EDIT
        || this.view.getViewType() == ViewType.PROVIDED_VISUAL) {

      ProviderToLocalEditView castView = (ProviderToLocalEditView) this.view;

      String shapeName;
      String shapeType;

      LocalToProviderModel adpModel = (LocalToProviderModel) this.model;
      switch (e.getActionCommand()) {
        //Jbuttons
        case "start":
          // This is what we meant when we say we weren't given enough information. What
          // does it mean to "start" from the POV of the controller, view, and model? So we had
          // to guess of make our own solutions

          ((ProviderToLocalEditView) this.view).start();
          break;
        case "restart":
          ((ProviderToLocalEditView) this.view).setTick(0);
          break;
        case "dropShape":
          shapeName = ((JComboBox) ((ProviderToLocalEditView) this.view).getSomething("whichShape"))
              .getSelectedItem().toString();
          adpModel.removeAShape(shapeName);
          break;
        case "addShape":
          shapeType = ((JComboBox) ((ProviderToLocalEditView) this.view)
              .getSomething("whichShapeType")).getSelectedItem().toString();
          shapeName = ((JComboBox) ((ProviderToLocalEditView) this.view).getSomething("whichShape"))
              .getSelectedItem().toString();
          adpModel.declareAShape(shapeName, shapeType);
          break;
        case "changeKeyFrame":
          shapeType = ((JComboBox) ((ProviderToLocalEditView) this.view)
              .getSomething("whichShapeType")).getSelectedItem().toString();
          shapeName = ((JComboBox) ((ProviderToLocalEditView) this.view).getSomething("whichShape"))
              .getSelectedItem().toString();
          IShape atT1 = null;
          for (IShape s : adpModel.getShapes()) {
            if (s.getName().equalsIgnoreCase(shapeName)) {
              atT1 = s;
              break;
            }
          }

          if (atT1 == null) {
            return;
          }

          /*

          Using atT1 to get the "1" values, and the view's JTexFields to get the "2" values

          //Also not given: What tells time!

          */

          int t2 = Integer.parseInt(
              ((JComboBox) ((ProviderToLocalEditView) this.view).getSomething("t")).toString());
          int x2 = Integer.parseInt(
              ((JComboBox) ((ProviderToLocalEditView) this.view).getSomething("x")).toString());
          int y2 = Integer.parseInt(
              ((JComboBox) ((ProviderToLocalEditView) this.view).getSomething("y")).toString());
          int w2 = Integer.parseInt(
              ((JComboBox) ((ProviderToLocalEditView) this.view).getSomething("width")).toString());
          int h2 = Integer.parseInt(
              ((JComboBox) ((ProviderToLocalEditView) this.view).getSomething("height"))
                  .toString());
          int r2 = Integer.parseInt(
              ((JComboBox) ((ProviderToLocalEditView) this.view).getSomething("r")).toString());
          int g2 = Integer.parseInt(
              ((JComboBox) ((ProviderToLocalEditView) this.view).getSomething("g")).toString());
          int b2 = Integer.parseInt(
              ((JComboBox) ((ProviderToLocalEditView) this.view).getSomething("b")).toString());

          adpModel
              .addAMotion(shapeName, atT1.gett0(), (int) atT1.getPosn().x, (int) atT1.getPosn().y,
                  (int) atT1.getWidth(), (int) atT1.getHeight(),
                  (int) atT1.getColor().getRed(), (int) atT1.getColor().getGreen(),
                  (int) atT1.getColor().getBlue(),
                  t2, x2, y2, w2, h2, r2, g2, b2);

          break;
        //Toggle buttons
        case "pauseResume":
          ((ProviderToLocalEditView) this.view).pause();
          break;
        case "rewind":
          ((ProviderToLocalEditView) this.view).setTick(0);
          break;
        case "loopBack":
          boolean looping = ((JToggleButton) ((ProviderToLocalEditView) this.view)
              .getSomething("loopBack")).isSelected();
          ((ProviderToLocalEditView) this.view).setLooping(looping);
          break;
        default:
          return;
      }
    }
  }


}
