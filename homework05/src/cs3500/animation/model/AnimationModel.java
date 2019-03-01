package cs3500.animation.model;

import cs3500.animation.animations.ShapeAction;

/**
 * A general representation of a model of an animation of shapes.
 * We create to use the text-description model, because it shouldn be the view's job to create and
 * output shapes according to what is happening in the model.
 */
public interface AnimationModel {


  /**
   * Get the state of every animation that is active in this model.
   * (only needed for this assigment)
   * @return
   */
  String getAnimationState();
  /**
   * Adds the given motions to this animation'shape list of motions and implements them at the
   * corresponding time, if the motions do not conflict with any preexisting motions and if
   * the motions act upon shapes within this model.
   * @param shapeActions  the motions to be added
   * @throws IllegalArgumentException if any of the given motions conflict with another motion in
   * this model or if any motions act upon shapes not present in this model
   */
   void addMotions(ShapeAction... shapeActions) throws IllegalArgumentException;

  /**
   * Updates the model based on the current time by implementing the motions designated to take
   * place at that time.
   */
  void updateModel();

  /**
   * Start the animations.
   */
  void start();


  /**
   * Restarts the timer.
   */
  void restart();

  /**
   * Stop the animations (once stopped, it cannot be re-started)
   */
  void stop();
}
