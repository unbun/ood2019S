package cs3500.animation.transforms.shapefx;

import cs3500.animation.shapes.LiveShape;
import cs3500.animation.transforms.ActionType;
import java.awt.Color;

/**
 * Represents a gradient change in the color of a given myShape in a given model over a given period
 * of time.
 */
public class Recolor extends InstantShapeTransform {

  private Color newColor;

  /**
   * Represents a gradient change in the color of a given myShape in a given model over a given
   * period of time.
   *
   * @param shape the myShape this action does.
   * @param startTime the time this action should start
   * @param endTime the time this action should end
   * @param newColor the color this action should result in for the myShape
   */
  public Recolor(LiveShape shape, int startTime, int endTime, Color newColor) {
    super(ActionType.RECOLOR, shape, startTime, endTime);
    this.newColor = newColor;
  }

  @Override
  public void mutate(LiveShape ls) {
    ls.recolor(this.newColor);
  }
}
