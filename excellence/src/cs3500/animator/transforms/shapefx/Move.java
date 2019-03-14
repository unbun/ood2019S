package cs3500.animator.transforms.shapefx;

import cs3500.animator.shapes.LiveShape;
import cs3500.animator.transforms.InstantTransform;
import cs3500.animator.transforms.TransformType;
import cs3500.animator.util.Posn;

/**
 * Represents a movement of a given myShape in a given model from one position to another over a
 * given period of time.
 */
public final class Move extends InstantTransform {

  private Posn endPosn;

  /**
   * Represents a movement of a given myShape in a given model from one position to another over a
   * given period of time.
   *
   * @param startTime the time this action should start
   * @param endTime the time this action should end
   * @param endPosn the posn this action should result in for the myShape
   */
  public Move(int startTime, int endTime, Posn endPosn) {
    super(TransformType.MOVE, startTime, endTime);
    this.endPosn = endPosn;
  }


  @Override
  protected void mutate(LiveShape ls) {
    ls.moveTo(endPosn);
  }
}
