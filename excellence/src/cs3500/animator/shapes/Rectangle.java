package cs3500.animator.shapes;

import cs3500.animator.util.Posn;
import java.awt.Color;

/**
 * Represents a rectangle.
 */
public final class Rectangle extends LiveShape {

  /**
   * Copy Constructor for Rectangle
   */
  public Rectangle(Rectangle copy) {
    super(copy);
  }

  /**
   * A Movable Rectangle.
   *
   * @param height height of rectangle
   * @param width width of rectangle
   * @param heading angle the myShape is heading
   * @param x x of the position of the myShape
   * @param y y of the positoin of the myShape
   * @param color the color
   * @param name the name/id
   */
  public Rectangle(int currTime, int height, int width, int heading, int x, int y, Color color,
      String name) {
    super(currTime, height, width, heading, new Posn(x, y), color, name);
  }

  /**
   * A Movable Rectangle.
   *
   * @param height height of rectangle
   * @param width width of rectangle
   * @param heading angle the oval is heading
   * @param posn the position of the rectangle
   * @param color the color
   * @param name the name/id
   */
  public Rectangle(int currTime, int height, int width, int heading, Posn posn, Color color,
      String name) {
    super(currTime, height, width, heading, posn, color, name);
  }

  @Override
  public LiveShape copy() {
    return new Rectangle(this);
  }

  @Override
  public String getID() {
    return name + " rectangle";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Rectangle) {
      return super.equals(obj);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode() + 31 * "rectangle".hashCode();
  }

  @Override
  public String toString() {
    return "Rectangle@[" + super.toString() + "]";
  }

}
