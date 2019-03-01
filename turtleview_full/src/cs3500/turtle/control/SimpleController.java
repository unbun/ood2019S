package cs3500.turtle.control;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import cs3500.turtle.tracingmodel.Line;
import cs3500.turtle.tracingmodel.SmarterTurtle;
import cs3500.turtle.tracingmodel.TracingTurtleModel;

/**
 * Created by blerner on 10/10/16.
 */
public class SimpleController {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    TracingTurtleModel m = new SmarterTurtle();
    while (s.hasNext()) {
      String in = s.next();
      switch (in) {
        case "q":
        case "quit":
          return;
        case "show":
          for (Line l : m.getLines()) {
            System.out.println(l);
          }
          break;
        case "move":
          try {
            double d = s.nextDouble();
            m.move(d);
          } catch (InputMismatchException ime) {
            System.out.println("Bad length to move");
          }
          break;
        case "trace":
          try {
            double d = s.nextDouble();
            m.trace(d);
          } catch (InputMismatchException ime) {
            System.out.println("Bad length to trace");
          }
          break;
        case "turn":
          try {
            double d = s.nextDouble();
            m.turn(d);
          } catch (InputMismatchException ime) {
            System.out.println("Bad length to turn");
          }
          break;
        case "square":
          try {
            double d = s.nextDouble();
            m.trace(d);
            m.turn(90);
            m.trace(d);
            m.turn(90);
            m.trace(d);
            m.turn(90);
            m.trace(d);
            m.turn(90);
          } catch (InputMismatchException ime) {
            System.out.println("Bad length to turn");
          }
          break;
        default:
          System.out.println(String.format("Unknown command %s", in));
          break;
      }
    }
  }
}
