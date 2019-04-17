package cs3500.animator.transforms;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.shapes.AShape;


/**
 * Change the height and width of a Shape.
 */
public class Resize extends ATransform {

  private float xFactor;
  private float yFactor;

  /**
   * Default constructor.
   *
   * @param startTime the time at which the size change begins
   * @param endTime the time at which the size change ends
   * @param xFactor the factor by which the width changes
   * @param yFactor the factor by which the height changes
   */
  public Resize(String name, int startTime, int endTime, float xFactor, float yFactor) {
    super(name, TransformType.RESIZE, startTime, endTime);
    this.xFactor = xFactor;
    this.yFactor = yFactor;
  }

  /**
   * Gets the change in x.
   *
   * @return the change in x.
   */
  public float getxFactor() {
    return this.xFactor;
  }

  /**
   * Returns the change in y.
   *
   * @return the change in y.
   */
  public float getyFactor() {
    return this.yFactor;
  }

  @Override
  public String toText(AnimationModel model) {
    String result = getPreDescription(model.getTickRate());
    result += String
        .format("%d %.0f %.0f %.0f %.0f %d %d %d\n", (this.endTime / model.getTickRate()),
            this.shapes.get(0).getPosn().getX(), this.shapes.get(0).getPosn().getY(),
            this.shapes.get(0).getWidth() + this.xFactor,
            this.shapes.get(0).getHeight() + this.yFactor,
            this.shapes.get(0).getColor().getRed(), this.shapes.get(0).getColor().getGreen(),
            this.shapes.get(0).getColor().getBlue());
    return result;
  }

  @Override
  public String commandToSVG(AShape s) {
    int starttime = startTime;
    String start = starttime * 1000 + "ms";
    int dur = endTime - startTime;
    String duration = dur * 1000 + "ms";
    String out = "    <animate attributeType=\"xml \"begin=\"" + start + " dur=\"" + "\""
        + duration + "\""
        + " attributeName=\"" + s.scaleX() + "\"\n" + "from=\"" + s.getWidth() + "\" to=\"" +
        (xFactor + s.getWidth()) + "\""
        + "fill=\"freeze\"\n" + "/>\n" + "    <animate attributeType=\"xml \"begin=\"" + start
        + " dur=\"" + "\""
        + duration + "\""
        + " attributeName=\"" + s.scaleY() + "\"\n" + "from=\"" + s.getHeight() + "\" to=\"" +
        (yFactor + s.getHeight()) + "\""
        + " fill=\"freeze\"\n" + "/>\n";
    return out;
  }
}