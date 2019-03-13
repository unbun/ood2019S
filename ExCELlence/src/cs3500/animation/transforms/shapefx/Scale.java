package cs3500.animation.transforms.shapefx;

import cs3500.animation.shapes.LiveShape;
import cs3500.animation.transforms.ActionType;

/**
 * Represents the change in size of a given myShape in a given model between given times.
 */
public class Scale extends InstantShapeTransform {

  private double xFactor;
  private double yFactor;

  /**
   * Represents the change in size of a given myShape in a given model between given times.
   *
   * @param shape the myShape this action does.
   * @param startTime the time this action should start
   * @param endTime the time this action should end
   * @param xFactor how much to scale the width
   * @parma yFactor how much to scale the height
   */
  public Scale(LiveShape shape, int startTime, int endTime, double xFactor, double yFactor) {
    super(ActionType.SCALE, shape, startTime, endTime);
    this.xFactor = xFactor;
    this.yFactor = yFactor;
  }

  @Override
  public void mutate(LiveShape ls) {
    ls.scale(xFactor, yFactor);
  }
}
