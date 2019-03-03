package cs3500.animation.animations;

import cs3500.animation.shapes.LiveShape;
import java.util.Objects;

/**
 * An action that updates/mutates a LiveShape.
 */
public abstract class AbstractShapeAction implements ShapeAction {

  protected LiveShape shape;

  protected ActionType type;

  protected int startTime;
  protected int endTime;

  /**
   * An action that updates/mutates a LiveShape.
   *
   * @param shape the shape to mutate
   * @param startTime the time to mutate it
   * @param endTime the time to mutate it
   */
  public AbstractShapeAction(LiveShape shape, int startTime, int endTime) {

    if (startTime > endTime) {
      throw new IllegalArgumentException("Start time must be less than end time!");
    }

    this.shape = Objects.requireNonNull(shape);

    this.startTime = startTime;
    this.endTime = endTime;

  }

  /**
   * The method that mutates the shape of the class.
   */
  public abstract void alterShape();

  @Override
  public int getStartTime() {
    return this.startTime;
  }

  /**
   * Determines if the given motion conflicts with this one. A motion conflicts with another motion
   * if it occurs during the same time interval and:
   * <li>
   * <ol>The other action is a motion on a shape.</ol>
   * <ol>The motion type are the same.</ol>
   * <ol>The times of the 2 are overlapping</ol>
   * <ol>The shapes are the equal according to the shape's {@code equal()} method.</ol>
   * </li>
   */
  public boolean conflict(ShapeAction other) {
    if (other instanceof AbstractShapeAction) {
      AbstractShapeAction otherSA = (AbstractShapeAction) other;
      return otherSA.shape.equals(this.shape)
          && otherSA.type == this.type
          && ((otherSA.startTime > this.startTime && otherSA.startTime < this.endTime) ||
          (otherSA.startTime < this.startTime && otherSA.endTime > this.startTime));
    } else {
      return false;
    }
  }

}
