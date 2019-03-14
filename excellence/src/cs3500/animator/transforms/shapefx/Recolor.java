package cs3500.animator.transforms.shapefx;

import cs3500.animator.shapes.LiveShape;
import cs3500.animator.transforms.InstantTransform;
import cs3500.animator.transforms.TransformType;
import java.awt.Color;

/**
 * Represents a gradient change in the color of a given myShape in a given model over a given period
 * of time.
 */
public final class Recolor extends InstantTransform {

  private Color newColor;

  /**
   * Represents a gradient change in the color of a given myShape in a given model over a given
   * period of time.
   *
   * @param startTime the time this action should start
   * @param endTime the time this action should end
   * @param newColor the color this action should result in for the myShape
   */
  public Recolor(int startTime, int endTime, Color newColor) {
    super(TransformType.RECOLOR, startTime, endTime);
    this.newColor = newColor;
  }

  @Override
  protected void mutate(LiveShape ls) {
    ls.recolor(this.newColor);
  }
}
