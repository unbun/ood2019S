package cs3500.animation.shapes;

import cs3500.animation.utils.Posn;
import cs3500.animation.utils.Utils;
import java.awt.Color;
import java.util.Objects;

/**
 * Represents a Shape that can move/be animated. (In other words a Shape with built-in, trackable
 * mutation).
 */
public abstract class LiveShape extends AbstractShape {

  private boolean initalized;

  public LiveShape(int height, int width, int heading, Posn posn, Color color,
      String name) {
    super(height, width, heading, posn, color, name);
    initalized = true;
  }

  /**
   * Move the shape to the given positoin.
   *
   * @param endPoint the position to move through
   */
  public void moveTo(Posn endPoint) {
    this.posn = Objects.requireNonNull(endPoint);
  }

  /**
   * Turn the shape to head in the given angle.
   *
   * @param endHeading the angle to turn to
   */
  public void turnTo(int endHeading) {
    this.heading = endHeading;
  }

  /**
   * Change the color of the shape to the given color.
   *
   * @param c the color to change to
   */
  public void recolor(Color c) {
    this.color = c;
  }

  /**
   * Change the size of the shape by the given factors.
   *
   * @param xFactor the factor to change the width by
   * @param yFactor the factor to change the height by
   */
  public void scale(double xFactor, double yFactor) {
    this.height *= (Double) Utils.requireNonNegative(yFactor, "yFactor");
    this.width *= (Double) Utils.requireNonNegative(xFactor, "xFactor");
  }

  /**
   * Change the initalization boolean.
   */
  public void init() {
    this.initalized = true;
  }

  /**
   * Is this live shape initalized.
   *
   * @return if the shape is init-ed
   */
  public boolean isInitalized() {
    return this.initalized;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }
}
