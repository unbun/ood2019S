package cs3500.animation.transforms.shapefx;

import cs3500.animation.shapes.LiveShape;
import cs3500.animation.transforms.ActionType;

/**
 * Represents a movement of a given myShape in a given model from one position to another over a
 * given period of time.
 */
public class Turn extends InstantShapeTransform {

  private int endHeading;

  /**
   * Represents a movement of a given myShape in a given model from one position to another over a
   * given period of time.
   *
   * @param shape the myShape this action does.
   * @param startTime the time this action should start
   * @param endTime the time this action should end
   * @param endHeading the angle this action should result in for the myShape
   */
  public Turn(LiveShape shape, int startTime, int endTime, int endHeading) {
    super(ActionType.ROTATE, shape, startTime, endTime);
    this.endHeading = endHeading;
  }


  @Override
  public void mutate(LiveShape ls) {
    ls.turnTo(endHeading);
  }
}
