package cs3500.animator.shapes;

import cs3500.animator.transforms.Transform;
import cs3500.animator.util.Posn;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents a Shape that can compute its own time-based movements and alterations.
 */
public interface IShape {

  /**
   * Getter for the name of the shape.
   *
   * @return the name of this shape
   */
  String getName();

  /**
   * Getter for the type of the shape.
   *
   * @return the type of this shape
   */
  String getType();

  /**
   * Getter for the position of the shape.
   *
   * @return the position of this shape
   */
  Posn getPosn();

  /**
   * Getter for the color of the shape.
   *
   * @return the color of this shape
   */
  Color getColor();

  /**
   * Getter for the appearance time of the shape.
   *
   * @return the appearance time of this shape
   */
  int gett0();

  /**
   * Getter for the list of transformations on the shape.
   *
   * @return the list of transformations for this shape
   */
  ArrayList<Transform> getTransformations();

  /**
   * Getter for the width of the shape.
   *
   * @return the width of this shape
   */
  double getWidth();

  /**
   * Getter for the height of the shape.
   *
   * @return the height of this shape
   */
  double getHeight();

  /**
   * Getter svg.
   *
   * @return svg.
   */
  String printSVG();

  /**
   * Return state at time.
   *
   * @param t time.
   * @return state at time.
   */
  IShape currState(int t);

  /**
   * Getting type of shape, either c or r, used for moving shape and formatting SVG.
   *
   * @return either "c" or "r"
   */
  String shapeSVGSymbol();

}