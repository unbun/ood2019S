package cs3500.animator.controller.handlers;

import cs3500.animator.shapes.IShape;
import cs3500.animator.view.ControllableView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Action listener class that adds a Shape to the view's model when its action is performed.
 */
public class AddShapeHandler implements ActionListener {

  ControllableView view;

  /**
   * Constructs an AddShapeHandler.
   *
   * @param view the controllable view to be passed in
   */
  public AddShapeHandler(ControllableView view) {
    this.view = Objects.requireNonNull(view);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    IShape toAdd = view.getDescribedShape();
    if (toAdd != null) {
      view.getModel().createShape(toAdd);
    } else {
      view.warningBox("Shape Manager", "Specified shape can't be added.");
    }
  }
}
