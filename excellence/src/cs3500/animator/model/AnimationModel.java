package cs3500.animator.model;


/**
 *
 */
public interface AnimationModel<V> {

  V getView();

  int getTime();

  void start();

  void restart();

  void stop();

}
