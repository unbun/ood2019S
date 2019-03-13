package cs3500.animation.transforms;

/**
 * Represents an animation for a myShape. TODO: each transform should only access a copy of it's
 * given myShape, and the apply function should return the new myShape
 */
public interface Transform<T> {

  /**
   * If this action is currently running(according to it's own state and the myShape time given). it
   * should apply some action to a myShape.
   */
  T apply(int currTime);

  /**
   * Is this action finished? (according to the given time).
   *
   * @param currTime the myShape time
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

  T getCopy();

  String textualView();

}
