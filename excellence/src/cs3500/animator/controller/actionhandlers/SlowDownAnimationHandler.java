package cs3500.animator.controller.actionhandlers;

import cs3500.animator.view.ControllableView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener class that slows the animation down by scaling the rate by a negative rate.
 */
public class SlowDownAnimationHandler implements ActionListener {

  ControllableView view;

  /**
   * Default constructor.
   *
   * @param view the hybrid view to be passed in.
   */
  public SlowDownAnimationHandler(ControllableView view) {
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.view.getAnimationPanel().slowDownRate();
    this.view.getCurrentSpeedLabel().setText("Rate: " + view.getAnimationPanel().getRate() + "x");
  }
}
