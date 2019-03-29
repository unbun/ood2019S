package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.shapes.IShape;
import cs3500.animator.transforms.Transform;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * The implementation of the textual view. Creates a textual output describing the shapes and
 * their specific animations.
 */
public class TextualView implements IAnimationView {

  @Override
  public ViewType getViewType() {
    return ViewType.TEXT;
  }

  @Override
  public void setListeners(MouseListener mouse, KeyListener keys) {
    return;
  }

  @Override
  public AnimationPanel getAnimationPanel() {
    throw new UnsupportedOperationException("Textual does not use panels");
  }

  @Override
  public String makeView(AnimationModel model) {
    StringBuilder sb = new StringBuilder();
    model.getShapes().sort(Comparator.comparingInt(IShape::getBirthTime));

    if (model.getShapes().isEmpty()) {
      //do nothing
    } else {

      //all of the shapes
      for (int i = 0; i < model.getShapes().size(); i++) {

        sb.append("shape ");
        sb.append(model.getShapes().get(i).getName()); // name
        sb.append(" ");
        sb.append(model.getShapes().get(i).getType()); // type
        sb.append("\n");

        ArrayList<Transform> transforms = model.getShapes().get(i).getOperations();
        transforms.sort(Comparator.comparingInt(Transform::getStartTime));
        for(Transform t : transforms){
          sb.append(t.getDescription(model));
        }
        sb.append("\n");
      }

    }
    return sb.toString();
  }

  @Override
  public void makeVisible() {
    // method does not apply to this view
  }

  @Override
  public void setModel(AnimationModel model) {
    return;
  }

}
