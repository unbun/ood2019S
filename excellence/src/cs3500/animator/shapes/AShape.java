package cs3500.animator.shapes;

import cs3500.animator.transforms.Transform;
import cs3500.animator.util.Posn;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a Shape that can calculate it's own time-based movements and alterations.
 */
public abstract class AShape implements IShape {

  protected String name;
  protected Posn posn;
  protected Color color;
  protected int birthTime;
  protected int deathTime;

  protected double width;
  protected double height;

  protected ShapeClass sClass;

  protected ArrayList<Transform> transformList;

  /**
   * Default constructor.
   *
   * @param name the name of the shape
   * @param posn the current position of the shape
   * @param color the initial color of the shape
   * @param birthTime the time that the shape appears in the animation
   * @param deathTime the time that the shape disappears in the animation
   */
  protected AShape(String name, ShapeClass sClass,
      Posn posn, Color color, int birthTime, int deathTime,
      double width, double height) {
    this.name = name;
    this.posn = posn;
    this.color = color;
    this.birthTime = birthTime;
    this.deathTime = deathTime;
    this.transformList = new ArrayList<>();
    this.width = width;
    this.height = height;
    this.sClass = Objects.requireNonNull(sClass);
  }

  /**
   * Moves a shape to a new position.
   *
   * @param destination the new position of the shape
   */
  protected void move(Posn destination) {
    this.posn.setX(destination.getX());
    this.posn.setY(destination.getY());
  }

  /**
   * Changes the width of a shape by a given factor.
   *
   * @param factor the change in width
   * @throws IllegalArgumentException if the new width is not positive
   */
  protected abstract void changeWidth(double factor) throws IllegalArgumentException;

  /**
   * Changes the height of a shape by a given factor.
   *
   * @param factor the change in height
   * @throws IllegalArgumentException if the new height is not positive
   */
  protected abstract void changeHeight(double factor) throws IllegalArgumentException;

  public String getName() {
    return this.name;
  }

  public Posn getPosn() {
    return this.posn;
  }

  public Color getColor() {
    return this.color;
  }

  public int getBirthTime() {
    return this.birthTime;
  }

  public int getDeathTime() {
    return this.deathTime;
  }

  public ArrayList<Transform> getOperations() {
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


  @Override
  public String printSVG() {
    StringBuilder sb = new StringBuilder();
    sb.append("\" fill=\"RGB(");
    sb.append(Integer.toString(Math.round(this.getColor().getRed() * 255)));
    sb.append(",");
    sb.append(Integer.toString(Math.round(this.getColor().getGreen() * 255)));
    sb.append(",");
    sb.append(Integer.toString(Math.round(this.getColor().getBlue() * 255)));
    sb.append(")\" visibility=\"visible\" >\n");

    for (Transform o : this.getOperations()) {
      sb.append(o.printSVG());
    }
    return sb.toString();
  }

  @Override
  abstract public IShape getStateAt(int t);

  @Override
  public String toString() {
    return String.format("%s:\"%s\" posn=%s; size=(%f, %f); color=%s", sClass.text, name,
        posn.getDescription(), height, width, color.toString());
  }
}
