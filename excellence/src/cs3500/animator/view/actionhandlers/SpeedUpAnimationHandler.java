package cs3500.animator.view.actionhandlers;

import cs3500.animator.view.ControllableView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener class that speeds up the animation.
 */
public class SpeedUpAnimationHandler implements ActionListener {
  ControllableView view;

  /**
   * Default constructor.
   *
   * @param view the hybrid view to be passed in
   */
  public SpeedUpAnimationHandler(ControllableView view) {
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.view.getAnimationPanel().speedUpRate();
    this.view.getCurrentSpeedLabel().setText("Rate: " + view.getAnimationPanel().getRate() + "x");
  }
}
