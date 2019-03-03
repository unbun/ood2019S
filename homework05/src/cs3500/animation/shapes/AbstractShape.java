package cs3500.animation.shapes;

import cs3500.animation.utils.Posn;
import cs3500.animation.utils.Utils;
import java.awt.Color;
import java.util.Objects;

/**
 * A general representation of a shape.
 */
public abstract class AbstractShape {

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
  public AbstractShape(int height, int width, int heading, Posn posn, Color color, String name) {
    this.height = (Integer) Utils.requireNonNegative(height, "height");
    this.width = (Integer) Utils.requireNonNegative(width, "width");
    this.heading = heading;
    this.posn = Objects.requireNonNull(posn, "posn cannot be null");
    this.color = Objects.requireNonNull(color, "color cannot be null");
    this.name = Objects.requireNonNull(name, "name cannot be null");
  }


  /**
   * Returns a string representation of this shape.
   */
  public abstract String shapeToString();

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


  @Override
  public int hashCode() {
    return Objects.hash(heading, name, width, height, posn.x, posn.y, height,
        color.getRed(), color.getBlue(), color.getGreen());
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof AbstractShape) {
      return this.hashCode() == obj.hashCode();
    } else {
      return false;
    }
  }
}



