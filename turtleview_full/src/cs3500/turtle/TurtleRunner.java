package cs3500.turtle;

import java.util.List;

import cs3500.turtle.control.IController;
import cs3500.turtle.control.MVCCommandController;
import cs3500.turtle.model.Position2D;
import cs3500.turtle.tracingmodel.Line;
import cs3500.turtle.tracingmodel.SmarterTurtle;
import cs3500.turtle.tracingmodel.TracingTurtleModel;
import cs3500.turtle.view.IView;
import cs3500.turtle.view.TurtleGraphicsView;

/**
 * Created by ashesh on 10/26/2016.
 */
public class TurtleRunner {
  public static void main(String[] args) {
    TracingTurtleModel model = new SmarterTurtle();
    IView view = new TurtleGraphicsView();
    IController controller = new MVCCommandController(model, view);
    controller.go();
  }
}
