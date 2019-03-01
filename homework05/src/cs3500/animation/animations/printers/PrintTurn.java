package cs3500.animation.animations.printers;

import cs3500.animation.animations.ActionType;
import cs3500.animation.shapes.LiveShape;

/**
 * Represents a movement of a given shape in a given model from one position to another over
 * a given period of time.
 */
public class PrintTurn extends AbstractPrint {

  private int endHeading;

  public PrintTurn(Appendable outstream,
      LiveShape shape, int startTime, int endTime, int endHeading) {
    super(outstream, ActionType.ROTATE, shape, startTime, endTime);
    this.endHeading = endHeading;
  }


  @Override
  public void alterShape() {
    this.shape.turnTo(endHeading);
  }

  @Override
  public String stateString(int currTime) {
    return "Turn to " + endHeading + " " + super.stateString(currTime);
  }
}
