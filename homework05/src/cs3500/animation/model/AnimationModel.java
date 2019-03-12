package cs3500.animation.model;

import cs3500.animation.actions.Transform;
import cs3500.animation.shapes.LiveShape;
import java.util.ArrayList;

/**
 * A general representation of a model of an animation of shapes. We create to use the
 * text-description model, because it shouldn be the view's job to create and output shapes
 * according to what is happening in the model.
 */
public interface AnimationModel {


  /**
   * Adds the given motions to this animation'shape list of motions and implements them at the
   * corresponding time, if the motions do not conflict with any preexisting motions and if the
   * motions act upon shapes within this model.
   *
   * @param transforms the motions to be added
   * @throws IllegalArgumentException if any of the given motions conflict with another motion in
   * this model or if any motions act upon shapes not present in this model
   */
  void addMotions(Transform... transforms) throws IllegalArgumentException;

  /**
   * Updates the model based on the current time by implementing the motions designated to take
   * place at that time.
   */
  void updateModel();


  /**
   * Resets all of the shapes in the model and restart the timer
   */
  void reset();

  /**
   * Get each shape in it's current state;
   *
   * @return a list of shapes that the model is controlling
   */
  ArrayList<LiveShape> getShapes();

  /**
   * Start the actions. (cannot happen if the timer has been stopped).
   */
  void start() throws IllegalStateException;


  /**
   * Reset the timer (but the timer continues to tick). Reset can happen anytime, even if the timer
   * is stopped.
   */
  void restartTime() throws IllegalStateException;

  /**
   * Stop the actions (once stopped, it cannot be re-started).
   */
  void stop();

  /**
   * Get the current time.
   *
   * @return the current time
   */
  int currTime();
}
