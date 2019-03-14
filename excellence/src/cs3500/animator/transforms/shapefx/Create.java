package cs3500.animator.transforms.shapefx;


import cs3500.animator.shapes.LiveShape;
import cs3500.animator.transforms.InstantTransform;
import cs3500.animator.transforms.TransformType;

/**
 * Print to the output that a LiveShape has been created (only really needed for assignment 5).
 */
public final class Create extends InstantTransform {

  String id;

  /**
   * Create a LiveShape within an action and set it to initalized.
   */
  public Create(int startTime) {
    super(TransformType.CREATE, startTime, startTime + 1);
  }

  @Override
  protected void mutate(LiveShape ls) {
    id = ls.getID();
  }

  @Override
  public String textualStr() {
    return String.format("shape %s", id);
  }
}
