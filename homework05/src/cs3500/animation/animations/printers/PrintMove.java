package cs3500.animation.animations.printers;

import cs3500.animation.animations.ActionType;
import cs3500.animation.shapes.LiveShape;
import cs3500.animation.utils.Posn;

/**
 * Represents a movement of a given shape in a given model from one position to another over a given
 * period of time.
 */
public class PrintMove extends AbstractPrint {

  private Posn endPosn;

  /**
   * Represents a movement of a given shape in a given model from one position to another over a
   * given period of time.
   *
   * @param outstream an appendable output stream to print to.
   * @param shape the shape this action does.
   * @param startTime the time this action should start
   * @param endTime the time this action should end
   * @param endPosn the posn this action should result in for the shape
   */
  public PrintMove(Appendable outstream,
      LiveShape shape, int startTime, int endTime, Posn endPosn) {
    super(outstream, ActionType.MOVE, shape, startTime, endTime);
    this.endPosn = endPosn;
  }


  @Override
  public void alterShape() {
    this.shape.moveTo(endPosn);
  }

  @Override
  public String stateString(int currTime) {
    return "Move to " + endPosn.toString() + " " + super.stateString(currTime);
  }
}
