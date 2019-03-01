package cs3500.turtle.tracingmodel;

import java.util.List;

import cs3500.turtle.model.TurtleModel;

/**
 * Created by blerner on 10/10/16.
 */
public interface TracingTurtleModel extends TurtleModel {
  /**
   * Move the turtle by the specified distance along its
   * heading. Do not change heading.
   * Draw a line from its initial position to its
   * final position.
   *
   * @param distance
   */
  void trace(double distance);

  /**
   * Get the lines traced by this turtle, caused by the
   * trace method above.
   *
   * @return a list of {@code Line} objects, in the order they were drawn.
   */
  List<Line> getLines();
}
