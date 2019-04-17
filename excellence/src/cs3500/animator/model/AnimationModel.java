package cs3500.animator.model;

import cs3500.animator.shapes.IShape;
import cs3500.animator.transforms.Transform;
import java.util.ArrayList;
import java.util.List;

/**
 * A model of {@code IShapes}. The Animation has a list of IShapes, and sets global values (like
 * tick rate, canvas size, etc.) The model also keeps track of the state the list of shapes, the
 * transforms of those shapes, and the keyframes created by those transforming shapes.
 */
public interface AnimationModel {


  /**
   * Returns a copy of all the shapes in the animation.
   *
   * @return a copy of the shapes in the animation
   */
  ArrayList<IShape> getShapes();

  /**
   * Returns tick in model.
   *
   * @return tick.
   */
  int getTickRate();

  /**
   * Returns the end time of the animation, by checking for the last operation or shape exit times.
   *
   * @return the end time of the animation
   */
  int getEndTime();

  /**
   * Returns the height of the animation canvas.
   */
  int getHeight();

  /**
   * Returns the width of the animation canvas.
   */
  int getWidth();

  /**
   * Returns this model's list of transformations.
   */
  List<Transform> getTransforms();

  /**
   * Creates an initial shape object in the animation, from where it can be transformed based on
   * user input.
   *
   * @param shape the shape to be added
   */
  void createShape(IShape shape);

  /**
   * Removes a shape from the animation.
   *
   * @param shape the shape to be removed
   * @throws IllegalArgumentException if there is no shape to remove
   */
  void removeShape(IShape shape) throws IllegalArgumentException;

  /**
   * Creates operation in list in model.
   *
   * @param trns operation.
   */
  void createTransform(Transform trns);

  /**
   * Create a KeyFrame from the given details.
   *
   * @param name the name of the shape to update
   * @param t the time to insert the keyframe
   * @param x the x posn to put the shape at
   * @param y the y posn to put the shape at
   * @param w the width of the shape
   * @param h the height of the shape
   * @param r the red value of the shape
   * @param g the green value of the shape
   * @param b the blue value of the shape
   */
  void createKeyFrame(String name, int t, int x, int y, int w, int h, int r, int g, int b);

  /**
   * Remove the key frame of the given shape at the given time.
   *
   * @param name the shape to remove the keyframe of
   * @param t the time to remove it at
   */
  void removeKeyFrame(String name, int t);

  /**
   * Sets the speed of the model to the given speed.
   *
   * @param speed speed.
   */
  void setSpeed(int speed);

  /**
   * Specify the bounding box to be used for this model.
   *
   * @param x The leftmost x value
   * @param y The topmost y value
   * @param width The width of the bounding box
   * @param height The height of the bounding box
   */
  void setBounds(int x, int y, int width, int height);

}