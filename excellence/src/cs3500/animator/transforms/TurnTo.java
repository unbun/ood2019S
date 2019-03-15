package cs3500.animator.transforms;

import cs3500.animator.shapes.LiveShape;
import java.util.function.Function;

public class TurnTo extends TimedTransfrom {


  int heading;

  public TurnTo(int startTime, int endTime, int heading) {
    super(TransformType.TURN, startTime, endTime);
    this.heading = heading;
  }

  @Override
  public Function<LiveShape, LiveShape> func(int time) {
    if(active(time)){
      return ls -> ls.turnTo(heading);
    } else {
      return liveShape -> liveShape;
    }
  }
}
