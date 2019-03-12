package cs3500.animation.actions.printers;

import cs3500.animation.actions.ActionType;
import cs3500.animation.shapes.LiveShape;
import java.awt.Color;

/**
 * Represents a gradient change in the color of a given shape in a given model over a given period
 * of time.
 */
public class PrintRecolor extends AbstractPrint {

  private Color newColor;

  /**
   * Represents a gradient change in the color of a given shape in a given model over a given period
   * of time.
   *
   * @param output an appendable output stream to print to.
   * @param shape the shape this action does.
   * @param startTime the time this action should start
   * @param endTime the time this action should end
   * @param newColor the color this action should result in for the shape
   */
  public PrintRecolor(Appendable output,
      LiveShape shape, int startTime, int endTime, Color newColor) {
    super(output, ActionType.RECOLOR, shape, startTime, endTime);
    this.newColor = newColor;
  }


  @Override
  public void applyHelp() {
    this.shape.recolor(this.newColor);
  }

  @Override
  public String stateString(int currTime) {
    return "Recolor " + shape.getID() + " to " +
        String.format("[r=%d, g=%d, b=%d]",
            newColor.getRed(), newColor.getGreen(), newColor.getBlue())
        + " " + super.stateString(currTime);
  }
}
