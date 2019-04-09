package cs3500.animator.controller.actionHandlers;

import cs3500.animator.view.ControllableView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener class toggles the view's play/pause state when its action is performed by the
 * corresponding buttons.
 */
public class PlayButtonHandler implements ActionListener {

  ControllableView view;

  /**
   * Default constructor.
   *
   * @param view the hybrid view to be passed in
   */
  public PlayButtonHandler(ControllableView view) {
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (view.getPlayButtonTitle().equalsIgnoreCase("Press to pause")) {
      view.setPlayButtonTitle("Press to play");
      this.view.getAnimationPanel().setPlaying(false);
    } else {
      view.setPlayButtonTitle("Press to pause");
      this.view.getAnimationPanel().setPlaying(true);
    }
  }
}
