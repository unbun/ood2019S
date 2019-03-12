package cs3500.animation.actions.printers;


import cs3500.animation.actions.ActionType;
import cs3500.animation.shapes.LiveShape;

/**
 * Print to the output that a LiveShape has been created (only really needed for assignment 5).
 */
public final class PrintCreate extends AbstractPrint {

  /**
   * Create a LiveShape within an action and set it to initalized.
   *
   * @param output the Appendable stream to print to.
   * @param shape the LiveShape to initalize
   * @param startTime the to initalize it at
   */
  public PrintCreate(Appendable output,
      LiveShape shape, int startTime) {
    super(output, ActionType.CREATE, shape, startTime, startTime + 1);
  }

  @Override
  public void applyHelp() {
    return;
  }

  @Override
  public void apply(int currTime) {
    if (started(currTime) && !finished(currTime) && !printed) {
      println(String.format("shape %s", this.shape.getID()));
      this.printed = true;
    }
  }

  @Override
  public String stateString(int currTime) {
    return "Init " + shape.getID() + super.stateString(currTime);
  }

}
