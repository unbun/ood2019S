package cs3500.animator.controller.actionhandlers;

import cs3500.animator.view.ControllableView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Action listener class that deletes a KeyFrame and then adds a new one to the view's model when
 * it's action is performed.
 */
public class UpdateKeyFrameHandler implements ActionListener {

  ControllableView view;

  /**
   * Default constructor.
   *
   * @param view the hybrid view to be passed in
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
        view.warnDialog("KeyFrame Editor", "Specified KeyFrame Values are not valid numbers");
      } catch (ArrayIndexOutOfBoundsException oobe) {
        view.warnDialog("KeyFrame Editor", "Not enough KeyFrame Values are validly specified");
      }
    }
  }
}
