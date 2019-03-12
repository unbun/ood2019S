package cs3500.animation.actions.printers;

import cs3500.animation.actions.ActionType;
import cs3500.animation.shapes.LiveShape;

/**
 * Represents a period of time in which no fields of the shape will change.
 */
public class PrintIdle extends AbstractPrint {

  /**
   * Represents a period of time in which no fields of the shape will change.
   *
   * @param output an appendable output stream to print to.
   * @param shape the shape this action does.
   * @param startTime the time this action should start
   * @param endTime the time this action should end
   */
  public PrintIdle(Appendable output,
      LiveShape shape, int startTime, int endTime) {
    super(output, ActionType.IDLE, shape, startTime, endTime);
  }


  @Override
  public void applyHelp() {
    return;
  }

  @Override
  public String stateString(int currTime) {
    return "Freeze " + super.stateString(currTime);
  }
}
