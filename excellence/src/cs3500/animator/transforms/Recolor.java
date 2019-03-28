package cs3500.animator.transforms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.shapes.IShape;

/**
 * Class representing an operation on a shape; changes the color of a shape to another color
 */
public class Recolor extends ATransform {
  private Color color;

  /**
   * Default constructor.
   *  @param startTime the time at which the color change begins
   * @param endTime   the time at which the color change ends
   * @param color    the new color of the shape
   */
  public Recolor(String name, int startTime, int endTime, Color color) {
    super(name, TransformType.RECOLOR, startTime, endTime);
    this.color = color;
  }

  /**
   * Getter. Returns color.
   *
   * @return color.
   */
  public Color getColor() {
    return this.color;
  }

  @Override
  public void apply(IShape shape) throws IllegalArgumentException {
    List<Transform> opsListOnThisShape = new ArrayList<>();
    for (int i = 0; i < shape.getOperations().size(); i++) {
      if (shape.getOperations().get(i).getType() == TransformType.RECOLOR) {
        opsListOnThisShape.add(shape.getOperations().get(i));
        if (this.startTime > shape.getOperations().get(i).getStartTime()
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
  public String getDescription(AnimationModel model) throws IllegalArgumentException {
    if (this.opShapes.isEmpty()) {
      throw new IllegalArgumentException("This operation has not been used");
    } else {

      String result = getPreDescription(model.getTickRate());
      result += String.format("%d %.0f %.0f %.0f %.0f %d %d %d\n", (this.endTime / model.getTickRate()), this.opShapes.get(0).getPosn().getX(),  this.opShapes.get(0).getPosn().getY(),
              this.opShapes.get(0).getWidth(), this.opShapes.get(0).getHeight(),
              this.color.getRed(), this.color.getGreen(), this.color.getBlue());

      return result;
    }
  }


  @Override
  public String printSVG() {
    String result = "";
    result += "<animate attributeType=\"xml\" begin=\"" + Integer.toString(100 * this.startTime)
            + "ms\" dur=\"" + Integer.toString(100 * (this.endTime - this.startTime)) + "ms\"" +
            " attributeName=\"fill\"" +
            " from=\"RGB("
            + Integer.toString(Math.round(255 * this.opShapes.get(0).getColor().getRed()))
            + ","
            + Integer.toString(Math.round(255 * this.opShapes.get(0).getColor().getGreen()))
            + ","
            + Integer.toString(Math.round(255 * this.opShapes.get(0).getColor().getBlue()))
            + ")\" to=\""
            + "RGB("
            + Integer.toString(Math.round(255 * this.color.getRed())) + ","
            + Integer.toString(Math.round(255 * this.color.getGreen())) + ","
            + Integer.toString(Math.round(255 * this.color.getBlue())) + ")"
            + "\" fill=\"freeze\" />\n";
    return result;
  }
}