package cs3500.animation.actions.printers;

import cs3500.animation.actions.ActionType;
import cs3500.animation.shapes.LiveShape;

/**
 * Represents a movement of a given shape in a given model from one position to another over a given
 * period of time.
 */
public class PrintTurn extends AbstractPrint {

  private int endHeading;

  /**
   * Represents a movement of a given shape in a given model from one position to another over a
   * given period of time.
   *
   * @param outstream an appendable output stream to print to.
   * @param shape the shape this action does.
   * @param startTime the time this action should start
   * @param endTime the time this action should end
   * @param endHeading the angle this action should result in for the shape
   */
  public PrintTurn(Appendable outstream,
      LiveShape shape, int startTime, int endTime, int endHeading) {
    super(outstream, ActionType.ROTATE, shape, startTime, endTime);
    this.endHeading = endHeading;
  }


  @Override
  public void applyHelp() {
    this.shape.turnTo(endHeading);
  }

  @Override
  public String stateString(int currTime) {
    return "Turn " + shape.getID() + " to " + endHeading
        + " degrees " + super.stateString(currTime);
  }
}
