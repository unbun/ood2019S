package cs3500.animator.transforms;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.shapes.IShape;

/**
 * An interface representing the operations available to animation operations, which implement this
 * interface through extending the abstract class AOperation.
 */
public interface Transform {
  //void apply(AShape shape);

  /**
   * Stores the specific operation to the given shape.
   *
   * @param shape the shape to be operated on
   *
   * @throws IllegalArgumentException if operation happens at invalid times
   */
  void apply(IShape shape) throws IllegalArgumentException;

  /**
   * Gets description of operation on shape.
   *
   * @return a description of an operation on a shape
   *
   * @throws IllegalArgumentException if there is no shape added to the operation
   */
  String getDescription(AnimationModel model) throws IllegalArgumentException;

  /**
   * Returns from time.
   *
   * @return time.
   */
  int getStartTime();

  /**
   * Returns to time.
   *
   * @return to time.
   */
  int getEndTime();

  /**
   * Returns formatted svg.
   *
   * @return svg.
   */
  String printSVG();

  /**
   * Name of op.
   *
   * @return name of op.
   */
  String getName();

  TransformType getType();
}