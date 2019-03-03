package cs3500.animation.model;

import cs3500.animation.animations.ShapeAction;
import cs3500.animation.utils.Utils;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>Represents an implementation of an animation model with various shapes that can be altered,
 * grown, and moved at various speeds at various times in various ways. Tha animations are listed in
 * order of time (if there is a tie, then the first action to be added to the model will go).</p>
 * <p>
 * Invariants:
 * <li>
 * <ul>1) The period of the timer is set to a given rate, and a TimerTask is scheduled once a
 * period.</ul>
 * <ul>2) The actions of <strong>this model impl</strong> will only be applied once, at their
 * starttime.</ul>
 * <ul>3) A shape cannot have the same kind of animation at the same time.</ul>
 * <ul>4) Once the timer starts, the animations will continually update until they are done or the
 * timer has stopped</ul>
 * <ul>5) Every motion's shape has atleast 1 motion that creates it (in other words, no motion has
 * a non-Created shape)</ul>
 * </li>
 * </p>
 */
public final class AnimationModelImpl implements AnimationModel {

  private final int rate; //in millis
  private Timer t;
  private int currTime;

  private ArrayList<ShapeAction> motions;

  /**
   * Constructs an AnimationModelImpl with the given height, width, and background color, and an
   * empty list of shapes and motions.
   *
   * @param rate in millis, the period of the timer's tick
   */
  public AnimationModelImpl(int rate) {

    this.motions = new ArrayList<>();
    this.rate = (Integer) Utils.requireNonNegative(rate, "Animation rate");
    this.currTime = -1; //timer hasn't started yet, so there is no currentTime;

    this.t = new Timer(true);
  }

  @Override
  public String getAnimationState() {
    StringBuilder out = new StringBuilder("Time: " + currTime + " ");
    for (ShapeAction m : this.motions) {
      out.append(m.stateString(currTime));
    }
    return out.toString();
  }

  @Override
  public void addMotions(ShapeAction... shapeActions) throws IllegalArgumentException {
    for (ShapeAction newMotion : shapeActions) {
      //make checks with this new motion with all exisiting motions
      for (ShapeAction inModel : this.motions) {
        boolean inConflict = inModel.conflict(newMotion);
        if (inConflict) {
          throw new IllegalArgumentException(newMotion.toString()
              + "overlaps with another motion!"); //enforce invariant 3
        }
      }

      if (!newMotion.isShapeInitalized()) { //enforces invariant 5
        this.motions.add(newMotion.initCreateAction(currTime));
      }
      this.motions.add(newMotion);
    }
  }


  @Override
  public void updateModel() {

    //decided to iterate with index because we are changing the list as we are iterating
    for (int i = 0; i < motions.size(); ) {
      ShapeAction m = this.motions.get(i);
      if (m.finished(currTime)) {
        motions.remove(m); //enforces invariant 4
      } else {
        if (m.getStartTime() == currTime) { //enforce invariant 2
          m.apply(currTime);
        }
        i++;
      }
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
  public synchronized void reset() {
    currTime = 0;
  }

  @Override
  public int currTime() {
    return currTime;
  }
}




