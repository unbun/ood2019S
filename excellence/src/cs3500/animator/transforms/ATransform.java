package cs3500.animator.transforms;

import java.util.ArrayList;
import java.util.Objects;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.shapes.IShape;
import cs3500.animator.util.Utils;

/**
 * An abstract class representing the abstraction of instances of transformations that shapes could go through.
 */
public abstract class ATransform implements Transform {
  protected String name;
  protected int startTime;
  protected int endTime;
  protected ArrayList<IShape> opShapes;
  private TransformType tType;

  /**
   * Default constructor.
   *
   * @param startTime the time at which the transform begins
   * @param endTime   the time at which the transform ends
   */
  protected ATransform(String name, TransformType tType, int startTime, int endTime) {

    if (startTime > endTime) {
      throw new IllegalArgumentException("Start time must be before endtime");
    }

    else {
      this.name = name;
      this.startTime = Utils.requireNonNegative(startTime);
      this.endTime = Utils.requireNonNegative(endTime);
      this.tType = Objects.requireNonNull(tType);
      this.opShapes = new ArrayList<>();
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
  public abstract void apply(IShape shape) throws IllegalArgumentException;

  @Override
  public abstract String getDescription(AnimationModel model) throws IllegalArgumentException;

  /**
   * Get the part of the Description that is formatted the same for all Transforms regardless of what they are.
   * @param tickRate the tickrate this transform will run at
   * @return a formatted String of the description.
   */
  protected String getPreDescription(int tickRate){
    String result = String.format("%s %s \t", this.tType.descriptor(), this.opShapes.get(0).getName());
    //t  x  y  w  h  r  g  b
    result += String.format("%d %.0f %.0f %.0f %.0f %d %d %d\t| ", (this.startTime / tickRate), this.opShapes.get(0).getPosn().getX(), this.opShapes.get(0).getPosn().getY(),
            this.opShapes.get(0).getWidth(), this.opShapes.get(0).getHeight(),
            this.opShapes.get(0).getColor().getRed(), this.opShapes.get(0).getColor().getGreen(), this.opShapes.get(0).getColor().getBlue());
    return result;
  }

  @Override
  public abstract String printSVG();

  @Override
  public String getName() {
    return this.name;
  }
}
