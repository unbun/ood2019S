package cs3500.animator.transforms;

/**
 * Represents an animator for a myShape. TODO: each transform should only access a copy of it's
 * given myShape, and the apply function should return the new myShape
 */
public interface Transform<T> {


  /**
   * If this action is currently running(according to it's own state and the myShape time given). it
   * should apply some action to a myShape.
   */
  T apply(T toMutate, int currTime) throws IllegalArgumentException;


  String textualStr();

  int startTime();

  boolean finished(int currTime);

  boolean started(int currTime);

}
