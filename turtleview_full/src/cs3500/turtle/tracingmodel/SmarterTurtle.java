package cs3500.turtle.tracingmodel;

import java.util.ArrayList;
import java.util.List;

import cs3500.turtle.model.Position2D;
import cs3500.turtle.model.SimpleTurtle;

/**
 * Created by blerner on 10/10/16.
 */
public class SmarterTurtle extends SimpleTurtle implements TracingTurtleModel {
  public SmarterTurtle() {
    super();
    lines = new ArrayList<>();
  }


  @Override
  public void trace(double distance) {
    Position2D cur = this.getPosition();
    move(distance);
    lines.add(new Line(cur, this.getPosition()));
  }

  @Override
  public List<Line> getLines() {
    return new ArrayList<>(lines);
  }

  //list of lines traced since this object was created
  List<Line> lines;

}

