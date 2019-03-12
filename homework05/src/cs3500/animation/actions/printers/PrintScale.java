package cs3500.animation.actions.printers;

import cs3500.animation.actions.ActionType;
import cs3500.animation.shapes.LiveShape;

/**
 * Represents the change in size of a given shape in a given model between given times.
 */
public class PrintScale extends AbstractPrint {

  private double xFactor;
  private double yFactor;

  /**
   * Represents the change in size of a given shape in a given model between given times.
   *
   * @param output an appendable output stream to print to.
   * @param shape the shape this action does.
   * @param startTime the time this action should start
   * @param endTime the time this action should end
   * @param xFactor how much to scale the width
   * @parma yFactor how much to scale the height
   */
  public PrintScale(Appendable output,
      LiveShape shape, int startTime, int endTime, double xFactor, double yFactor) {
    super(output, ActionType.SCALE, shape, startTime, endTime);
    this.xFactor = xFactor;
    this.yFactor = yFactor;
  }

  @Override
  public void applyHelp() {
    this.shape.scale(xFactor, yFactor);
  }

  @Override
  public String stateString(int currTime) {
    return String.format("Scale %s by h=%.1f, w=%.1f", shape.getID(), yFactor , xFactor)
        + super.stateString(currTime);
  }
}
