package cs3500.animation.animations.printers;

import cs3500.animation.animations.ActionType;
import cs3500.animation.animations.InstantShapeAction;
import cs3500.animation.animations.ShapeAction;
import cs3500.animation.shapes.LiveShape;

/**
 * Create a LiveShape within an action and set it to initalized.
 */
public final class PrintCreate extends AbstractPrint implements InstantShapeAction {

  private boolean occured;

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
    this.occured = false;
  }

  @Override
  public void alterShape() {
    if (!occured()) {
      this.shape.init();
      this.occured = true;
      //invariant: once occured is true, it says true
      // (enforced b/c occured is only set to false in the constructor
    }
  }

  @Override
  public void apply(int currTime) {
    if (started(currTime) && !finished(currTime) && !occured()) {

      System.out.println("HELLO");

      println(String.format("shape %s", this.shape.shapeToString()));
      this.alterShape();
      this.occured = true;
    }
  }

  @Override
  public boolean occured() {
    return occured;
  }

  @Override
  public String stateString(int currTime) {
    return "Create " + super.stateString(currTime);
  }

  @Override
  public boolean conflict(ShapeAction other) {
    return false;
  }
}
