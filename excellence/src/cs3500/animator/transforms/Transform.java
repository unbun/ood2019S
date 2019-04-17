package cs3500.animator.transforms;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.shapes.AShape;
import cs3500.animator.shapes.IShape;


/**
 * A transformation that Shape can go through to alter some aspect of it and it's representation.
 */
public interface Transform {

  /**
   * Adds the specific transformation to the given shape's list of transformations if it is valid
   * for that shape.
   *
   * @param shape the shape to be operated on
   * @throws IllegalArgumentException if transformation happens at invalid times
   */
  void apply(IShape shape) throws IllegalArgumentException;

  /**
   * Gets text description of a transformation on a shape.
   *
   * @return a description of a transformation on a shape
   */
  String toText(AnimationModel model);

  /**
   * Returns this transformation in svg format.
   *
   * @param s the shape being transformed
   */
  String commandToSVG(AShape s);

  /**
   * Returns start time.
   *
   * @return time.
   */
  int getStartTime();

  /**
   * Returns end time.
   *
   * @return end time.
   */
  int getEndTime();

  /**
   * Name of transformation.
   *
   * @return name of transformation.
   */
  String getName();

  /**
   * Type of transformation.
   *
   * @return type of transformation.
   */
  TransformType getType();
}