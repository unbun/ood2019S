package cs3500.animator.controller.handlers;

import cs3500.animator.view.AnimationPanel;
import cs3500.animator.view.ControllableView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Toggles the view's play/pause state.
 */
public class PlayButtonHandler implements ActionListener {

  ControllableView view;

  /**
   * Constructs a PlayButtonHandler.
   *
   * @param view the controllable view to be passed in
   */
  public PlayButtonHandler(ControllableView view) {
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (view.getPlayButtonTitle().equals("Press to pause")) {
      view.setPlayButtonTitle("Press to play");
      ((AnimationPanel) this.view.getAnimationPanel()).setPlaying(false);
    } else {
      view.setPlayButtonTitle("Press to pause");
      ((AnimationPanel) this.view.getAnimationPanel()).setPlaying(true);
    }
  }
}
