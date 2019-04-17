package cs3500.animator.controller.handlers;

import cs3500.animator.view.AnimationPanel;
import cs3500.animator.view.ControllableView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Slows the animation down by scaling the rate by a negative factor.
 */
public class SlowDownHandler implements ActionListener {

  ControllableView view;

  /**
   * Constructs a SlowDownHandler.
   *
   * @param view the controllable view to be passed in.
   */
  public SlowDownHandler(ControllableView view) {
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    ((AnimationPanel) this.view.getAnimationPanel()).slowDownAnimation();
    this.view.getCurrentSpeedLabel()
        .setText("Rate: " + ((AnimationPanel) this.view.getAnimationPanel()).getRate() + "x");
  }
}
