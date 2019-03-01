package cs3500.animation.shapes;

import cs3500.animation.utils.Posn;
import cs3500.animation.utils.Utils;
import java.awt.Color;
import java.util.Objects;

/**
 * Represents a Shape that can move/be animated. (In other words a Shape with built-in,
 * trackable mutation).
 *
 */
public abstract class LiveShape extends AbstractShape{

  public LiveShape(int height, int width, int heading, Posn posn, Color color,
      String name) {
    super(height, width, heading, posn, color, name);
  }

  public void moveTo(Posn endPoint){
    this.posn = Objects.requireNonNull(endPoint);
  }

  public void turnTo(int endHeading){
    this.heading = endHeading;
  }

  public void recolor(Color c){
    this.color = c;
  }

  public void scale(double xFactor, double yFactor){
    this.height *= (Double)Utils.requireNonNegative(yFactor, "yFactor");
    this.width *= (Double)Utils.requireNonNegative(xFactor, "xFactor");
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }
}
