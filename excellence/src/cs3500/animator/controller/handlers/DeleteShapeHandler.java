package cs3500.animator.controller.handlers;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.ControllableView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Action listener class that deletes a shape from the view's model when it's action is performed.
 */
public class DeleteShapeHandler implements ActionListener {

  ControllableView view;
  AnimationModel model;

  /**
   * Constructs a DeleteShapeHandler.
   *
   * @param view the controllable view to be passed in
   */
  public DeleteShapeHandler(ControllableView view, AnimationModel model) {
    this.view = Objects.requireNonNull(view);
    this.model = Objects.requireNonNull(model);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String shapeName = view.getEditShapeName();
    for (int i = 0; i < model.getShapes().size(); i++) {
      if (model.getShapes().get(i).getName().equals(shapeName)) {
        model.removeShape(model.getShapes().get(i));
        return;
      }
    }

    view.warningBox("Shape Manager", "Could not find specified shape.");
  }
}
