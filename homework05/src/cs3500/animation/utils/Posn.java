package cs3500.animation.utils;

import java.util.Objects;

/**
 * Represents a two-dimensional point in space.
 */
public class Posn {

  public int x;
  public int y;


  /**
   * Constructs a Posn with the given x and y coordinates.
   *
   * @param x the x-coordinate
   * @param y the y-coordinate
   */
  public Posn(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (obj instanceof Posn) {
      return this.x == ((Posn) obj).x && this.y == ((Posn) obj).y;
    } else {
      return false;
    }
  }
}
