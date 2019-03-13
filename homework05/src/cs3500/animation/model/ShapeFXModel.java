package cs3500.animation.model;

import cs3500.animation.shapes.LiveShape;
import cs3500.animation.transforms.Transform;
import cs3500.animation.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

/**
 * <p>Represents an implementation of an animation model with various shapefx that can be altered,
 * grown, and moved at various speeds at various times in various ways. Tha transforms are listed in
 * order of time (if there is a tie, then the first action to be added to the model will go).</p>
 * <p>
 * Invariants:
 * <li>
 * <ul>1) The period of the timer is set to a given rate, and a TimerTask is scheduled once a
 * period.</ul>
 * <ul>2) The map of strings is mapped so that each shape id is the key and each shape is the
 * value</ul>
 * <ul>3) A {@code LiveShape} cannot have the same kind of animation at the same time.</ul>
 * <ul>4) Once the timer starts, the transforms will continually update until they are done or the
 * timer has stopped</ul>
 * <ul>5) Every motion's myShape exists within the class's list of {@code LiveShape}a</ul>
 * </li>
 * </p>
 */
public abstract class ShapeFXModel implements AnimationModel {

  private final int rate; //in millis
  protected List<Transform<LiveShape>> transforms;
  int currTime;
  private Timer t;
  private Map<String, LiveShape> shapes; //map makes updating shapefx from multiple transforms easier

  public ShapeFXModel(int rate) {
    this(rate, new ArrayList<>());
  }

  public ShapeFXModel(int rate, List<LiveShape> shapes) {

    Objects.requireNonNull(shapes);

    if (shapes.stream().map(LiveShape::getID).distinct().count() != (long) shapes.size()) {
      throw new IllegalArgumentException(
          "Shapes list cannot contain shapefx with the same ids (types + name");
    }

    this.shapes = shapes.stream().collect(
        Collectors.toMap(LiveShape::getID, ls -> ls));

    this.transforms = new ArrayList<>();
    this.rate = (Integer) Utils.requireNonNegative(rate, "Animation rate");
    this.currTime = -1; //timer hasn't started yet, so there is no currentTime;

    this.t = new Timer(true);
  }

  @Override
  public void addMotions(Transform<LiveShape>... transforms) throws IllegalArgumentException {
    for (Transform<LiveShape> newMotion : transforms) {
      LiveShape currShape = newMotion.getCopy();

      if (!shapes.containsKey(currShape.getID())) {
        shapes.put(currShape.getID(), currShape);
      }

      for (Transform inModel : this.transforms) {
        boolean inConflict = inModel.conflict(newMotion);

        if (inConflict) {
          throw new IllegalArgumentException(newMotion.toString()
              + "overlaps with another motion!"); //enforce invariant 3
        }
      }

      this.transforms.add(newMotion);
    }
  }

  @Override
  public void flushMotions() {
    transforms = transforms.stream().filter(ls -> !ls.finished(currTime))
        .collect(Collectors.toList());
  }

  @Override
  public void onTick() {
    //run the transforms to mutate the shapefx
    for (Transform<LiveShape> f : transforms) {
      LiveShape mutated = f.apply(currTime);
      shapes.replace(mutated.getID(), mutated); // enforces invariant 2
    }
    currTime++;
  }

  public List<LiveShape> getShapes() {
    return new ArrayList<>(this.shapes.values());
  }

  @Override
  public void reset() {
    for (LiveShape s : this.shapes.values()) {
      s.reset();
    }
    this.restartTime();
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
        onTick();
      }
    }, 0, rate); //enforces invariant 1
  }

  @Override
  public synchronized void restartTime() {
    currTime = 0;
  }

  @Override
  public int currTime() {
    return currTime;
  }


}




