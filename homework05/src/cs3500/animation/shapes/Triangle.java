package cs3500.animation.shapes;

import cs3500.animation.utils.Posn;
import java.awt.Color;

/**
 * Represents a triangle.
 */
public final class Triangle extends LiveShape {

  /**
   * Copy Constructor for Oval
   */
  public Triangle(Triangle copy) {
    super(copy.height, copy.width, copy.heading, copy.posn, copy.color, copy.name);
  }

  /**
   * A Movable Triangle.
   *
   * @param height height of Triangle
   * @param width width of Triangle
   * @param heading angle the myShape is heading
   * @param x x of the position of the myShape
   * @param y y of the positoin of the myShape
   * @param color the color
   * @param name the name/id
   */
  public Triangle(int height, int width, int heading, int x, int y, Color color, String name) {
    super(height, width, heading, new Posn(x, y), color, name);
  }

  /**
   * A Movable Triangle.
   *
   * @param height height of triangle
   * @param width width of triangle
   * @param heading angle the triangle is heading
   * @param posn the position of the triangle
   * @param color the color
   * @param name the name/id
   */
  public Triangle(int height, int width, int heading, Posn posn, Color color, String name) {
    super(height, width, heading, posn, color, name);
  }


  @Override
  public LiveShape copy() {
    return new Triangle(this);
  }

  @Override
  public String getID() {
    return name + " triangle";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (obj instanceof Triangle) {
      return this.hashCode() == ((Triangle) obj).hashCode();
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode() + 31 * "triangle".hashCode();
  }

}

