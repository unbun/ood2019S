package cs3500.animator.transforms;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.shapes.IShape;
import cs3500.animator.util.Utils;
import java.util.ArrayList;
import java.util.Objects;

/**
 * An abstract class representing instances of transformations that could be applied to shapes.
 */
public abstract class ATransform implements Transform {

  protected String name;
  protected int startTime;
  protected int endTime;
  protected ArrayList<IShape> shapes;
  private TransformType tType;

  /**
   * Default constructor.
   *
   * @param startTime the time at which the transform begins
   * @param endTime the time at which the transform ends
   */
  protected ATransform(String name, TransformType tType, int startTime, int endTime) {
    if (startTime > endTime) {
      throw new IllegalArgumentException("Start time must be before end time");
    } else {
      this.name = name;
      this.startTime = Utils.requireNonNegative(startTime);
      this.endTime = Utils.requireNonNegative(endTime);
      this.tType = Objects.requireNonNull(tType);
      this.shapes = new ArrayList<>();
    }
  }

  @Override
  public TransformType getType() {
    return this.tType;
  }

  @Override
  public int getStartTime() {
    return startTime;
  }

  @Override
  public int getEndTime() {
    return endTime;
  }

  @Override
  public void apply(IShape shape) throws IllegalArgumentException {
    if (this.startTime < shape.gett0()) {
      throw new IllegalArgumentException("Cannot animate shape before it appears.");
    } else {
      this.shapes.add(shape);
      shape.getTransformations().add(this);
    }
  }


  @Override
  public abstract String toText(AnimationModel model);

  /**
   * Produces the first part of a textual representation for any transformation.
   *
   * @param tickRate the tickrate of the transform
   * @return a formatted String describing the transform
   */
  protected String getPreDescription(int tickRate) {
    String result = String
        .format("%s %s \t", this.tType.descriptor(), this.shapes.get(0).getName());
    //t  x  y  w  h  r  g  b
    result += String.format("%d %.0f %.0f %.0f %.0f %d %d %d\t| ", (this.startTime / tickRate),
        this.shapes.get(0).getPosn().getX(), this.shapes.get(0).getPosn().getY(),
        this.shapes.get(0).getWidth(), this.shapes.get(0).getHeight(),
        this.shapes.get(0).getColor().getRed(), this.shapes.get(0).getColor().getGreen(),
        this.shapes.get(0).getColor().getBlue());
    return result;
  }

  @Override
  public String getName() {
    return this.name;
  }
}
