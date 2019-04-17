package cs3500.animator.provider.model;

import cs3500.animator.shapes.IShape;
import java.util.ArrayList;

/**
 * Interface for models that need to be added upon. On any model implementing this class, they will
 * have the ability to access the getters from ReadOnlyModel, but will also be able to add new
 * shapes and new animations by calling the methods below.
 *
 * <p>RENAMED to from 'AnimationModel' to 'PAnimationModel' to make things easier on our sleep
 * -deprived minds.
 */
public interface PAnimatorModel extends ReadOnlyModel {

  /**
   * Add a motion to a given shape within this model by specifying the beginning and end time and
   * state of the given shape. AKeyFrames are constructed from all the start values and all the end
   * values for this motion, and are then passed to a helper to add to the correct shape's list of
   * KeyFrames. If either the beginning or end AKeyFrames you create already exist within the
   * specified shape's list, then nothing will happen. If the two AKeyFrame's values are the same,
   * nothing happens. If no AKeyFrame exists that has the same tick as either of the two created,
   * then add it to the shape's list of AKeyFrames.
   *
   * @param shapeName the name of the shape you want to add motion to
   * @param t1 the start tick of this animation
   * @param x1 the x value of this shape at t1
   * @param y1 the y value of this shape at t1
   * @param w1 the width value of this shape at t1
   * @param h1 the height value of this shape at t1
   * @param r1 the red value of this shape at t1
   * @param g1 the green value of this shape at t1
   * @param b1 the blue value of this shape at t1
   * @param t2 the end tick of this animation
   * @param x2 the x value of this shape at t2
   * @param y2 the y value of this shape at t2
   * @param w2 the width value of this shape at t2
   * @param h2 the height value of this shape at t2
   * @param r2 the red value of this shape at t2
   * @param g2 the green value of this shape at t2
   * @param b2 the blue value of this shape at t2
   * @throws IllegalArgumentException if you look for a shape that does not exist in this model
   * @throws IllegalArgumentException if the starting time is greater than the end time or either
   *                                  time is less than 1
   * @throws IllegalArgumentException if either the height or the width of the new or old shape
   *                                  states are less than or equal to 0
   */
  void addAMotion(String shapeName, int t1, int x1, int y1, int w1, int h1, int r1, int g1,
      int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2);

  /**
   * Create a shape and put it in this model with an initially empty list of shapes, and the given
   * name and type.
   *
   * @param shapeName the desired name of this shape
   * @param shapeType the desired type for this shape (currently only rectangle and ellipse)
   * @throws IllegalArgumentException if the shape type is not an existing shape type
   * @throws IllegalArgumentException if the given shape name is already assigned to another shape
   *                                  in this model
   */
  void declareAShape(String shapeName, String shapeType);

  /**
   * Initializes the values for the dimensions of this model's canvas from their original -1 value.
   *
   * @param x the desired x value for the top left corner of the canvas
   * @param y the desired y value for the top left corner of the canvas
   * @param width the desired width for the canvas
   * @param height the desired height for the canvas
   */
  void initCanvas(int x, int y, int width, int height);

  /**
   * Remove the shape with the given name from this model's list of shapes. Subsequently delete's
   * all that shape's AKeyFrames.
   *
   * @param shapeName the name of the shape to remove
   * @throws IllegalArgumentException if the shape you are trying to remove does not exist
   */
  void removeAShape(String shapeName);

  /**
   * Fills in the gaps in time between all the AKeyFrames in every shape from this model's list of
   * shapes. "Tweens" the values in between the existing ticks to figure out what a shape will look
   * like at the missing tick times. Inserts the missing ticks.
   *
   * <p>CHANGE LOG: changed the ArrayList's Generic to be an IShape rather than a Shape. (allowed
   * from users).
   */
  ArrayList<IShape> fillAllFrames();


}
