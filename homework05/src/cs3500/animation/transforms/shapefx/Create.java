package cs3500.animation.transforms.shapefx;


import cs3500.animation.shapes.LiveShape;
import cs3500.animation.transforms.ActionType;

/**
 * Print to the output that a LiveShape has been created (only really needed for assignment 5).
 */
public final class Create extends InstantShapeTransform {

  /**
   * Create a LiveShape within an action and set it to initalized.
   *
   * @param shape the LiveShape to initalize
   * @param startTime the to initalize it at
   */
  public Create(LiveShape shape, int startTime) {
    super(ActionType.CREATE, shape, startTime, startTime + 1);
  }

  @Override
  protected void mutate(LiveShape ls) {
    return;
  }

  @Override
  public String textualView() {
    return String.format("%s %s", type.getDescriptor(), myShape.getID());
  }
}
