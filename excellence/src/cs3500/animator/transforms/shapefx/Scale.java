package cs3500.animator.transforms.shapefx;

import cs3500.animator.shapes.LiveShape;
import cs3500.animator.transforms.InstantTransform;
import cs3500.animator.transforms.TransformType;

/**
 * Represents the change in size of a given myShape in a given model between given times.
 */
public final class Scale extends InstantTransform {

  private double xFactor;
  private double yFactor;

  /**
   * Represents the change in size of a given myShape in a given model between given times.
   *
   * @param startTime the time this action should start
   * @param endTime the time this action should end
   * @param xFactor how much to scale the width
   * @parma yFactor how much to scale the height
   */
  public Scale(int startTime, int endTime, double xFactor, double yFactor) {
    super(TransformType.SCALE, startTime, endTime);
    this.xFactor = xFactor;
    this.yFactor = yFactor;
  }

  @Override
  protected void mutate(LiveShape ls) {
    ls.scale(xFactor, yFactor);
  }
}
