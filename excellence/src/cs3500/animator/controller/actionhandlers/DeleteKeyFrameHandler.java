package cs3500.animator.controller.actionhandlers;

import cs3500.animator.view.ControllableView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Action listener class that deletes a KeyFrame to the view's model when it's action is performed.
 */
public class DeleteKeyFrameHandler implements ActionListener {

  ControllableView view;

  /**
   * Default constructor.
   *
   * @param view the hybrid view to be passed in
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
        view.warnDialog("KeyFrame Editor", "Specified KeyFrame Values are not valid numbers");
      } catch (ArrayIndexOutOfBoundsException oobe) {
        view.warnDialog("KeyFrame Editor", "Not enough KeyFrame Values are validly specified");
      }
    }
  }
}
