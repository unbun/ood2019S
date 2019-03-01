package cs3500.animation.model;

import cs3500.animation.animations.ShapeAction;
import cs3500.animation.utils.Utils;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents an implementation of an animation model with various shapes that can be altered,
 * grown, and moved at various speeds at various times in various ways.
 *
 *
 * Invariants:
 * <li>
 * <ul>1) The period of the timer is set to a given rate, and a TimerTask is scheleded once a
 * period.</ul>
 * <ul>2) The actions of the model will only be applied once, at their starttime.</ul>
 * <ul>3) A shape cannot have the same kind of animation at the same time.</ul>
 * <ul>4) Once the timer starts, the animations will continually update until they are done or the
 * timer has stopped</ul>
 * </li>
 */
public class AnimationModelImpl implements AnimationModel {

  private final int height;
  private final int width;
  private final Color background;


  private final int rate; //in millis
  private Timer t;
  private int currTime;

  private ArrayList<ShapeAction> motions;

  /**
   * Constructs an AnimationModelImpl with the given height, width, and background color, and an
   * empty list of shapes and motions.
   *
   * @param height the height of the animation
   * @param width the width of the animation
   * @param background the color of the animation
   */
  public AnimationModelImpl(int height, int width, Color background, int rate) {
    this.height = height;
    this.width = width;
    this.background = background;
    this.motions = new ArrayList<>();
    this.rate = (Integer) Utils.requireNonNegative(rate, "Animation rate");
    this.currTime = 0;

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
      this.motions.add(newMotion);
    }
  }


  @Override
  public void updateModel() {

    //decided to iterate with index because we are changing the list as we are iterating
    for (int i = 0; i < motions.size(); ) {
      ShapeAction m = this.motions.get(i);
      if (m.finished(currTime)) {
        motions.remove(m);
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
  public synchronized void start() {
    t.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        try {
          updateModel();
          Thread.sleep(rate); //enforces invariant 1 (1/2)
          currTime++;
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, 0, rate); //enforces invariant 1 (2/2)
  }

  @Override
  public synchronized void restart() {
    currTime = -1;
  }
}




