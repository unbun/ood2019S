package cs3500.animation.animations.printers;

import cs3500.animation.animations.ActionType;
import cs3500.animation.shapes.LiveShape;

/**
 * Represents the change in size of a given shape in a given model between given times.
 */
public class PrintScale extends AbstractPrint {

  private double xFactor;
  private double yFactor;

  public PrintScale(Appendable output,
      LiveShape shape, int startTime, int endTime, double xFactor, double yFactor) {
    super(output, ActionType.SCALE, shape, startTime, endTime);
    this.xFactor = xFactor;
    this.yFactor = yFactor;
  }

  @Override
  public void alterShape() {
    this.shape.scale(xFactor, yFactor);
  }

  @Override
  public String stateString(int currTime) {
    return "Scale height by " + yFactor + " & width by " + xFactor + " "
        + super.stateString(currTime);
  }
}
