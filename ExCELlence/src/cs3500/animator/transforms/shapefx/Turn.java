package cs3500.animator.transforms.shapefx;

import cs3500.animator.shapes.LiveShape;
import cs3500.animator.transforms.TransformType;
import cs3500.animator.transforms.InstantTransform;

/**
 * Represents a movement of a given myShape in a given model from one position to another over a
 * given period of time.
 */
public final class Turn extends InstantTransform {

  private int endHeading;

  /**
   * Represents a movement of a given myShape in a given model from one position to another over a
   * given period of time.
   *
   * @param startTime the time this action should start
   * @param endTime the time this action should end
   * @param endHeading the angle this action should result in for the myShape
   */
  public Turn(int startTime, int endTime, int endHeading) {
    super(TransformType.ROTATE, startTime, endTime);
    this.endHeading = endHeading;
  }


  @Override
  protected void mutate(LiveShape ls) {
    ls.turnTo(endHeading);
  }
}
