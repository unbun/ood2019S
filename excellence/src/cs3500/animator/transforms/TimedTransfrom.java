package cs3500.animator.transforms;


import cs3500.animator.shapes.LiveShape;
import java.util.Objects;
import java.util.function.Function;

public abstract class TimedTransfrom implements Transform{

  private int startTime;
  private int endTime;
  private TransformType type;

  TimedTransfrom(TransformType type, int startTime, int endTime) throws IllegalArgumentException {

    if(startTime > endTime){
      throw new IllegalArgumentException("Start time must be before endtime");
    }

    this.type = Objects.requireNonNull(type);
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public boolean active(int time){
    return time >= startTime && time < endTime;
  }

  public String typeStr(){
    return type.descriptor();
  }

  public int sortRank(){
    return startTime;
  }

}
