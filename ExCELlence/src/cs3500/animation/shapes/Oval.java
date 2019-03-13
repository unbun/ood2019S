package cs3500.animation.shapes;

import cs3500.animation.utils.Posn;
import java.awt.Color;

/**
 * Represents an oval.
 */
public final class Oval extends LiveShape {


  /**
   * Copy Constructor for Oval
   */
  public Oval(Oval copy) {
    super(copy.height, copy.width, copy.heading, copy.posn, copy.color, copy.name);
  }

  /**
   * A Movable Oval.
   *
   * @param height height of oval
   * @param width width of oval
   * @param heading angle the myShape is heading
   * @param x x of the position of the myShape
   * @param y y of the positoin of the myShape
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
  public LiveShape copy() {
    return new Oval(this);
  }

  @Override
  public String getID() {
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

