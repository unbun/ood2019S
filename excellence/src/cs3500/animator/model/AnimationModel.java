package cs3500.animator.model;


import cs3500.animator.shapes.LiveShape;
import cs3500.animator.transforms.Transform;

/**
 *
 */
public interface AnimationModel<V> {

    V viewNow();

    V viewAtTime(int time);

    int getTime();

    void start();

    void restart();

    void stop();

    void addShape(LiveShape ls);

    void addMotion(String shapeName, Transform t);

}
