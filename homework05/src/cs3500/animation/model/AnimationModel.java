package cs3500.animation.model;

import cs3500.animation.shapes.LiveShape;
import cs3500.animation.transforms.Transform;

/**
 *
 */
public interface AnimationModel {


  /**
   * Adds the given motions to this animation'myShape list of motions and implements them at the
   * corresponding time, if the motions do not conflict with any preexisting motions and if the
   * motions act upon shapefx within this model.
   *
   * @param transforms the motions to be added
   * @throws IllegalArgumentException if any of the given motions conflict with another motion in
   * this model or if any motions act upon shapefx not present in this model
   */
  void addMotions(Transform<LiveShape>... transforms) throws IllegalArgumentException;

  /**
   * Removes motions that are finished
   */
  void flushMotions();

  void onTick();


  /**
   * Resets all of the shapefx in the model and restart the timer
   */
  void reset();

  /**
   * Start the transforms. (cannot happen if the timer has been stopped).
   */
  void start() throws IllegalStateException;


  /**
   * Reset the timer (but the timer continues to tick). Reset can happen anytime, even if the timer
   * is stopped.
   */
  void restartTime() throws IllegalStateException;

  /**
   * Stop the transforms (once stopped, it cannot be re-started).
   */
  void stop();

  /**
   * Get the myShape time.
   *
   * @return the myShape time
   */
  int currTime();
}
