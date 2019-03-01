package cs3500.animation.shapes;
import cs3500.animation.utils.Posn;
import java.awt.Color;

/**
 * Represents an oval.
 */
public class Oval extends LiveShape {

  public Oval(int height, int width, int heading, int x, int y, Color color, String name) {
    super(height, width, heading, new Posn(x,y), color, name);
  }

  public Oval(int height, int width, int heading, Posn posn, Color color, String name) {
    super(height, width, heading, posn, color, name);
  }

  @Override
  public String shapeToString() {
    return name + " oval";
  }

  @Override
  public boolean equals(Object obj) {
    if(this == obj){
      return true;
    } else if(obj instanceof Oval) {
      return this.hashCode() == ((Oval)obj).hashCode();
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode() + 31 * "oval".hashCode();
  }
}

