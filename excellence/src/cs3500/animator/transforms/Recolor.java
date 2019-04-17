package cs3500.animator.transforms;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.shapes.AShape;
import java.awt.Color;

/**
 * Represents a transformation which recolors a shape to a new color.
 */
public class Recolor extends ATransform {

  private Color color;

  /**
   * Default constructor.
   *
   * @param startTime the time at which the color change begins
   * @param endTime the time at which the color change ends
   * @param color the new color of the shape
   */
  public Recolor(String name, int startTime, int endTime, Color color) {
    super(name, TransformType.RECOLOR, startTime, endTime);
    this.color = color;
  }

  /**
   * Getter. Returns destination color.
   *
   * @return color.
   */
  public Color getColor() {
    return this.color;
  }


  @Override
  public String toText(AnimationModel model) {
    String output = getPreDescription(model.getTickRate());
    output += String
        .format("%d %.0f %.0f %.0f %.0f %d %d %d\n", (this.endTime / model.getTickRate()),
            this.shapes.get(0).getPosn().getX(), this.shapes.get(0).getPosn().getY(),
            this.shapes.get(0).getWidth(), this.shapes.get(0).getHeight(),
            this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    return output;
  }


  @Override
  public String commandToSVG(AShape s) {
    String output = "";
    output += "<animate attributeType=\"xml\" begin=\"" + (1000 * this.startTime)
        + "ms\" dur=\"" + (1000 * (this.endTime - this.startTime)) + "ms\"" +
        " attributeName=\"fill\"" +
        " from=\"RGB(" + (Math.round(this.shapes.get(0).getColor().getRed()))
        + "," + (Math.round(this.shapes.get(0).getColor().getGreen()))
        + "," + (Math.round(this.shapes.get(0).getColor().getBlue()))
        + ")\" to=\"" + "RGB(" + (Math.round(this.color.getRed())) + ","
        + (Math.round(this.color.getGreen())) + "," + (Math.round(this.color.getBlue())) + ")"
        + "\" fill=\"freeze\" />\n";
    return output;
  }
}