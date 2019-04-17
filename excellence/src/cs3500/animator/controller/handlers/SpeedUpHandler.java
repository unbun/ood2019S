package cs3500.animator.controller.handlers;

import cs3500.animator.view.AnimationPanel;
import cs3500.animator.view.ControllableView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Speeds up the animation by scaling the panel's rate by a positive factor.
 */
public class SpeedUpHandler implements ActionListener {

  ControllableView view;

  /**
   * Constructs a SpeedUpHandler.
   *
   * @param view the controllable view to be passed in
   */
  public SpeedUpHandler(ControllableView view) {
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    ((AnimationPanel) this.view.getAnimationPanel()).speedUpAnimation();
    this.view.getCurrentSpeedLabel()
        .setText("Rate: " + ((AnimationPanel) this.view.getAnimationPanel()).getRate() + "x");
  }
}
