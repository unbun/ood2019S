package cs3500.turtle.control.commands;

import cs3500.turtle.control.TracingTurtleCommand;
import cs3500.turtle.tracingmodel.TracingTurtleModel;

/**
 * Created by ashesh on 10/28/2016.
 */
public class Retrieve implements TracingTurtleCommand {
  @Override
  public void go(TracingTurtleModel m) {
    m.retrieve();
  }
}
