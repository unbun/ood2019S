package cs3500.animator.shapes;

import cs3500.animator.transforms.MoveTo;
import cs3500.animator.transforms.Recolor;
import cs3500.animator.transforms.Resize;
import cs3500.animator.transforms.Transform;
import cs3500.animator.util.Posn;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents different types of shapes that can compute their own time-based movements and
 * alterations.
 */
public abstract class AShape implements IShape {

  protected String name;
  protected Posn posn;
  protected Color color;
  protected int t0;

  protected double width;
  protected double height;

  protected ShapeClass sClass;

  protected ArrayList<Transform> transformList;

  /**
   * Constructs an AShape.
   *
   * @param name the name of the shape
   * @param posn the current position of the shape
   * @param color the initial color of the shape
   * @param t0 the time that the shape appears in the animation
   */
  protected AShape(String name, ShapeClass sClass,
      Posn posn, Color color, int t0,
      double width, double height) {
    this.name = name;
    this.posn = posn;
    this.color = color;
    this.t0 = t0;
    this.transformList = new ArrayList<>();
    this.width = width;
    this.height = height;
    this.sClass = Objects.requireNonNull(sClass);
  }

  public String getName() {
    return this.name;
  }

  public Posn getPosn() {
    return this.posn;
  }

  public Color getColor() {
    return this.color;
  }

  public int gett0() {
    return this.t0;
  }

  public ArrayList<Transform> getTransformations() {
    return this.transformList;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  @Override
  public String getType() {
    return this.sClass.textName();
  }

  public abstract String xFieldSVG();

  public abstract String yFieldSVG();

  public abstract String scaleX();

  public abstract String scaleY();

  public abstract String endLineSVG();


  @Override
  public String printSVG() {
    String svg;
    String color = this.color.getRed() + "," + this.color.getGreen() + "," + this.color.getBlue();
    svg = "<" + this.sClass.svgName() + " id" + "=\"" + this.name + "\"" + " " + xFieldSVG() + "=\""
        + this.posn.x + "\" "
        + yFieldSVG() + "=\"" + this.posn.y + "\" " +
        scaleX() + "=\"" + this.width + "\" " + scaleY() + "=\"" + this.height +
        "\" fill=\"rgb(" + color + ")\" visibility=\"visible\"";
    for (Transform t : this.transformList) {
      svg += t.commandToSVG(this);
    }
    return svg;
  }

  @Override
  public String toString() {
    return String.format("%s:\"%s\" posn=%s; size=(%f, %f); color=%s", sClass.text, name,
        posn.getDescription(), height, width, color.toString());
  }

  /**
   * Determines the current Posn of a shape at the given time depending on its progress in its
   * tweening transformation from one Posn to another over a period of time.
   *
   * @param m the move transformation
   * @param beforePosn the position before the move has occurred
   * @param afterPosn the destination of the shape
   * @param time current time
   * @return designated position at time t
   */
  protected Posn tweenPosn(MoveTo m, Posn beforePosn, Posn afterPosn, int time) {
    int timechange = m.getEndTime() - m.getStartTime();
    int xchange = (int) (afterPosn.x - beforePosn.x);
    int ychange = (int) (afterPosn.y - beforePosn.y);
    double xChange = (double) xchange / (double) timechange;
    double yChange = (double) ychange / (double) timechange;
    double xChangeSoFar = xChange * (time - m.getStartTime());
    double yChangeSoFar = yChange * (time - m.getStartTime());
    int newX = (int) beforePosn.x + ((Double) Math.floor(xChangeSoFar)).intValue();
    int newY = (int) beforePosn.y + ((Double) Math.floor(yChangeSoFar)).intValue();
    return new Posn(newX, newY);
  }

  /**
   * Determines the current Color of a shape at the given time depending on its progress in its
   * tweening transformation from one Color to another over a period of time.
   *
   * @param c the changeColor transformation
   * @param c1 the color before the transformation
   * @param c2 the color after the transformation
   * @param time current time
   * @return designated color at time t
   */
  protected Color tweenColor(Recolor c, Color c1, Color c2, int time) {
    int r1 = c1.getRed();
    int g1 = c1.getGreen();
    int b1 = c1.getBlue();
    int r2 = c2.getRed();
    int g2 = c2.getGreen();
    int b2 = c2.getBlue();
    int rchange = r2 - r1;
    int gchange = g2 - g1;
    int bchange = b2 - b1;
    int timechange = c.getEndTime() - c.getStartTime();
    double rChange = (double) rchange / (double) timechange;
    double gChange = (double) gchange / (double) timechange;
    double bChange = (double) bchange / (double) timechange;
    double rChangeSoFar = rChange * (time - c.getStartTime());
    double gChangeSoFar = gChange * (time - c.getStartTime());
    double bChangeSoFar = bChange * (time - c.getStartTime());
    int tweenedRed = c1.getRed() + ((Double) Math.floor(rChangeSoFar)).intValue();
    int tweenedGreen = c1.getGreen() + ((Double) Math.floor(gChangeSoFar)).intValue();
    int tweenedBlue = c1.getBlue() + ((Double) Math.floor(bChangeSoFar)).intValue();
    return new Color(tweenedRed, tweenedGreen, tweenedBlue);
  }

  /**
   * Determines the current width of a shape at the given time depending on its progress in its
   * tweening transformation from one width to another over a period of time.
   *
   * @param s the scale transformation
   * @param beforeWidth the width before the transformation
   * @param afterWidth the width after the transformation
   * @param time the current time
   * @return designated width at time t
   */
  protected double tweenWidth(Resize s, double beforeWidth, double afterWidth, int time) {
    int timechange = s.getEndTime() - s.getStartTime();
    int wchange = (int) (afterWidth - beforeWidth);
    double wChange = (double) wchange / (double) timechange;
    double wChangeSoFar = wChange * (time - s.getStartTime());
    return beforeWidth + ((Double) Math.floor(wChangeSoFar)).intValue();
  }

  /**
   * Determines the current height of a shape at the given time depending on its progress in its
   * tweening transformation from one height to another over a period of time.
   *
   * @param s the scale transformation
   * @param beforeHeight the width before the transformation
   * @param afterHeight the width after the transformation
   * @param time the current time
   * @return designated width at time t
   */
  protected double tweenHeight(Resize s, double beforeHeight, double afterHeight, int time) {
    int timechange = s.getEndTime() - s.getStartTime();
    int hchange = (int) (afterHeight - beforeHeight);
    double hChange = (double) hchange / (double) timechange;
    double hChangeSoFar = hChange * (time - s.getStartTime());
    return beforeHeight + ((Double) Math.floor(hChangeSoFar)).intValue();
  }
}
