package cs3500.animator.controller.handlers;

import cs3500.animator.view.AnimationPanel;
import cs3500.animator.view.ControllableView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implements the ability to toggle looping via a checkbox.
 */
public class LoopHandler implements ActionListener {

  ControllableView view;

  /**
   * Constructs a LoopHandler.
   *
   * @param view the controllable view to be used
   */
  public LoopHandler(ControllableView view) {
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (view.getLoopCheckBox().isSelected()) {
      ((AnimationPanel) this.view.getAnimationPanel()).setLooping(true);
      view.getLoopCheckBox().setText("Pause to disable looping");
    } else {
      if (!view.getPlayButtonTitle().equals("Press to pause")) {
        ((AnimationPanel) this.view.getAnimationPanel()).setLooping(false);
        view.getLoopCheckBox().setText("Enable Looping");
      } else {
        view.getLoopCheckBox().setSelected(true);
      }
    }
  }
}
