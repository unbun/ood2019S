package cs3500.animation.model;

import cs3500.animation.actions.Transform;
import cs3500.animation.shapes.LiveShape;
import cs3500.animation.utils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>Represents an implementation of an animation model with various shapes that can be altered,
 * grown, and moved at various speeds at various times in various ways. Tha actions are listed in
 * order of time (if there is a tie, then the first action to be added to the model will go).</p>
 * <p>
 * Invariants:
 * <li>
 * <ul>1) The period of the timer is set to a given rate, and a TimerTask is scheduled once a
 * period.</ul>
 * <ul>3) A {@code LiveShape} cannot have the same kind of animation at the same time.</ul>
 * <ul>4) Once the timer starts, the actions will continually update until they are done or the
 * timer has stopped</ul>
 * <ul>5) Every motion's shape exists within the class's list of {@code LiveShape}a</ul>
 * </li>
 * </p>
 */
public final class AnimationModelImpl implements AnimationModel {

  private final int rate; //in millis
  private Timer t;
  private int currTime;

  private List<Transform> motions;
  private List<LiveShape> shapes;

  public AnimationModelImpl(int rate) {
    this(rate, new ArrayList<>());
  }

  public AnimationModelImpl(int rate, List<LiveShape> shapes) {

    this.motions = new ArrayList<>();
    this.shapes = shapes;
    this.rate = (Integer) Utils.requireNonNegative(rate, "Animation rate");
    this.currTime = -1; //timer hasn't started yet, so there is no currentTime;

    this.t = new Timer(true);
  }

  @Override
  public String getAnimationState() {
    StringBuilder out = new StringBuilder("Time: " + currTime + " ");
    for (Transform m : this.motions) {
      out.append(m.stateString(currTime));
    }
    return out.toString();
  }

  @Override
  public void addMotions(Transform... transforms) throws IllegalArgumentException {
    for (Transform newMotion : transforms) {
      LiveShape currShape = newMotion.getShape();

      if (!this.shapes.contains(currShape)) {
        shapes.add(currShape);
      }

      for (Transform inModel : this.motions) {
        boolean inConflict = inModel.conflict(newMotion);

        if (inConflict) {
          throw new IllegalArgumentException(newMotion.toString()
              + "overlaps with another motion!"); //enforce invariant 3
        }
      }

      this.motions.add(newMotion);
    }
  }

  @Override
  public void addShapes(LiveShape... shapes) {
    for (LiveShape s : shapes) {
      if (!this.shapes.contains(s)) {
        Objects.requireNonNull(s);
        this.shapes.add(s);
      }
    }
  }

  @Override
  public void updateModel() {

    //decided to iterate with index because we are changing the list as we are iterating
    for (int i = 0; i < motions.size(); ) {
      Transform m = this.motions.get(i);
      if (m.finished(currTime)) {
        motions.remove(m); //enforces invariant 4
      } else {
        m.apply(currTime);
        i++;
      }
    }
  }

  @Override
  public void resetShapes() {
    for (LiveShape s : this.shapes) {
      s.reset();
    }
  }

  @Override
  public synchronized void stop() {
    t.cancel();
  }

  @Override
  public synchronized void start() throws IllegalStateException {
    if (currTime != -1) {
      throw new IllegalStateException("Cannot start an already started timer");
    }

    t.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        updateModel();
        currTime++;
      }
    }, 0, rate); //enforces invariant 1
  }

  @Override
  public synchronized void resetTime() {
    currTime = 0;
  }

  @Override
  public int currTime() {
    return currTime;
  }
}




