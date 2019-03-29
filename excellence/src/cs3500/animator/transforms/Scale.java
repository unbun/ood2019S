package cs3500.animator.transforms;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.shapes.IShape;
import cs3500.animator.shapes.Oval;
import cs3500.animator.shapes.Rectangle;

/**
 * Change the height and width of a Shape.
 */
public class Scale extends ATransform {
  private float xFactor;
  private float yFactor;

  /**
   * Default constructor.
   *  @param startTime the time at which the width change begins
   * @param endTime   the time at which the width change ends
   * @param xFactor   the factor by which the width changes
   * @param yFactor   the factor by which the height changes
   */
  public Scale(String name, int startTime, int endTime, float xFactor, float yFactor) {
    super(name, TransformType.RESIZE, startTime, endTime);
    this.xFactor = xFactor;
    this.yFactor = yFactor;
  }

  /**
   * Gets the change in x.
   *
   * @return the change in x.
   */
  public float getxFactor() {
    return this.xFactor;
  }

  /**
   * Returns the change in y.
   *
   * @return the change in y.
   */
  public float getyFactor() {
    return this.yFactor;
  }

  @Override
  public void apply(IShape shape) {
    List<Transform> opsListOnThisShape = new ArrayList<>();
    for (int i = 0; i < shape.getOperations().size(); i++) {
      if (shape.getOperations().get(i).getType() == TransformType.RESIZE) {
        opsListOnThisShape.add(shape.getOperations().get(i));
        if (this.startTime > shape.getOperations().get(i).getEndTime()
                && this.startTime < shape.getOperations().get(i).getEndTime()) {
          throw new IllegalArgumentException("Cannot call the same animation while in progress.");
        }
      }
    }

    // checks that operation happens after shape appears
    if (this.startTime <= shape.getBirthTime()) {
      throw new IllegalArgumentException("Cannot animate shape before it appears.");
    }
    // checks that operation happens before shape disappears
    else if (this.startTime > shape.getDeathTime()) {
      throw new IllegalArgumentException("Cannot animate shape after it disappears.");
    }
    // adds shape to this operations list of shapes
    // adds this operation to the shape's list of operations
    else {
      this.opShapes.add(shape);
      shape.getOperations().add(this);
    }
  }

  @Override
  public String getDescription(AnimationModel model) {
    if (this.opShapes.isEmpty()) {
      throw new IllegalArgumentException("This operation has not been used");
    }
    if (this.opShapes.get(0).getWidth() + this.xFactor <= 0
            || this.opShapes.get(0).getHeight() + this.yFactor <= 0) {
      throw new IllegalArgumentException("Illegal change in shape dimensions.");
    } else {
      String result = getPreDescription(model.getTickRate());
      result += String.format("%d %.0f %.0f %.0f %.0f %d %d %d\n", (this.endTime / model.getTickRate()), this.opShapes.get(0).getPosn().getX(), this.opShapes.get(0).getPosn().getY(),
              this.opShapes.get(0).getWidth() + this.xFactor, this.opShapes.get(0).getHeight() + this.yFactor,
              this.opShapes.get(0).getColor().getRed(), this.opShapes.get(0).getColor().getGreen(), this.opShapes.get(0).getColor().getBlue());

      return result;
    }
  }

  @Override
  public String printSVG() {
    String result = "";

    if (this.opShapes.get(0) instanceof Rectangle) {
      if (xFactor == 0 && yFactor != 0) {
        result += "<animate attributeType=\"xml\" begin=\"" + Integer.toString(100 * this.startTime)
                + "ms\" dur=\"" + Integer.toString(100 * (this.endTime - this.startTime)) + "ms\"" +
                " attributeName=\"height\"" +
                " from=\"" + Double.toString(this.opShapes.get(0).getHeight()) + "\" to=\""
                + Double.toString(this.opShapes.get(0).getHeight() + yFactor)
                + "\" fill=\"freeze\" />\n";
      }
      if (xFactor != 0 && yFactor == 0) {
        result += "<animate attributeType=\"xml\" begin=\"" + Integer.toString(100 * this.startTime)
                + "ms\" dur=\"" + Integer.toString(100 * (this.endTime - this.startTime)) + "ms\"" +
                " attributeName=\"width\"" +
                " from=\"" + Double.toString(this.opShapes.get(0).getWidth()) + "\" to=\""
                + Double.toString(this.opShapes.get(0).getWidth() + xFactor)
                + "\" fill=\"freeze\" />\n";
      } else {
        result += "<animate attributeType=\"xml\" begin=\"" + Integer.toString(100 * this.startTime)
                + "ms\" dur=\"" + Integer.toString(100 * (this.endTime - this.startTime)) + "ms\"" +
                " attributeName=\"width\"" +
                " from=\"" + Double.toString(this.opShapes.get(0).getWidth()) + "\" to=\""
                + Double.toString(this.opShapes.get(0).getWidth() + xFactor)
                + "\" fill=\"freeze\" />\n";
        result += "<animate attributeType=\"xml\" begin=\"" + Integer.toString(100 * this.startTime)
                + "ms\" dur=\"" + Integer.toString(100 * (this.endTime - this.startTime)) + "ms\"" +
                " attributeName=\"height\"" +
                " from=\"" + Double.toString(this.opShapes.get(0).getHeight()) + "\" to=\""
                + Double.toString(this.opShapes.get(0).getHeight() + yFactor)
                + "\" fill=\"freeze\" />\n";
      }
    }

    if (this.opShapes.get(0) instanceof Oval) {
      if (xFactor == 0 && yFactor != 0) {
        result += "<animate attributeType=\"xml\" begin=\"" + Integer.toString(100 * this.startTime)
                + "ms\" dur=\"" + Integer.toString(100 * (this.endTime - this.startTime)) + "ms\"" +
                " attributeName=\"ry\"" +
                " from=\"" + Double.toString(this.opShapes.get(0).getHeight() / 2) + "\" to=\""
                + Double.toString(this.opShapes.get(0).getHeight() / 2 + yFactor / 2)
                + "\" fill=\"freeze\" />\n";
      }
      if (xFactor != 0 && yFactor == 0) {
        result += "<animate attributeType=\"xml\" begin=\"" + Integer.toString(100 * this.startTime)
                + "ms\" dur=\"" + Integer.toString(100 * (this.endTime - this.startTime)) + "ms\"" +
                " attributeName=\"rx\"" +
                " from=\"" + Double.toString(this.opShapes.get(0).getWidth() / 2) + "\" to=\""
                + Double.toString(this.opShapes.get(0).getWidth() / 2 + xFactor / 2)
                + "\" fill=\"freeze\" />\n";
      } else {
        result += "<animate attributeType=\"xml\" begin=\"" + Integer.toString(100 * this.startTime)
                + "ms\" dur=\"" + Integer.toString(100 * (this.endTime - this.startTime)) + "ms\" " +
                "attributeName=\"rx\"" +
                " from=\"" + Double.toString(this.opShapes.get(0).getWidth() / 2) + "\" to=\""
                + Double.toString(this.opShapes.get(0).getWidth() / 2 + xFactor / 2)
                + "\" fill=\"freeze\" />\n";
        result += "<animate attributeType=\"xml\" begin=\"" + Integer.toString(100 * this.startTime)
                + "ms\" dur=\"" + Integer.toString(100 * (this.endTime - this.startTime)) + "ms\"" +
                " attributeName=\"ry\"" +
                " from=\"" + Double.toString(this.opShapes.get(0).getHeight() / 2) + "\" to=\""
                + Double.toString(this.opShapes.get(0).getHeight() / 2 + yFactor / 2)
                + "\" fill=\"freeze\" />\n";
      }
    }
    return result;
  }
}