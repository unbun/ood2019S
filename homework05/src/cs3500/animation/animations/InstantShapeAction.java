package cs3500.animation.animations;

/**
 * An Action that should only run once (and should not create a race condition)
 */
public interface InstantShapeAction extends ShapeAction {
  boolean occured();
}
