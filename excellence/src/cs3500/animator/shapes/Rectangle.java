package cs3500.animator.shapes;


import cs3500.animator.transforms.MoveTo;
import cs3500.animator.transforms.Recolor;
import cs3500.animator.transforms.Resize;
import cs3500.animator.transforms.Transform;
import cs3500.animator.transforms.TransformType;
import cs3500.animator.util.Constants;
import cs3500.animator.util.Posn;
import java.awt.Color;

/**
 * Represents a rectangle.
 */
public class Rectangle extends AShape {

  public Rectangle(String name) {
    super(name, ShapeClass.RECT, Constants.DEFAULT_POSN, Constants.DEFAULT_COLOR, 0,
        Constants.DEFAULT_XRADIUS, Constants.DEFAULT_YRADIUS);
  }

  /**
   * Default constructor for a rectangle.
   *
   * @param name the name of the rectangle
   * @param posn the current position of the rectangle
   * @param color the initial color of the rectangle
   * @param t0 the time that the rectangle appears in the animation
   * @param width the width of a rectangle
   * @param height the height of a rectangle
   */
  public Rectangle(String name, Posn posn, Color color,
      int t0, double width, double height) {
    super(name, ShapeClass.RECT, posn, color, t0, width, height);
  }

  // no additional symbol is needed to represent a rectangle in svg format
  @Override
  public String shapeSVGSymbol() {
    return "";
  }

  @Override
  public String printSVG() {
    StringBuilder sb = new StringBuilder();
    sb.append("<rect id=\"");
    sb.append(this.getName());
    sb.append("\" x=\"");
    sb.append((this.getPosn().getX()));
    sb.append("\" y=\"");
    sb.append((this.getPosn().getY()));
    sb.append("\" width=\"");
    sb.append((this.getWidth()));
    sb.append("\" height=\"");
    sb.append((this.getHeight()));
    sb.append(super.printSVG());
    sb.append("</rect>\n");
    return sb.toString();
  }

  @Override
  public String xFieldSVG() {
    return "x";
  }

  @Override
  public String yFieldSVG() {
    return "y";
  }

  @Override
  public String scaleX() {
    return "width";
  }

  @Override
  public String scaleY() {
    return "height";
  }

  @Override
  public String endLineSVG() {
    return "</rect>\n";
  }

  @Override
  public IShape currState(int time) {
    Rectangle r = new Rectangle(this.name, this.posn, this.color, this.t0, this.width, this.height);
    for (Transform t : this.transformList) {
      if (time >= t.getStartTime() && time < t.getEndTime()) {
        if (t.getType() == TransformType.RESIZE) {
          r.width = this.tweenWidth((Resize) t, r.width, r.width +
              (((Resize) t).getxFactor() / 2), time);
          r.height = this.tweenHeight((Resize) t, r.height, r.height +
              (((Resize) t).getyFactor() / 2), time);
        } else if (t.getType() == TransformType.RECOLOR) {
          r.color = r.tweenColor((Recolor) t, r.color, ((Recolor) t).getColor(), time);
        } else if (t.getType() == TransformType.MOVE) {
          r.posn = r.tweenPosn((MoveTo) t, r.posn, ((MoveTo) t).getDest(), time);
        }
      }
      if (time >= t.getEndTime()) {
        if (t.getType() == TransformType.RESIZE) {
          r.width = this.tweenWidth((Resize) t, r.width, r.width +
              (((Resize) t).getxFactor() / 2), t.getEndTime());
          r.height = this.tweenHeight((Resize) t, r.height, r.height +
              (((Resize) t).getyFactor() / 2), t.getEndTime());
        } else if (t.getType() == TransformType.RECOLOR) {
          r.color = r.tweenColor((Recolor) t, r.color, ((Recolor) t).getColor(),
              t.getEndTime());
        } else if (t.getType() == TransformType.MOVE) {
          r.posn = r.tweenPosn((MoveTo) t, r.posn, ((MoveTo) t).getDest(), t.getEndTime());
        }
      }
    }
    return r;
  }


}