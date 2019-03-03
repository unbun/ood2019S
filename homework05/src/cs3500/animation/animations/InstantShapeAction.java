package cs3500.animation.animations;

/**
 * An Action that should only run once (and should avoid any race conditions).
 */
public interface InstantShapeAction extends ShapeAction {

  /**
   * Is this action already done acting.
   *
   * @return has this action occured
   */
  boolean occured();
}
