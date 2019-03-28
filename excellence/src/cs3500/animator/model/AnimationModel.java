package cs3500.animator.model;

import java.util.ArrayList;

import cs3500.animator.shapes.IShape;
import cs3500.animator.transforms.Transform;

/**
 *
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

  int getHeight();

  int getWidth();

  /**
   * Creates an initial shape object in the animation, from where it can perform different actions
   * based on user input.
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
   * Sets speed, which is an int in model.
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