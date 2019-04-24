package cs3500.animator.controller.actionhandlers;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.util.Utils;
import cs3500.animator.view.ControllableView;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Handle changing the time of the view by a JSlider source.
 */
public class TimeScrubHandler implements ChangeListener {

  ControllableView view;
  AnimationModel model;

  /**
   * Default constructor.
   *
   * @param view the hybrid view to be passed in.
   */
  public TimeScrubHandler(ControllableView view, AnimationModel model) {
    this.view = view;
    this.model = model;
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    JSlider source;
    try {
      source = (JSlider) e.getSource();
    } catch (ClassCastException cce) {
      throw new IllegalArgumentException(cce);
    }

    float timeMin = 0;
    float timeMax = model.getEndTime();

    float slideMin = source.getMinimum();
    float slideMax = source.getMaximum();

    float x = source.getValue();

    //only scrub if the animation is paused;
    if (view.getPlayButtonTitle().equalsIgnoreCase("Press to play")) {
      float mapped = Utils.map(x, slideMin, slideMax, timeMin, timeMax);
      this.view.getAnimationPanel().setTime((int) mapped);
    }
  }
}
