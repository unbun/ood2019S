package cs3500.animation.animations.printers;

import cs3500.animation.animations.ActionType;
import cs3500.animation.animations.InstantShapeAction;
import cs3500.animation.animations.ShapeAction;
import cs3500.animation.shapes.LiveShape;

public class PrintCreate extends AbstractPrint implements InstantShapeAction {

  boolean occured;

  public PrintCreate(Appendable output,
      LiveShape shape, int startTime) {
    super(output, ActionType.CREATE, shape, startTime, startTime + 1);
    this.occured = false;
  }

  @Override
  public void alterShape() {
    return;
  }

  @Override
  public void apply(int currTime) {
    if(!occured && currTime >= startTime){
      println(String.format("shape %s", this.shape.shapeToString()));
      this.occured = true;
    }
  }

  @Override
  public boolean finished(int currTime) {
    return occured();
  }

  @Override
  public boolean started(int currTime) {
    return currTime >= startTime;
  }

  @Override
  public boolean occured() {
    return occured;
  }

  @Override
  public String stateString(int currTime) {
    return "Create " + super.stateString(currTime);
  }

  @Override
  public boolean conflict(ShapeAction other) {
    return false;
  }
}
