package cs3500.animator.model;

import cs3500.animator.shapes.LiveShape;
import java.util.List;

/**
 *
 */
public interface AnimationModel<V>{

  /**
   * Adds the given motions to this animator'myShape list of motions and implements them at the
   * corresponding time, if the motions do not conflict with any preexisting motions and if the
   * motions act upon shapefx within this model.
   *
   * @param shps the shapes to be added
   *
   */
  void addShapes(LiveShape ... shps);

  List<LiveShape> currentShapes();

  V getView();

  int getTime();

  void start();

  void restart();

  void stop();

}
