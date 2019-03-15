package cs3500.animator.transforms;

import cs3500.animator.shapes.LiveShape;
import java.util.function.Function;

public class Resize extends TimedTransfrom {

  int height;
  int width;

  public Resize(int startTime, int endTime, int height, int width) {
    super(TransformType.RESIZE, startTime, endTime);
    this.height = height;
    this.width = width;
  }

  @Override
  public Function<LiveShape, LiveShape> func(int time) {
    if(active(time)){
      return ls -> ls.resize(height, width);
    } else {
      return liveShape -> liveShape;
    }
  }
}
