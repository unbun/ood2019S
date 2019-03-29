package cs3500.animator.shapes;

import cs3500.animator.transforms.*;
import cs3500.animator.util.Posn;

import java.awt.*;

/**
 * Represents an oval.
 */
public class Oval extends AShape {

  /**
   * Default constructor.
   *
   * @param name          the name of the oval
   * @param posn          the current position of the oval
   * @param color         the color of the oval
   * @param birthTime    the time that the oval appears in the animation
   * @param deathTime the time that the oval disappears in the animation
   * @param xradius       the radius in the x-direction of the oval
   * @param yradius       the radius in the y-direction of the oval
   */
  public Oval(String name, Posn posn, Color color, int birthTime,
              int deathTime, double xradius, double yradius) {
    super(name, ShapeClass.OVAL, posn, color, birthTime, deathTime, xradius, yradius);
  }

  @Override
  protected void changeWidth(double factor) throws IllegalArgumentException {
    if ((this.width * 2) + factor <= 0) {
      throw new IllegalArgumentException("Width must be positive.");
    } else {
      this.width += factor / 2;
    }
  }

  @Override
  protected void changeHeight(double factor) throws IllegalArgumentException {
    if ((this.height * 2) + factor <= 0) {
      throw new IllegalArgumentException("Height must be positive.");
    } else {
      this.height += factor / 2;
    }
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
  public String printSVG() {
    StringBuilder sb = new StringBuilder();

    sb.append("<ellipse id=\"");
    sb.append(this.getName());
    sb.append("\" cx=\"");
    sb.append(Double.toString(this.getPosn().getX()));
    sb.append("\" cy=\"");
    sb.append(Double.toString(this.getPosn().getY()));
    sb.append("\" rx=\"");
    sb.append(Double.toString(this.getWidth() / 2));
    sb.append("\" ry=\"");
    sb.append(Double.toString(this.getHeight() / 2));

    sb.append(super.printSVG());

    sb.append("</ellipse>\n");
    return sb.toString();
  }


  @Override
  public IShape getStateAt(int time) {

    Oval r = new Oval(this.name, this.posn, this.color, this.birthTime,
            this.deathTime, this.width, this.height);


    for (Transform o : this.transformList) {
      if (o.getType() == TransformType.MOVE) {
        if (time >= o.getStartTime() && time < o.getEndTime()) {
          r.posn = r.tweenPosn((MoveTo) o, r.posn, ((MoveTo) o).getDest(), time);
        }
        if (time >= o.getEndTime()) {
          r.posn = r.tweenPosn((MoveTo) o, r.posn, ((MoveTo) o).getDest(), o.getEndTime());
        }
      }
      if (o.getType() == TransformType.RECOLOR) {
        if (time >= o.getStartTime() && time < o.getEndTime()) {
          r.color = r.tweenColor((Recolor) o, r.color, ((Recolor) o).getColor(), time);
        }
        if (time >= o.getEndTime()) {
          r.color = r.tweenColor((Recolor) o, r.color, ((Recolor) o).getColor(),
                  o.getEndTime());
        }
      }

      if (o.getType() == TransformType.RESIZE) {
        if (time >= o.getStartTime() && time < o.getEndTime()) {
          r.width = this.tweenWidth((Scale) o, r.width, r.width +
                  (((Scale) o).getyFactor() / 2), time);
          r.height = this.tweenHeight((Scale) o, r.height, r.height +
                  (((Scale) o).getyFactor() / 2), time);
        }
        if (time >= o.getEndTime()) {
          r.width = this.tweenWidth((Scale) o, r.width, r.width +
                  (((Scale) o).getyFactor() / 2), o.getEndTime());
          r.height = this.tweenHeight((Scale) o, r.height, r.height +
                  (((Scale) o).getyFactor() / 2), o.getEndTime());
        }
      }
    }
    return r;
  }

  @Override
  public String getSymbol() {
    return "c";
  }

  /**
   * Tween method for a shape's position. Creates a new position based on the input time and
   * destination of the shape after the move operation has been called.
   *
   * @param m          the move operation
   * @param beforePosn the position before the move has occurred
   * @param afterPosn  the destination of the shape
   * @param time       current time
   *
   * @return designated position at time t
   */
  private Posn tweenPosn(MoveTo m, Posn beforePosn, Posn afterPosn, int time) {

    return new Posn((beforePosn.getX() * ((double) (m.getEndTime() - time) /
            (m.getEndTime() - m.getStartTime()))
            + (afterPosn.getX() * ((double) (time - m.getStartTime()) / (m.getEndTime()
            - m.getStartTime())))),

            (beforePosn.getY() * ((double) (m.getEndTime() - time) / (m.getEndTime()
                    - m.getStartTime()))
                    + (afterPosn.getY() * ((double) (time - m.getStartTime()) /
                    (m.getEndTime() - m.getStartTime())))));
  }

  /**
   * Tween method for the color of a shape. Creates a new color based on the input time and desired
   * resultant color after the changeColor operation has been called.
   *
   * @param c           the changeColor operation
   * @param beforeColor the color before the operation
   * @param afterColor  the color after the operation
   * @param time        current time
   *
   * @return designated color at time t
   */
  private Color tweenColor(Recolor c, Color beforeColor, Color afterColor, int time) {

    int tweenedRed = (int)(beforeColor.getRed() * ((float) (c.getEndTime() - time) / (c.getEndTime()
            - c.getStartTime()))
            + (afterColor.getRed() * ((float) (time - c.getStartTime()) /
            (c.getEndTime() - c.getStartTime())))) % 256;

    int tweenedGreen = (int)(beforeColor.getGreen() * ((float) (c.getEndTime() - time) / (c.getEndTime()
            - c.getStartTime()))
            + (afterColor.getGreen() * ((float) (time - c.getStartTime())
            / (c.getEndTime() - c.getStartTime())))) % 256;

    int tweenedBlue = (int)(beforeColor.getBlue() * ((float) (c.getEndTime() - time) / (c.getEndTime()
            - c.getStartTime()))
            + (afterColor.getBlue() * ((float) (time - c.getStartTime())
            / (c.getEndTime() - c.getStartTime())))) % 256;


    return new Color(tweenedRed, tweenedGreen, tweenedBlue);
  }

  /**
   * Tween method for the width of a shape. Creates a new width based on the input time and desired
   * width after the scale operation has been called.
   *
   * @param s           the scale operation
   * @param beforeWidth the width before the operation
   * @param afterWidth  the width after the operation
   * @param time        the current time
   *
   * @return designated width at time t
   */
  private double tweenWidth(Scale s, double beforeWidth, double afterWidth, int time) {
    return (beforeWidth * ((double) (s.getEndTime() - time) / (s.getEndTime() - s.getStartTime()))
            + (afterWidth * ((double) (time - s.getStartTime()) / (s.getEndTime()
            - s.getStartTime()))));
  }

  /**
   * Tween method for the height of a shape. Creates a new height based on the input time and
   * desired height after the scale operation has been called.
   *
   * @param s            the scale operation
   * @param beforeHeight the width before the operation
   * @param afterHeight  the width after the operation
   * @param time         the current time
   *
   * @return designated width at time t
   */
  private double tweenHeight(Scale s, double beforeHeight, double afterHeight, int time) {
    return (beforeHeight * ((double) (s.getEndTime() - time) / (s.getEndTime() - s.getStartTime()))
            + (afterHeight * ((double) (time - s.getStartTime()) / (s.getEndTime()
            - s.getStartTime()))));
  }
}