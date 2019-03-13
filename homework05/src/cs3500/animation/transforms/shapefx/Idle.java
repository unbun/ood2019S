package cs3500.animation.transforms.shapefx;

import cs3500.animation.shapes.LiveShape;
import cs3500.animation.transforms.ActionType;

/**
 * Represents a period of time in which no fields of the myShape will change.
 */
public class Idle extends InstantShapeTransform {

  /**
   * Represents a period of time in which no fields of the myShape will change.
   *
   * @param shape the myShape this action does.
   * @param startTime the time this action should start
   * @param endTime the time this action should end
   */
  public Idle(LiveShape shape, int startTime, int endTime) {
    super(ActionType.IDLE, shape, startTime, endTime);
  }

  @Override
  protected void mutate(LiveShape ls) {
    return;
  }
}
