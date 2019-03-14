package cs3500.animator.transforms;

import cs3500.animator.shapes.LiveShape;
import java.util.Objects;

/**
 * An action that updates/mutates a LiveShape.
 *
 * Class Invariant: the apply function only mutates once (enforced by the occurred boolean)
 */
public abstract class InstantTransform implements Transform<LiveShape> {

  protected TransformType type;

  private int startTime;
  private int endTime;

  private String strBefore;
  private String strAfter;

  private boolean occurred;

  /**
   * An action that updates/mutates a LiveShape.
   *
   * @param startTime the time to mutate it
   * @param endTime the time to mutate it
   */
  public InstantTransform(TransformType type, int startTime, int endTime)
      throws IllegalArgumentException {

    if (startTime > endTime) {
      throw new IllegalArgumentException("Start time must be less than end time!");
    }

    try {
      this.type = Objects.requireNonNull(type);
    } catch (NullPointerException ne) {
      throw new IllegalArgumentException(ne.getMessage());
    }

    this.startTime = startTime;
    this.endTime = endTime;
    this.occurred = false;

    this.strBefore = "queued";
    this.strAfter = "queued";
  }

  @Override
  public LiveShape apply(LiveShape toMutate, int currTime) {
    try{
      Objects.requireNonNull(toMutate);
    } catch (NullPointerException ne){
      throw new IllegalArgumentException(ne.getMessage());
    }

    if (started(currTime) && !finished(currTime) && !occurred) {
      this.strBefore = String.format("%s:\t %d %s",
          toMutate.getName(), this.startTime, toMutate.stateStr());

      this.mutate(toMutate);

      this.strAfter = String.format("%d %s",
          this.endTime, toMutate.stateStr());

      this.occurred = true;
    }

    return toMutate;
  }

  protected abstract void mutate(LiveShape ls);

  @Override
  public boolean finished(int currTime) {
    return endTime <= currTime;
  }

  @Override
  public boolean started(int currTime) {
    return startTime <= currTime;
  }

  @Override
  public String textualStr() {
    return String.format("%s %s\t| %s", this.type.toString(), strBefore, strAfter);
  }

  @Override
  public int startTime() {
    return startTime;
  }
}
