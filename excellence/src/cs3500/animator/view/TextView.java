package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.shapes.IShape;
import cs3500.animator.transforms.Transform;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JPanel;

/**
 * Implementation of a textual view. Creates a textual output describing the shapes and their
 * specific animations.
 */
public class TextView implements IView {

  @Override
  public ViewType getViewType() {
    return ViewType.TEXT;
  }

  // not applicable to this view
  @Override
  public void setListeners(MouseListener mouse, KeyListener keys) {
    return;
  }

  @Override
  public JPanel getAnimationPanel() {
    throw new UnsupportedOperationException("Textual does not use panels");
  }

  @Override
  public String updateView(AnimationModel model) {
    StringBuilder sb = new StringBuilder();
    model.getShapes().sort(Comparator.comparingInt(IShape::gett0));
    for (int i = 0; i < model.getShapes().size(); i++) {
      sb.append("shape ");
      sb.append(model.getShapes().get(i).getName());
      sb.append(" ");
      sb.append(model.getShapes().get(i).getType());
      sb.append("\n");
      ArrayList<Transform> transforms = model.getShapes().get(i).getTransformations();
      transforms.sort(Comparator.comparingInt(Transform::getStartTime));
      for (Transform t : transforms) {
        sb.append(t.toText(model));
      }
      sb.append("\n");
    }

    return sb.toString();
  }

  @Override
  public void init() {
    // method does not apply to this view
  }

  @Override
  public void setModel(AnimationModel model) {
    return;
  }

}
