package cs3500.animation.transforms.shapefx;

import cs3500.animation.shapes.LiveShape;
import cs3500.animation.transforms.ActionType;
import cs3500.animation.utils.Posn;

/**
 * Represents a movement of a given myShape in a given model from one position to another over a
 * given period of time.
 */
public class Move extends InstantShapeTransform {

  private Posn endPosn;

  /**
   * Represents a movement of a given myShape in a given model from one position to another over a
   * given period of time.
   *
   * @param shape the myShape this action does.
   * @param startTime the time this action should start
   * @param endTime the time this action should end
   * @param endPosn the posn this action should result in for the myShape
   */
  public Move(LiveShape shape, int startTime, int endTime, Posn endPosn) {
    super(ActionType.MOVE, shape, startTime, endTime);
    this.endPosn = endPosn;
  }

  @Override
  public void mutate(LiveShape ls) {
    ls.moveTo(endPosn);
  }
}
