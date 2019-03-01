package cs3500.animation.shapes;
import cs3500.animation.utils.Posn;
import java.awt.Color;
import java.util.Objects;

/**
 * Represents a triangle.
 */
public class Triangle extends LiveShape {

  public Triangle(int height, int width, int heading, int x, int y, Color color, String name) {
    super(height, width, heading, new Posn(x,y), color, name);
  }

  public Triangle(int height, int width, int heading, Posn posn, Color color, String name) {
    super(height, width, heading, posn, color, name);
  }

  @Override
  public String shapeToString() {
    return name + " triangle" ;
  }

  @Override
  public boolean equals(Object obj) {
    if(this == obj){
      return true;
    } else if(obj instanceof Triangle) {
      return this.hashCode() == ((Triangle)obj).hashCode();
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode() + 31* "triangle".hashCode();
  }
}

