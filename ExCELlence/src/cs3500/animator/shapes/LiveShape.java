package cs3500.animator.shapes;

import cs3500.animator.transforms.Transform;
import cs3500.animator.transforms.shapefx.Create;
import cs3500.animator.util.Posn;
import cs3500.animator.util.Utils;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a Shape that can move/be animated. (In other words a Shape with built-in, trackable
 * mutation).
 */
public abstract class LiveShape implements Comparable<LiveShape> {

  final String name;
  private int height;
  private int width;
  private int heading; //angle in degrees
  private Posn posn;
  private Color color;

  private List<Transform<LiveShape>> transformList;

  /**
   * Copy constructor (does not need a Create transformation)
   * @param toCopy
   */
  public LiveShape(LiveShape toCopy){
    name = toCopy.name;
    height = toCopy.height;
    width = toCopy.width;
    heading = toCopy.heading;
    posn = toCopy.posn;
    color = toCopy.color;
  }

  /**
   * Constructs an AbstractShape.
   *
   * @param currTime the time of creation for this shape
   * @param height the height of the shape
   * @param width the width of the shape
   * @param posn the position of the shape
   * @param color the color of the shape
   * @param name the name of the shape
   */
  public LiveShape(int currTime, int height, int width, int heading, Posn posn, Color color,
      String name) {
    this.height = (Integer) Utils.requireNonNegative(height, "height");
    this.width = (Integer) Utils.requireNonNegative(width, "width");
    this.heading = heading;
    this.posn = Objects.requireNonNull(posn, "posn cannot be null");
    this.color = Objects.requireNonNull(color, "color cannot be null");
    this.name = Objects.requireNonNull(name, "name cannot be null");

    this.transformList = new ArrayList<>();
    transformList.add(new Create(currTime));
  }

  public abstract LiveShape copy();

  /**
   * A transforms given will only be added if they don't conflict with another transform for this
   * shape and if they are actually set for this shape.
   * @param transforms
   */
  public final void addTransforms(Transform<LiveShape> ... transforms){
    transformList.addAll(Arrays.asList(transforms));
  }

  /**
   * Get the state of this LiveShape at the given time (doesn't actually mutate {@code this} shape.
   * @param currTime
   * @return
   */
  public LiveShape update(int currTime){
    LiveShape toMutate = this.copy();

    for(Transform<LiveShape> t: transformList){
      toMutate = t.apply(this, currTime);
    }

    return toMutate;
  }

  ///////////////////////////////////////////
  ///////////// Build Helpers ///////////////
  ///////////////////////////////////////////

  /**
   *
   */
   public String buildTextDoc(){
     StringBuilder result = new StringBuilder();

     List<Transform<LiveShape>> sorted =
         transformList.stream().sorted(Comparator.comparingInt(Transform::startTime)).collect(
             Collectors.toList());

     for(Transform<LiveShape> t : sorted){
       result.append(t.textualStr());
       result.append("\n");
     }

     return result.toString();
   }

   /*
   public String buildSvgDoc(){

   }

   public List<LiveShape> buildJPanelDoc()
    */

  ///////////////////////////////////////////
  ///////////// String Getters //////////////
  ///////////////////////////////////////////

  /**
   * Returns a string identification of this myShape. (shape type + name)
   */
  public abstract String getID();

  /**
   * Returns the name of this shape
   * @return
   */
  public String getName(){
    return this.name;
  }

  /**
   * A string containing this Shape's field in the following format: "x y heading width height
   * color.red color.green color.blue"
   *
   * @return a formatted list of this Shape's field
   */
  public String stateStr() {
    return String
        .format("%d %d %d %d %d %d %d %d",
            this.posn.x, this.posn.y, this.heading,
            this.width, this.height,
            this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }

  ///////////////////////////////
  ////////// MUTATIONS //////////
  ///////////////////////////////

  /**
   * Move the myShape to the given positoin.
   *
   * @param endPoint the position to move through
   */
  public void moveTo(Posn endPoint) {
    this.posn = Objects.requireNonNull(endPoint);
  }

  /**
   * Turn the myShape to head in the given angle.
   *
   * @param endHeading the angle to turn to
   */
  public void turnTo(int endHeading) {
    this.heading = endHeading;
  }

  /**
   * Change the color of the myShape to the given color.
   *
   * @param c the color to change to
   */
  public void recolor(Color c) {
    this.color = c;
  }

  /**
   * Change the size of the myShape by the given factors.
   *
   * @param xFactor the factor to change the width by
   * @param yFactor the factor to change the height by
   */
  public void scale(double xFactor, double yFactor) {
    this.height *= (Double) Utils.requireNonNegative(yFactor, "yFactor");
    this.width *= (Double) Utils.requireNonNegative(xFactor, "xFactor");
  }

  //////////////////////////////////////
  ////////// OBJECT HANDLING ///////////
  //////////////////////////////////////


  @Override
  public int compareTo(LiveShape o) {
    return this.getID().compareTo(o.getID());
  }

  @Override
  public int hashCode() {
    return Objects.hash(heading, name, width, height, posn.x, posn.y,
        color.getRed(), color.getBlue(), color.getGreen());
  }

  @Override
  public boolean equals(Object obj) {
    if(this == obj){
      return true;
    } else if (obj instanceof LiveShape) {
      return this.hashCode() == obj.hashCode();
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return String.format("name=%s, width=%d, height=%d, heading=%d, "
            + "posn={%d, %d}, color={%d,%d,%d}",
        name, width, height, heading, posn.x, posn.y,
        color.getRed(), color.getGreen(), color.getBlue());
  }


}
