package cs3500.animation.animations;


/**
 * Represents an animation for a shape
 */
public interface ShapeAction {

  /**
   * If this action is currently running(according to it's own state and the current time given).
   * it should apply some action to a shape
   * @param currTime
   */
  void apply(int currTime);

  /**
   * Is this action finished? (according to the given time)
   * @param currTime the current time
   * @return
   */
  boolean finished(int currTime);

  /**
   * Has this aciton started? (according to the given time)
   * @param currTime
   * @return
   */
  boolean started(int currTime);

  /**
   * A pseudo-replacement for {@code equals()} that only checks if this action conflicts with the
   * other one in terms of what it is animating.
   * @param other
   * @return
   */
  boolean conflict(ShapeAction other);

  /**
   * Get the state of this action if it is currently running (according to the given time)
   * @param currTime
   * @return
   */
  String stateString(int currTime);

  //

  /**
   * Get the start time of this action. NOTE: This is just for this assignment, so that the model only
   * updates animations once, and the animation description is cleaner.
   * @return
   */
  int getStartTime();

}
