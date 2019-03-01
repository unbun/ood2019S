package cs3500.animation.shapes;

import cs3500.animation.utils.Posn;
import java.awt.Color;

/**
 * Represents a rectangle.
 */
public class Rectangle extends LiveShape {

  public Rectangle(int height, int width, int heading, int x, int y, Color color, String name) {
    super(height, width, heading, new Posn(x,y), color, name);
  }

  public Rectangle(int height, int width, int heading, Posn posn, Color color, String name) {
    super(height, width, heading, posn, color, name);
  }

  @Override
  public String shapeToString() {
    return name + " rectangle";
  }

  @Override
  public boolean equals(Object obj) {
    if(this == obj){
      return true;
    } else if(obj instanceof Rectangle) {
      return this.hashCode() == ((Rectangle)obj).hashCode();
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode() + 31* "rectangle".hashCode();
  }
}
