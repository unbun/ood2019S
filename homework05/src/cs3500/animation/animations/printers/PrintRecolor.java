package cs3500.animation.animations.printers;

import cs3500.animation.animations.ActionType;
import cs3500.animation.shapes.LiveShape;
import java.awt.Color;

/**
 * Represents a gradient change in the color of a given shape in a given model over a given
 * period of time.
 */
public class PrintRecolor extends AbstractPrint {

  private Color newColor;

  public PrintRecolor(Appendable output,
      LiveShape shape, int startTime, int endTime, Color newColor ) {
    super(output, ActionType.RECOLOR, shape, startTime, endTime);
    this.newColor = newColor;
  }


  @Override
  public void alterShape() {
    this.shape.recolor(this.newColor);
  }

  @Override
  public String stateString(int currTime) {
    return "Recolor to " + newColor.toString() + " " + super.stateString(currTime);
  }
}
