package cs3500.animator.controller.handlers;

import cs3500.animator.view.ControllableView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Action listener class that deletes a KeyFrame from the view's model when its action is
 * performed.
 */
public class DeleteKeyFrameHandler implements ActionListener {

  ControllableView view;

  /**
   * Constructs a DeleteKeyFrameHandler.
   *
   * @param view the controllable view to be passed in
   */
  public DeleteKeyFrameHandler(ControllableView view) {
    this.view = Objects.requireNonNull(view);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    String[] fields = view.getDescribedKeyFrame();
    if (fields != null) {
      System.out.println("Deleted KeyFrame");
      try {
        view.getModel().removeKeyFrame(fields[0],
            Integer.parseInt(fields[1]));
      } catch (NumberFormatException nfe) {
        view.warningBox("KeyFrame Editor",
            "Specified KeyFrame Values are not valid numbers");
      } catch (ArrayIndexOutOfBoundsException oobe) {
        view.warningBox("KeyFrame Editor",
            "Not enough KeyFrame Values are validly specified");
      }
    }
  }
}
