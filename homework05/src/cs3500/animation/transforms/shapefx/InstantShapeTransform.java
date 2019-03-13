package cs3500.animation.transforms.shapefx;

import cs3500.animation.shapes.LiveShape;
import cs3500.animation.transforms.ActionType;
import cs3500.animation.transforms.Transform;
import java.util.Objects;

/**
 * An action that updates/mutates a LiveShape.
 *
 * Class Invariant: myShape is mutated, but never passed as a reference (only as a copy)
 */
public abstract class InstantShapeTransform implements Transform<LiveShape> {

  protected LiveShape myShape;

  ActionType type;

  private int startTime;
  private int endTime;

  private String strBefore;
  private String strAfter;

  private boolean occurred;


  /**
   * An action that updates/mutates a LiveShape.
   *
   * @param shape the myShape to copy for mutation
   * @param startTime the time to mutate it
   * @param endTime the time to mutate it
   */
  public InstantShapeTransform(ActionType type, LiveShape shape, int startTime, int endTime)
      throws IllegalArgumentException {

    if (startTime > endTime) {
      throw new IllegalArgumentException("Start time must be less than end time!");
    }

    try {
      this.myShape = Objects.requireNonNull(shape);
      this.type = Objects.requireNonNull(type);
    } catch (NullPointerException ne) {
      throw new IllegalArgumentException(ne.getMessage());
    }

    this.startTime = startTime;
    this.endTime = endTime;
    this.occurred = false;
  }

  @Override
  public LiveShape apply(int currTime) {
    if (started(currTime) && !finished(currTime) && !occurred) {
      this.strBefore = myShape.getDescription();
      this.mutate(myShape);
      this.strAfter = myShape.getDescription();
      this.occurred = true;
    }
    return myShape;
  }

  protected abstract void mutate(LiveShape ls);

  @Override
  public LiveShape getCopy() {
    return myShape.copy();
  }

  @Override
  public boolean finished(int currTime) {
    return endTime <= currTime;
  }

  @Override
  public boolean started(int currTime) {
    return startTime <= currTime;
  }

  /**
   * Determines if the given motion conflicts with this one. A motion conflicts with another motion
   * if it occurs during the same time interval and:
   * <li>
   * <ol>The motion type are the same.</ol>
   * <ol>The shapefx of both tranforms have the same IDs.</ol>
   * <ol>The times of the 2 are overlapping</ol>
   * </li>
   */
  public boolean conflict(Transform other) {
    if (other instanceof InstantShapeTransform) {
      InstantShapeTransform otherT = (InstantShapeTransform) other;
      return otherT.type == this.type  // #1
          && (myShape.compareTo(otherT.myShape) == 0) //#2
          && ((otherT.startTime > this.startTime && otherT.startTime < this.endTime) ||
          (otherT.startTime < this.startTime && otherT.endTime > this.startTime)); // #3
    } else {
      return false;
    }
  }

  @Override
  public String textualView() {
    String header = this.type.getDescriptor() + " " + this.myShape.getName();

    String start = this.startTime + " " + this.strBefore;
    String end = this.endTime + " " + this.strAfter;

    return header + ":\t " + start + "\t| " + end;
  }
}
