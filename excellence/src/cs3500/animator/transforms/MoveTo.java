package cs3500.animator.transforms;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.shapes.AShape;
import cs3500.animator.util.Posn;

/**
 * Represents a transformation which moves a shape to given location.
 */
public class MoveTo extends ATransform {

  private Posn dest;

  /**
   * Default constructor.
   *
   * @param startTime the time at which the move begins
   * @param endTime the time at which the move  ends
   * @param dest the dest position of the shape
   */
  public MoveTo(String name, int startTime, int endTime, Posn dest) {
    super(name, TransformType.MOVE, startTime, endTime);
    this.dest = dest;
  }

  @Override
  public String toText(AnimationModel model) throws IllegalArgumentException {
    String output = getPreDescription(model.getTickRate());
    output += String
        .format("%d %.0f %.0f %.0f %.0f %d %d %d\n", (this.endTime / model.getTickRate()),
            this.dest.getX(), this.dest.getY(),
            this.shapes.get(0).getWidth(), this.shapes.get(0).getHeight(),
            this.shapes.get(0).getColor().getRed(), this.shapes.get(0).getColor().getGreen(),
            this.shapes.get(0).getColor().getBlue());
    return output;
  }

  @Override
  public String commandToSVG(AShape s) {
    int starttime = startTime;
    String start = starttime * 1000 + "ms";
    int dur = endTime - startTime;
    String duration = dur * 1000 + "ms";
    String out = "    <animate attributeType=\"xml \"begin=\"" + start + " dur=\"" + "\""
        + duration + "\""
        + " attributeName=\"" + s.xFieldSVG() + "\"\n" + "from=\"" + s.getPosn().x + "\" to=\"" +
        dest.x + "\""
        + "fill=\"freeze\"\n" + "/>\n" + "    <animate attributeType=\"xml \"begin=\"" + start
        + " dur=\"" + "\""
        + duration + "\""
        + " attributeName=\"" + s.yFieldSVG() + "\"\n" + "from=\"" + s.getPosn().y + "\" to=\""
        + dest.y + "\""
        + " fill=\"freeze\"\n" + "/>\n";
    return out;
  }

  /**
   * Gets destination posn of this transform.
   *
   * @return dest.
   */
  public Posn getDest() {
    return this.dest;
  }

}