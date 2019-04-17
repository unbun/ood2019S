package cs3500.animator.util;

import java.util.Objects;

/**
 * Represents a two-dimensional point in space.
 */
public class Posn {

  public double x;
  public double y;


  /**
   * Constructs a Posn with the given x and y coordinates.
   *
   * @param x the x-coordinate
   * @param y the y-coordinate
   */
  public Posn(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  /**
   * Returns a textual description of a Posn.
   */
  public String getDescription() {
    StringBuilder sb = new StringBuilder();
    sb.append("(");
    sb.append(this.getX());
    sb.append(",");
    sb.append(this.getY());
    sb.append(")");

    return sb.toString();
  }

  @Override
  public String toString() {
    return String.format("{x=%f, y=%f}", x, y);
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
