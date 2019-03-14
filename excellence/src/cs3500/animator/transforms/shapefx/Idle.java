package cs3500.animator.transforms.shapefx;

import cs3500.animator.shapes.LiveShape;
import cs3500.animator.transforms.InstantTransform;
import cs3500.animator.transforms.TransformType;

/**
 * Represents a period of time in which no fields of the myShape will change.
 */
public final class Idle extends InstantTransform {

  /**
   * Represents a period of time in which no fields of the myShape will change.
   *
   * @param startTime the time this action should start
   * @param endTime the time this action should end
   */
  public Idle(int startTime, int endTime) {
    super(TransformType.IDLE, startTime, endTime);
  }

  @Override
  protected void mutate(LiveShape ls) {
  }
}
