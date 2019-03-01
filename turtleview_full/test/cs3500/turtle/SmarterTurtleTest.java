package cs3500.turtle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.turtle.tracingmodel.Line;
import cs3500.turtle.model.Position2D;
import cs3500.turtle.model.SimpleTurtle;
import cs3500.turtle.model.TurtleModel;
import cs3500.turtle.tracingmodel.SmarterTurtle;
import cs3500.turtle.tracingmodel.TracingTurtleModel;

import static org.junit.Assert.*;

/**
 * Created by ashesh on 5/23/2016.
 */
public final class SmarterTurtleTest {

  @Test
  public void testOne() {
    TracingTurtleModel t = new SmarterTurtle();

    t.turn(60);
    t.trace(19);
    t.turn(-120);
    t.trace(19);
    t.turn(-120);
    t.trace(19);
    List<Line> tlines = t.getLines();

    TracingTurtleModel t2 = new SmarterTurtle();

    t2.trace(19);
    t2.turn(120);
    t2.trace(19);
    t2.turn(120);
    t2.trace(19);

    List<Line> t2lines = t2.getLines();

    assertEquals(tlines.size(), t2lines.size());

    for (Line l : tlines) {
      assertTrue(t2lines.contains(l));
    }
  }

  @Test
  public void testDrawSomething() {
    TracingTurtleModel t = new SmarterTurtle();

    Position2D p1 = t.getPosition();
    t.save();
    t.turn(45);
    t.trace(10);
    t.retrieve();

    t.save();
    t.turn(135);
    t.trace(10);
    t.retrieve();

    t.save();
    t.turn(-90);
    t.trace(10);
    t.retrieve();

      List<Line> actualLines = t.getLines();
      List<Line> expectedLines = new ArrayList<>();

      expectedLines.add(new Line(
              new Position2D(0, 0),
              new Position2D(
                      10.0 * Math.cos(Math.toRadians(45.0)), 10.0 * Math.sin(Math.toRadians(45.0)))));

      expectedLines.add(new Line(
              new Position2D(0, 0),
              new Position2D(
                      10.0 * Math.cos(Math.toRadians(135.0)), 10.0 * Math.sin(Math.toRadians(135.0)))));

      expectedLines.add(new Line(
              new Position2D(0, 0),
              new Position2D(
                      10.0 * Math.cos(Math.toRadians(-90)), 10.0 * Math.sin(Math.toRadians(-90)))));

      assertEquals(expectedLines.size(), actualLines.size());

      for (int i = 0; i < expectedLines.size(); i++) {
        assertEquals(expectedLines.get(i), actualLines.get(i));
    }
  }

  @Test
  public void testInit() {
    TurtleModel t = new SimpleTurtle();

    assertEquals(0.0, t.getPosition().getX(), 0.001);
    assertEquals(0.0, t.getPosition().getY(), 0.001);
  }

  @Test
  public void testMove() {
    TurtleModel t = new SimpleTurtle();

    t.move(10);
    assertEquals(10.0, t.getPosition().getX(), 0.001);
    assertEquals(0.0, t.getPosition().getY(), 0.001);
  }

  @Test
  public void testTurnOnly() {
    TurtleModel t = new SimpleTurtle();

    t.turn(10);
    assertEquals(0.0, t.getPosition().getX(), 0.001);
    assertEquals(0.0, t.getPosition().getY(), 0.001);


    t.turn(-100);
    assertEquals(0.0, t.getPosition().getX(), 0.001);
    assertEquals(0.0, t.getPosition().getY(), 0.001);
  }

  @Test
  public void testTurnAndMove() {
    TurtleModel t = new SimpleTurtle();

    t.turn(30);
    t.move(10);
    assertEquals(10.0 * Math.cos(Math.toRadians(30)), t.getPosition().getX(), 0.001);
    assertEquals(10.0 * Math.sin(Math.toRadians(30)), t.getPosition().getY(), 0.001);
  }

  @Test
  public void testTrace() {
    TracingTurtleModel t = new SmarterTurtle();

    t.trace(10);
    List<Line> lines = t.getLines();
    assertEquals(1, lines.size());
    assertEquals(0.0, lines.get(0).start.getX(), 0.001);
    assertEquals(0.0, lines.get(0).start.getY(), 0.001);
    assertEquals(10.0, lines.get(0).end.getX(), 0.001);
    assertEquals(0.0, lines.get(0).end.getY(), 0.001);
  }

  @Test
  public void testMultipleTrace() {
    TracingTurtleModel t = new SmarterTurtle();
    List<Line> expectedLines = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      t.turn(90);
      t.trace(10);
      t.turn(-90);
      t.trace(10);
      t.turn(-90);
      t.trace(10);
      t.turn(90);
      t.trace(10);
      expectedLines.add(new Line(new Position2D(20 * i, 0),
                                 new Position2D(20 * i, 10)));

      expectedLines.add(new Line(new Position2D(20 * i, 10),
                                 new Position2D(20 * i + 10, 10)));

      expectedLines.add(new Line(new Position2D(20 * i + 10, 10),
                                 new Position2D(20 * i + 10, 0)));

      expectedLines.add(new Line(new Position2D(20 * i + 10, 0),
                                 new Position2D(20 * i + 20, 0)));
    }

    List<Line> actualLines = t.getLines();
    assertEquals(expectedLines.size(), actualLines.size());
    for (int i = 0; i < expectedLines.size(); i++) {
      assertEquals(expectedLines.get(i), actualLines.get(i));
    }
  }

  @Test
  public void testPush() {
    TurtleModel t = new SimpleTurtle();

    t.move(10);
    Position2D initPosition = t.getPosition();
    t.save();
    t.turn(90);
    t.move(10);
    t.retrieve();
    Position2D finalPosition = t.getPosition();
    assertEquals(initPosition.getX(), finalPosition.getX(), 0.01);
    assertEquals(finalPosition.getY(), finalPosition.getY(), 0.01);
  }

}