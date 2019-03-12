package cs3500.animation.actions.printers;


import cs3500.animation.actions.ActionType;
import cs3500.animation.shapes.LiveShape;

/**
 * Print to the output that a LiveShape has been created (only needed for assignment 5), and ends
 * (which is noted by the overridden started and finished method).
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
    super(output, ActionType.CREATE, shape, startTime, startTime);
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
  public boolean started(int currTime) {
    return printed || super.started(currTime);
  }

  @Override
  public boolean finished(int currTime) {
    return printed;
  }

  @Override
  public String stateString(int currTime) {
    return "Create " + super.stateString(currTime);
  }

}
