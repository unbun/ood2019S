package cs3500.animator.shapes;


import cs3500.animator.transforms.MoveTo;
import cs3500.animator.transforms.Recolor;
import cs3500.animator.transforms.Scale;
import cs3500.animator.transforms.Transform;
import cs3500.animator.transforms.TransformType;
import cs3500.animator.util.Posn;
import java.awt.Color;

/**
 * Represents a rectangle.
 */
public class Rectangle extends AShape {

  /**
   * Default constructor for a rectangle.
   *
   * @param name the name of the rectangle
   * @param posn the current position of the rectangle
   * @param color the initial color of the rectangle
   * @param birthTime the time that the rectangle appears in the animation
   * @param deathTime the time that the rectangle disappears in the animation
   * @param width the width of a rectangle
   * @param height the height of a rectangle
   */
  public Rectangle(String name, Posn posn, Color color,
      int birthTime, int deathTime, double width, double height) {
    super(name, ShapeClass.RECT, posn, color, birthTime, deathTime, width, height);
  }

  @Override
  protected void changeWidth(double factor) throws IllegalArgumentException {
    if (this.width + factor <= 0) {
      throw new IllegalArgumentException("Width must be positive.");
    } else {
      this.width += factor;
    }
  }

  @Override
  protected void changeHeight(double factor) throws IllegalArgumentException {
    if (this.height + factor <= 0) {
      throw new IllegalArgumentException("Height must be positive.");
    } else {
      this.height += factor;
    }
  }

  @Override
  public String printSVG() {
    StringBuilder sb = new StringBuilder();

    sb.append("<rect id=\"");
    sb.append(this.getName());
    sb.append("\" x=\"");
    sb.append(Double.toString(this.getPosn().getX()));
    sb.append("\" y=\"");
    sb.append(Double.toString(this.getPosn().getY()));
    sb.append("\" width=\"");
    sb.append(Double.toString(this.getWidth()));
    sb.append("\" height=\"");
    sb.append(Double.toString(this.getHeight()));

    sb.append(super.printSVG());

    sb.append("</rect>\n");
    return sb.toString();
  }


  @Override
  public IShape getStateAt(int time) {
    Rectangle r = new Rectangle(this.name, this.posn, this.color, this.birthTime,
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
        if (time > o.getStartTime() && time < o.getEndTime()) {
          r.width = r.tweenWidth((Scale) o, r.width, r.width + ((Scale) o).getyFactor(),
              time);
          r.height = r.tweenHeight((Scale) o, r.height, r.height +
              ((Scale) o).getyFactor(), time);
        }
        if (time >= o.getEndTime()) {
          r.width = r.tweenWidth((Scale) o, r.width, r.width +
              ((Scale) o).getyFactor(), o.getEndTime());
          r.height = r.tweenHeight((Scale) o, r.height, r.height +
              ((Scale) o).getyFactor(), o.getEndTime());
        }
      }
    }

    return r;
  }

  @Override
  public String getSymbol() {
    return "";
  }

  /**
   * Tween method for a shape's position. Creates a new position based on the input time and
   * destination of the shape after the move operation has been called.
   *
   * @param m the move operation
   * @param beforePosn the position before the move has occurred
   * @param afterPosn the destination of the shape
   * @param time current time
   * @return designated position at time t
   */
  private Posn tweenPosn(MoveTo m, Posn beforePosn, Posn afterPosn, int time) {

    return new Posn((beforePosn.getX() * ((double) (m.getEndTime() - time) /
        (m.getEndTime() - m.getStartTime()))
        + (afterPosn.getX() * ((double) (time - m.getStartTime()) /
        (m.getEndTime() - m.getStartTime())))),

        (beforePosn.getY() * ((double) (m.getEndTime() - time) /
            (m.getEndTime() - m.getStartTime()))
            + (afterPosn.getY() * ((double) (time - m.getStartTime())
            / (m.getEndTime() - m.getStartTime())))));
  }

  /**
   * Tween method for the color of a shape. Creates a new color based on the input time and desired
   * resultant color after the recolor operation has been called.
   *
   * @param c the recolor operation
   * @param beforeColor the color before the operation
   * @param afterColor the color after the operation
   * @param time current time
   * @return designated color at time t
   */
  private Color tweenColor(Recolor c, Color beforeColor, Color afterColor, int time) {

    return new Color((beforeColor.getRed() + ((afterColor.getRed() - beforeColor.getRed())
        * (time
        - c.getStartTime()) / (c.getEndTime() - c.getStartTime()))),

        (beforeColor.getGreen() + ((afterColor.getGreen() - beforeColor.getGreen())
            * (time
            - c.getStartTime()) / (c.getEndTime() - c.getStartTime()))),
        (beforeColor.getBlue() + ((afterColor.getBlue() - beforeColor.getBlue())
            * (time
            - c.getStartTime()) / (c.getEndTime() - c.getStartTime()))));
  }

  /**
   * Tween method for the width of a shape. Creates a new width based on the input time and desired
   * width after the scale operation has been called.
   *
   * @param s the scale operation
   * @param beforeWidth the width before the operation
   * @param afterWidth the width after the operation
   * @param time the current time
   * @return designated width at time t
   */
  private double tweenWidth(Scale s, double beforeWidth, double afterWidth, int time) {
    return (beforeWidth * ((double) (s.getEndTime() - time) / (s.getEndTime() - s.getStartTime()))
        + (afterWidth * ((double) (time - s.getStartTime()) /
        (s.getEndTime() - s.getStartTime()))));
  }

  /**
   * Tween method for the height of a shape. Creates a new height based on the input time and
   * desired height after the scale operation has been called.
   *
   * @param s the scale operation
   * @param beforeHeight the width before the operation
   * @param afterHeight the width after the operation
   * @param time the current time
   * @return designated width at time t
   */
  private double tweenHeight(Scale s, double beforeHeight, double afterHeight, int time) {
    return (beforeHeight * ((double) (s.getEndTime() - time) / (s.getEndTime() - s.getStartTime()))
        + (afterHeight * ((double) (time - s.getStartTime()) /
        (s.getEndTime() - s.getStartTime()))));
  }
}