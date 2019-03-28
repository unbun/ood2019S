package cs3500.animator.transforms;

import java.util.ArrayList;
import java.util.Objects;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.shapes.IShape;

/**
 * An abstract class representing the abstraction of instances of operations that shapes can have.
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
   * @param startTime the time at which the operation begins
   * @param endTime   the time at which the operation ends
   */
  protected ATransform(String name, TransformType tType, int startTime, int endTime) {
    if (this.startTime < 0 || this.endTime < 0) {
      throw new IllegalArgumentException("Invalid times.");
    } else if (this.startTime > this.endTime) {
      throw new IllegalArgumentException("Invalid times.");
    }
    else {
      this.name = name;
      this.startTime = startTime;
      this.endTime = endTime;
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
