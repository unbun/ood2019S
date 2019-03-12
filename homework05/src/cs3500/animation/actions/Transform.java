package cs3500.animation.actions;

import cs3500.animation.shapes.LiveShape;

/**
 * Represents an animation for a shape. TODO: each transform should only access a copy of it's given
 * shape, and the apply function should return the new shape
 */
public interface Transform {

  /**
   * If this action is currently running(according to it's own state and the current time given). it
   * should apply some action to a shape.
   */
  void apply(int currTime);

  /**
   * Is this action finished? (according to the given time).
   *
   * @param currTime the current time
   */
  boolean finished(int currTime);

  /**
   * Has this aciton started? (according to the given time).
   */
  boolean started(int currTime);

  /**
   * A pseudo-replacement for {@code equals()} that only checks if this action conflicts with the
   * other one in terms of what it is animating.
   */
  boolean conflict(Transform other);

  /**
   * Get the state of this action if it is currentlsty running (according to the given time).
   */
  String stateString(int currTime);

  /**
   * Get the shape of the shape in this shape tranformation
   * @return the shape of the transformation
   */
  LiveShape getShape();

}
