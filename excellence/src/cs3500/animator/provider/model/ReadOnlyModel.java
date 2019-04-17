package cs3500.animator.provider.model;

import cs3500.animator.shapes.IShape;
import java.util.ArrayList;

/**
 * Interface that provides getter methods for models. Any implementing classes will be able to
 * access information from within this model, but will not be able to change or mutate anything.
 */
public interface ReadOnlyModel {

  /**
   * Getter method to get a model's list of shapes.
   *
   * @return this model's list of shapes
   */
  ArrayList<IShape> getShapes();

  /**
   * Calculates the whole width of the canvas. The canvas is stored in the model as having a
   * position, and dimensions. This method will get the total canvas width by subtracting the x
   * value from the width.
   *
   * @return the width dimension of the visual view panel
   */
  int getWholeCanvasWidth();

  /**
   * Calculates the whole height of the canvas. The canvas is stored in the model as having a
   * position, and dimensions. This method will get the total canvas width by subtracting the y
   * value from the height.
   *
   * @return the height dimension of the visual view panel
   */
  int getWholeCanvasHeight();

  /**
   * Find the first tick that any shape starts at within this model by collecting a list of all the
   * beginning ticks from all the shapes in this model, and finding the lowest tick value.
   *
   * @return the first tick that an animation/shape occurs in this model
   */
  int findBeginningTime();

  /**
   * Find the last tick that any shape ends at to determine when this model has no more animations
   * left to perform by collecting a list of all the final tick values for each shape in this model.
   * Then returns the largest tick value in the list.
   *
   * @return the final tick for this model
   */
  int findEndTime();

  //SIMPLE GETTERS////////////////////////////////////////////////////////

  /**
   * returns the x-position of the canvas.
   *
   * @return the Canvas X.
   */
  int getCanX();

  /**
   * returns the y-position of the canvas.
   *
   * @return the Canvas Y.
   */
  int getCanY();

  /**
   * returns the width of the canvas.
   *
   * @return the Canvas width.
   */
  int getCanWidth();

  /**
   * returns the height of the canvas.
   *
   * @return the Canvas Height.
   */
  int getCanHeight();
}
