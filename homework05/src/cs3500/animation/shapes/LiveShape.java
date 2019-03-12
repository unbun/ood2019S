package cs3500.animation.shapes;

import cs3500.animation.utils.Posn;
import cs3500.animation.utils.Utils;
import java.awt.Color;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a Shape that can move/be animated. (In other words a Shape with built-in, trackable
 * mutation).
 */
public abstract class LiveShape {

  private Map<String, Object> originalfields;

  int height;
  int width;
  int heading; //angle in degrees
  Posn posn;
  Color color;
  String name;

  /**
   * Constructs an AbstractShape.
   *
   * @param height the height of the shape
   * @param width the width of the shape
   * @param posn the position of the shape
   * @param color the color of the shape
   * @param name the name of the shape
   */
  public LiveShape(int height, int width, int heading, Posn posn, Color color,
      String name) {
    this.height = (Integer) Utils.requireNonNegative(height, "height");
    this.width = (Integer) Utils.requireNonNegative(width, "width");
    this.heading = heading;
    this.posn = Objects.requireNonNull(posn, "posn cannot be null");
    this.color = Objects.requireNonNull(color, "color cannot be null");
    this.name = Objects.requireNonNull(name, "name cannot be null");

    // to ensure that the original fields are truly original, they need to be created in the
    // constructor. But you can't copy an object (in the traditional sense) in the constructor,
    // so we did it reflexively
    try {
      this.originalfields = Utils.reflexiveCopy(this, 1, "originalfields");
    } catch (IllegalAccessException e) {
      throw new IllegalArgumentException("values are un-parsable");
    }
  }

  ///////////////////////////////////////////
  ////////// String/Output Helpers //////////
  ///////////////////////////////////////////

  /**
   * Returns a string identification of this shape.
   */
  public abstract String getID();


  public String getName() {
    return this.name;
  }

  /**
   * A string containing this Shape's field in the following format: "x y heading width height
   * color.red color.green color.blue"
   *
   * @return a formatted list of this Shape's field
   */
  public String getDescription() {
    return String
        .format("%d %d %d %d %d %d %d %d", this.posn.x, this.posn.y, this.heading, this.width,
            this.height,
            this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }

  ///////////////////////////////
  ////////// MUTATIONS //////////
  ///////////////////////////////

  public void reset() {
    this.height = (int) this.originalfields.get("height");
    this.width = (Integer) this.originalfields.get("width");
//    this.heading = (Integer) this.originalfields.get("heading");
    this.posn = (Posn) this.originalfields.get("posn");
    this.color = (Color) this.originalfields.get("color");
    this.name = (String) this.originalfields.get("name");
  }

  /**
   * Move the shape to the given positoin.
   *
   * @param endPoint the position to move through
   */
  public void moveTo(Posn endPoint) {
    this.posn = Objects.requireNonNull(endPoint);
  }

  /**
   * Turn the shape to head in the given angle.
   *
   * @param endHeading the angle to turn to
   */
  public void turnTo(int endHeading) {
    this.heading = endHeading;
  }

  /**
   * Change the color of the shape to the given color.
   *
   * @param c the color to change to
   */
  public void recolor(Color c) {
    this.color = c;
  }

  /**
   * Change the size of the shape by the given factors.
   *
   * @param xFactor the factor to change the width by
   * @param yFactor the factor to change the height by
   */
  public void scale(double xFactor, double yFactor) {
    this.height *= (Double) Utils.requireNonNegative(yFactor, "yFactor");
    this.width *= (Double) Utils.requireNonNegative(xFactor, "xFactor");
  }

  //////////////////////////////////////
  ////////// OBJECT OVERRIDES //////////
  //////////////////////////////////////

  @Override
  public int hashCode() {
    return Objects.hash(heading, name, width, height, posn.x, posn.y, height,
        color.getRed(), color.getBlue(), color.getGreen());
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof LiveShape) {
      return this.hashCode() == obj.hashCode();
    } else {
      return false;
    }
  }
}
