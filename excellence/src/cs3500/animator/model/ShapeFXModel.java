package cs3500.animator.model;

import cs3500.animator.shapes.LiveShape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>Represents an implementation of an animator model with various shapefx that can be altered,
 * grown, and moved at various speeds at various times in various ways. Tha transforms are listed in
 * order of time (if there is a tie, then the first action to be added to the model will go).</p>
 * <p>
 * Invariants:
 * <li>
 * <ul>1) The period of the timer is set to a given rate, and a TimerTask is scheduled once a
 * period.</ul>
 * <ul>2) The map of strings is mapped so that each shape id is the key and each shape is the
 * value</ul>
 * <ul>3) A {@code LiveShape} cannot have the same kind of animator at the same time.</ul>
 * <ul>4) Once the timer starts, the transforms will continually update until they are done or the
 * timer has stopped</ul>
 * <ul>5) Every motion's myShape exists within the class's list of {@code LiveShape}a</ul>
 * </li>
 * </p>
 */
public abstract class ShapeFXModel<V> implements AnimationModel<V> {

  protected List<LiveShape> shapes;
  private Timer t;
  private int rate;
  private int currTime;

  public ShapeFXModel(int rate) {
    this(rate, new ArrayList<>());
  }

  public ShapeFXModel(int rate, LiveShape... shapes) {
    this(rate, Arrays.asList(shapes));
  }

  public ShapeFXModel(int rate, List<LiveShape> shapes) {

    if (shapes.stream().map(LiveShape::getID).distinct().count() != (long) shapes.size()) {
      throw new IllegalArgumentException(
          "Shapes list cannot contain shapes with the same ids (types + name)");
    }

    this.shapes = Objects.requireNonNull(shapes);
    this.t = new Timer(true);
    this.rate = rate;
    this.currTime = -1;
  }

  @Override
  public void addShapes(LiveShape... shps) {
    for (LiveShape s : shps) {
      if (!shapes.contains(s)) { // no repetitions
        shapes.add(s);
      }
    }
  }

  @Override
  public List<LiveShape> currentShapes() {

    ArrayList<LiveShape> updated = new ArrayList<>();

    for (LiveShape ls : shapes) {
      updated.add(ls.update(getTime()));
    }

    return updated;
  }

  ///////////////////////////////////////////
  ////////////// TIMER METHODS //////////////
  ///////////////////////////////////////////


  @Override
  public int getTime() {
    return currTime;
  }

  @Override
  public void restart() {
    currTime = 0;
  }

  @Override
  public void start() {
    if (currTime != -1) {
      throw new IllegalStateException("Cannot start a timer that is already running");
    }

    t.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        currentShapes();
        currTime++;
      }
    }, 0, rate);
  }

  @Override
  public void stop() {
    t.cancel();
  }
}




