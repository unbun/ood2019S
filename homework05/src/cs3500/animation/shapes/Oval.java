package cs3500.animation.shapes;

import cs3500.animation.utils.Posn;
import java.awt.Color;

/**
 * Represents an oval.
 */
public class Oval extends LiveShape {

  /**
   * A Movable Oval.
   *
   * @param height height of oval
   * @param width width of oval
   * @param heading angle the shape is heading
   * @param x x of the position of the shape
   * @param y y of the positoin of the shape
   * @param color the color
   * @param name the name/id
   */
  public Oval(int height, int width, int heading, int x, int y, Color color, String name) {
    super(height, width, heading, new Posn(x, y), color, name);
  }

  /**
   * A Movable Oval.
   *
   * @param height height of oval
   * @param width width of oval
   * @param heading angle the oval is heading
   * @param posn the position of the oval
   * @param color the color
   * @param name the name/id
   */
  public Oval(int height, int width, int heading, Posn posn, Color color, String name) {
    super(height, width, heading, posn, color, name);
  }

  @Override
  public String shapeToString() {
    return name + " oval";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (obj instanceof Oval) {
      return this.hashCode() == ((Oval) obj).hashCode();
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode() + 31 * "oval".hashCode();
  }
}

