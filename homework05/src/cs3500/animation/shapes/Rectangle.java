package cs3500.animation.shapes;

import cs3500.animation.utils.Posn;
import java.awt.Color;

/**
 * Represents a rectangle.
 */
public final class Rectangle extends LiveShape {

  /**
   * Copy Constructor for Rectangle
   */
  public Rectangle(Rectangle copy) {
    super(copy.height, copy.width, copy.heading, copy.posn, copy.color, copy.name);
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
  public Rectangle(int height, int width, int heading, int x, int y, Color color, String name) {
    super(height, width, heading, new Posn(x, y), color, name);
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
  public Rectangle(int height, int width, int heading, Posn posn, Color color, String name) {
    super(height, width, heading, posn, color, name);
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
    if (this == obj) {
      return true;
    } else if (obj instanceof Rectangle) {
      return this.hashCode() == ((Rectangle) obj).hashCode();
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode() + 31 * "rectangle".hashCode();
  }

}
