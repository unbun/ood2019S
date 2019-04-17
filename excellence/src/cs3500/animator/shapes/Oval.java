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
 * Represents an oval.
 */
public class Oval extends AShape {

  public Oval(String name) {
    super(name, ShapeClass.OVAL, Constants.DEFAULT_POSN, Constants.DEFAULT_COLOR, 0,
        Constants.DEFAULT_XRADIUS, Constants.DEFAULT_YRADIUS);
  }


  /**
   * Default constructor.
   *
   * @param name the name of the oval
   * @param posn the current position of the oval
   * @param color the color of the oval
   * @param t0 the time that the oval appears in the animation
   * @param xradius the radius in the x-direction of the oval
   * @param yradius the radius in the y-direction of the oval
   */
  public Oval(String name, Posn posn, Color color, int t0, double xradius, double yradius) {
    super(name, ShapeClass.OVAL, posn, color, t0, xradius, yradius);
  }

  @Override
  public double getWidth() {
    return this.width * 2;
  }

  @Override
  public double getHeight() {
    return this.height * 2;
  }

  @Override
  public String shapeSVGSymbol() {
    return "c";
  }

  @Override
  public String printSVG() {
    StringBuilder sb = new StringBuilder();
    sb.append("<ellipse id=\"");
    sb.append(this.getName());
    sb.append("\" cx=\"");
    sb.append((this.getPosn().getX()));
    sb.append("\" cy=\"");
    sb.append((this.getPosn().getY()));
    sb.append("\" rx=\"");
    sb.append((this.getWidth() / 2));
    sb.append("\" ry=\"");
    sb.append((this.getHeight() / 2));
    sb.append(super.printSVG());
    sb.append("</ellipse>\n");
    return sb.toString();
  }

  @Override
  public String xFieldSVG() {
    return "cx";
  }

  @Override
  public String yFieldSVG() {
    return "cy";
  }

  @Override
  public String scaleX() {
    return "rx";
  }

  @Override
  public String scaleY() {
    return "ry";
  }

  @Override
  public String endLineSVG() {
    return "</ellipse>\n";
  }


  @Override
  public IShape currState(int time) {
    Oval o = new Oval(this.name, this.posn, this.color, this.t0, this.width, this.height);
    for (Transform t : this.transformList) {
      if (time >= t.getStartTime() && time < t.getEndTime()) {
        if (t.getType() == TransformType.RESIZE) {
          o.width = this.tweenWidth((Resize) t, o.width, o.width +
              (((Resize) t).getxFactor() / 2), time);
          o.height = this.tweenHeight((Resize) t, o.height, o.height +
              (((Resize) t).getyFactor() / 2), time);
        } else if (t.getType() == TransformType.RECOLOR) {
          o.color = o.tweenColor((Recolor) t, o.color, ((Recolor) t).getColor(), time);
        } else if (t.getType() == TransformType.MOVE) {
          o.posn = o.tweenPosn((MoveTo) t, o.posn, ((MoveTo) t).getDest(), time);
        }
      }
      if (time >= t.getEndTime()) {
        if (t.getType() == TransformType.RESIZE) {
          o.width = this.tweenWidth((Resize) t, o.width, o.width +
              (((Resize) t).getxFactor() / 2), t.getEndTime());
          o.height = this.tweenHeight((Resize) t, o.height, o.height +
              (((Resize) t).getyFactor() / 2), t.getEndTime());
        } else if (t.getType() == TransformType.RECOLOR) {
          o.color = o.tweenColor((Recolor) t, o.color, ((Recolor) t).getColor(),
              t.getEndTime());
        } else if (t.getType() == TransformType.MOVE) {
          o.posn = o.tweenPosn((MoveTo) t, o.posn, ((MoveTo) t).getDest(), t.getEndTime());
        }
      }
    }
    return o;
  }


}