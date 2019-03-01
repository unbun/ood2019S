package cs3500.turtle.control.commands;


import cs3500.turtle.control.TracingTurtleCommand;
import cs3500.turtle.tracingmodel.TracingTurtleModel;

/**
 * This command implements the "Koch snowflake".
 * The Koch snowflake is a recursively drawn figure.
 * It starts with an equilateral triangle whose
 * sides are of a given length.
 * <p>
 * Then, each segment is broken down into 4 segments:
 * go 1/3rd length in the direction of the line,
 * turn 60 and go 1/3rd length, then turn -120 and go
 * 1/3rd length, and finally turn 60 and go 1/3rd
 * length. Basically it converts
 * _____________ to
 * /\
 * /  \
 * ___/    \____
 */
public class Koch implements TracingTurtleCommand {

  private double d;
  private int depth;

  public Koch(double distance, int depth) {
    d = distance;
    this.depth = depth;
  }

  @Override
  public void go(TracingTurtleModel m) {
    drawKoch(d, depth, m);
    m.turn(-120);
    drawKoch(d, depth, m);
    m.turn(-120);
    drawKoch(d, depth, m);
    m.turn(-120);
  }

  private void drawKoch(double distance, int depth, TracingTurtleModel model) {
    if (depth > 0) {
      drawKoch(distance / 3, depth - 1, model);
      model.turn(60);
      drawKoch(distance / 3, depth - 1, model);
      model.turn(-120);
      drawKoch(distance / 3, depth - 1, model);
      model.turn(60);
      drawKoch(distance / 3, depth - 1, model);
    } else {
      model.trace(distance);
    }
  }
}
