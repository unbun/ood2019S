package cs3500.animation.animations.printers;

import cs3500.animation.animations.AbstractShapeAction;
import cs3500.animation.animations.ActionType;
import cs3500.animation.animations.InstantShapeAction;
import cs3500.animation.shapes.LiveShape;
import java.io.IOException;
import java.util.Objects;


/**
 * <p>Design Justification: For now, we are just implementing the model, which only needs to keep
 * track of the state of each animation. But we need an output to ensure that the model is properly
 * animating by providing some output. So we created these {@code AbstractPrint}'shape. If model has
 * {@code AbstractPrint} animations, updating the state of such animations will cause them to output
 * to an {@code Appendable} stream. </p>
 *
 * <p> When we implement the view, it will handle outputs, instead of the actions directly. That is
 * why we have a very loosely defined ShapeAction Interface </p>
 */
public abstract class AbstractPrint extends AbstractShapeAction {

  protected Appendable output;

  /**
   * Constructs an AbstractShapePrintAction with the given startTime and endTime to be applied to
   * the given shape in the given model.
   *
   * @param startTime the time at which the motion will begin
   * @param endTime the time at which the motion will end
   * @param shape the shape for the motion to be applied to
   */
  public AbstractPrint(Appendable output, ActionType type, LiveShape shape,
      int startTime, int endTime) {
    super(shape, startTime, endTime);
    this.output = Objects.requireNonNull(output);
    this.type = type;
  }

  /**
   * Print the current state of the shape, mutate it, and print the new state.
   *
   * @param currTime the time to mutate and output.
   */
  public void apply(int currTime) {
    if (started(currTime) && !finished(currTime)) {

      String header = this.type.getDescriptor() + " " + this.shape.getName();
      String start = this.startTime + " " + this.shape.getDescription();
      this.alterShape();
      String end = this.endTime + " " + this.shape.getDescription();

      println(header + ":\t " + start + "\t| " + end);
    }
  }

  @Override
  public boolean finished(int currTime) {
    return endTime <= currTime;
  }

  @Override
  public boolean started(int currTime) {
    return startTime <= currTime;
  }

  protected void println(String... strings) {
    try {
      for (String s : strings) {
        output.append(s);
        output.append("\n");
      }
    } catch (IOException e) {
      throw new IllegalStateException("Output stream is invalid");
    }
  }

  @Override
  public String stateString(int currTime) {
    StringBuilder out = new StringBuilder(shape.shapeToString() + ": ");
    if (finished(currTime)) {
      out.append("finished");
    } else if (started(currTime)) {
      out.append("started");
    } else {
      out.append("queued");
    }

    out.append("\n");
    return out.toString();
  }

  @Override
  public InstantShapeAction initCreateAction(int startTime) {
    return new PrintCreate(output, shape, startTime);
  }

  @Override
  public boolean isShapeInitalized() {
    return shape.isInitalized();
  }
}
