package cs3500.animator.controller.handlers;

import cs3500.animator.view.ControllableView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Deletes a KeyFrame and then adds an updated one to the view's model.
 */
public class UpdateKeyFrameHandler implements ActionListener {

  ControllableView view;

  /**
   * Constructs an UpdateKeyFrameHandler.
   *
   * @param view the controllable view to be passed in
   */
  public UpdateKeyFrameHandler(ControllableView view) {
    this.view = Objects.requireNonNull(view);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String[] fields = view.getDescribedKeyFrame();
    if (fields != null) {
      System.out.println("Created KeyFrame");
      try {
        view.getModel().removeKeyFrame(fields[0],
            Integer.parseInt(fields[1]));
        view.getModel().createKeyFrame(fields[0],
            Integer.parseInt(fields[1]), Integer.parseInt(fields[2]),
            Integer.parseInt(fields[3]), Integer.parseInt(fields[4]),
            Integer.parseInt(fields[5]), Integer.parseInt(fields[6]),
            Integer.parseInt(fields[7]), Integer.parseInt(fields[8]));
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
