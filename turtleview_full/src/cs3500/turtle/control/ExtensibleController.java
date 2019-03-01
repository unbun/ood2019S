package cs3500.turtle.control;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

import cs3500.turtle.control.commands.Koch;
import cs3500.turtle.control.commands.Move;
import cs3500.turtle.control.commands.Square;
import cs3500.turtle.control.commands.Trace;
import cs3500.turtle.control.commands.Turn;
import cs3500.turtle.tracingmodel.Line;
import cs3500.turtle.tracingmodel.SmarterTurtle;
import cs3500.turtle.tracingmodel.TracingTurtleModel;

/**
 * Created by blerner on 10/10/16.
 */
public class ExtensibleController {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    TracingTurtleModel m = new SmarterTurtle();
    Stack<TracingTurtleCommand> commands = new Stack<>();

    Map<String, Function<Scanner, TracingTurtleCommand>> knownCommands = new HashMap<>();
    knownCommands.put("move", (Scanner s) -> {
      return new Move(s.nextDouble());
    });
    knownCommands.put("turn", (Scanner s) -> {
      return new Turn(s.nextDouble());
    });
    knownCommands.put("trace", (Scanner s) -> {
      return new Trace(s.nextDouble());
    });
    knownCommands.put("square", (Scanner s) -> {
      return new Square(s.nextDouble());
    });
    knownCommands.put("koch", (Scanner s) -> {
      return new Koch(s.nextDouble()
              , s.nextInt())
              ;
    });
    knownCommands.put("show", (Scanner s) -> {
      return (TracingTurtleModel model) -> {
        for (Line l : model.getLines())
          System.out.println(l);
      };
    });
    while (scan.hasNext()) {
      TracingTurtleCommand c;
      String in = scan.next();
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit"))
        return;
      Function<Scanner, TracingTurtleCommand> cmd = knownCommands.getOrDefault(in, null);
      if (cmd == null) {
        throw new IllegalArgumentException();
      } else {
        c = cmd.apply(scan);
        commands.add(c);
        c.go(m);
      }
    }
  }
}
