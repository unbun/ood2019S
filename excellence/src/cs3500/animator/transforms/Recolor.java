package cs3500.animator.transforms;

import cs3500.animator.shapes.LiveShape;
import java.awt.Color;
import java.util.function.Function;

public class Recolor extends TimedTransfrom {

  Color c;

  public Recolor(int startTime, int endTime, Color c) {
    super(TransformType.RECOLOR, startTime, endTime);
    this.c = c;
  }

  @Override
  public Function<LiveShape, LiveShape> func(int time) {
    if(active(time)){
      return ls -> ls.recolor(c);
    } else {
      return liveShape -> liveShape;
    }
  }
}
