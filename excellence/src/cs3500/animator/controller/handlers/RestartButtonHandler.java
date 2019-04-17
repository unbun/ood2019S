package cs3500.animator.controller.handlers;

import cs3500.animator.view.AnimationPanel;
import cs3500.animator.view.ControllableView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Enables the restart functionality of an animation via a button.
 */
public class RestartButtonHandler implements ActionListener {

  ControllableView view;

  /**
   * Constructs a RestartButtonHandler.
   *
   * @param view the controllable view to be passed in
   */
  public RestartButtonHandler(ControllableView view) {
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    ((AnimationPanel) this.view.getAnimationPanel()).setTime(0);
  }
}
