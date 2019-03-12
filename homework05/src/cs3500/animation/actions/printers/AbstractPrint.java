package cs3500.animation.actions.printers;

import cs3500.animation.actions.AbstractTransform;
import cs3500.animation.actions.ActionType;
import cs3500.animation.shapes.LiveShape;
import java.io.IOException;
import java.util.Objects;


/**
 * <p>Design Justification: For now, we are just implementing the model, which only needs to keep
 * track of the state of each animation. But we need an output to ensure that the model is properly
 * animating by providing some output. So we created these {@code AbstractPrint}'shape. If model has
 * {@code AbstractPrint} actions, updating the state of such actions will cause them to output to an
 * {@code Appendable} stream. </p>
 *
 * <p> When we implement the view, it will handle outputs, instead of the actions directly. That is
 * why we have a very loosely defined Transform Interface </p>
 */
public abstract class AbstractPrint extends AbstractTransform {

  protected Appendable output;
  boolean printed;

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
    this.printed = false;
  }

  /**
   * Print the current state of the shape, mutate it, and print the new state.
   *
   * @param currTime the time to mutate and output.
   */
  public void apply(int currTime) {
    if (started(currTime) && !finished(currTime) && !printed) {
      String header = this.type.getDescriptor() + " " + this.shape.getName();

      String start = this.startTime + " " + this.shape.getDescription();
      this.applyHelp();
      String end = this.endTime + " " + this.shape.getDescription();

      println(header + ":\t " + start + "\t| " + end);
      this.printed = true;
    }
  }

  /**
   * The method that actually mutates the shape of the class.
   */
  public abstract void applyHelp();


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
    StringBuilder out = new StringBuilder(":\t");
    if (finished(currTime)) {
      out.append("finished");
    } else if (started(currTime)) {
      out.append(String.format("running[%s/%s secs]",
          (currTime - startTime), (endTime - startTime)));
    } else {
      out.append("queued");
    }

    out.append("\n");
    return out.toString();
  }
}
