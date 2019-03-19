package cs3500.animator.shapes;

import cs3500.animator.transforms.Transform;
import cs3500.animator.util.Posn;
import cs3500.animator.util.Utils;

import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * Represents a Shape that can move/be animated. (In other words a Shape with built-in, trackable
 * mutation).
 */
public abstract class LiveShape implements Comparable<LiveShape> {

    final String name;
    ShapeClass sClass;
    private int height;
    private int width;
    private int heading; //angle in degrees
    private Posn posn;
    private Color color;
    private List<Transform> transforms;

    /**
     * Copy constructor
     */
    public LiveShape(LiveShape toCopy) {
        name = toCopy.name;
        height = toCopy.height;
        width = toCopy.width;
        heading = toCopy.heading;
        posn = toCopy.posn;
        color = toCopy.color;
        transforms = toCopy.transforms;
        sClass = toCopy.sClass;
        this.transforms.sort(Comparator.comparingInt(Transform::startTime));
    }

    public LiveShape(String name, ShapeClass sClass) {
        this(0, 0, 0, new Posn(0, 0), Color.white, name, sClass);
    }

    /**
     * Constructs an AbstractShape.
     *
     * @param height the height of the shape
     * @param width  the width of the shape
     * @param posn   the position of the shape
     * @param color  the color of the shape
     * @param name   the name of the shape
     * @param sClass
     */
    public LiveShape(int height, int width, int heading, Posn posn, Color color,
                     String name, ShapeClass sClass, Transform... transforms) {
        this(height, width, heading, posn, color, name, sClass,
                new ArrayList<>(Arrays.asList(Objects.requireNonNull(transforms))));
    }

    /**
     * Constructs an AbstractShape.
     *
     * @param height the height of the shape
     * @param width  the width of the shape
     * @param posn   the position of the shape
     * @param color  the color of the shape
     * @param name   the name of the shape
     * @param sClass
     */
    public LiveShape(int height, int width, int heading, Posn posn, Color color,
                     String name, ShapeClass sClass, List<Transform> transforms) {
        this.height = Utils.requireNonNegative(height, "height");
        this.width = Utils.requireNonNegative(width, "width");
        this.heading = heading;
        this.posn = Objects.requireNonNull(posn, "posn cannot be null");
        this.color = Objects.requireNonNull(color, "color cannot be null");
        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.sClass = Objects.requireNonNull(sClass, "shape class cannot be null");
        this.transforms = Objects.requireNonNull(transforms);
        this.transforms.sort(Comparator.comparingInt(Transform::startTime));
    }


    public abstract LiveShape copy();

    public LiveShape transform(int height, int width, int heading, Posn posn, Color color)
            throws IllegalArgumentException {
        LiveShape result = this.copy();
        result.height = Utils.requireNonNegative(height, "new height");
        result.width = Utils.requireNonNegative(width, "new height");
        result.heading = heading;
        result.posn = posn;
        result.color = color;
        return result;
    }

    public LiveShape transform(int height, int width, Posn posn, Color color)
            throws IllegalArgumentException {
        return this.transform(height, width, this.heading, posn, color);
    }

    public void addTransforms(Transform... transforms) {
        this.transforms.addAll(Arrays.asList(transforms));
        this.transforms.sort(Comparator.comparingInt(Transform::startTime));
    }

    public LiveShape moveTo(Posn p) {
        return this.transform(this.height, this.width, this.heading, p, this.color);
    }

    public LiveShape moveTo(int x, int y) {
        return this.moveTo(new Posn(x, y));
    }

    public LiveShape turnTo(int heading) {
        return this.transform(this.height, this.width, heading, this.posn, this.color);
    }

    public LiveShape resizeH(int height) {
        return this.resize(height, this.width);
    }

    public LiveShape resizeW(int width) {
        return this.resize(this.height, width);
    }

    public LiveShape resize(int height, int width) {
        return this.transform(height, width, this.heading, this.posn, this.color);
    }

    public LiveShape recolor(Color color) {
        return this.transform(this.height, this.width, this.heading, this.posn, color);
    }

    public LiveShape recolor(int r, int g, int b) {
        return this.recolor(new Color(r, g, b));
    }

    public LiveShape recolorRed(int r) {
        return this.recolor(r, color.getGreen(), color.getBlue());
    }

    public LiveShape recolorGreen(int g) {
        return this.recolor(color.getRed(), g, color.getBlue());
    }

    public LiveShape recolorBlue(int b) {
        return this.recolor(color.getRed(), color.getGreen(), b);
    }

    //////////////////////////////////////
    ////////////// GETTERS ///////////////
    //////////////////////////////////////

    public String textTag() {
        return String.format("%s %s", name, sClass.textName());
    }

    public String svgTag() {
        return String.format("%s id=\"%s\"", sClass.svgName(), name);
    }

    public List<Transform> getTransforms() {
        return new ArrayList<>(this.transforms);
    }

    public String getName() {
        return this.name;
    }

    public String textFields() {
        //x  y theta  w  h  r  g  b

        return String.format("%d %d %d %d %d %d %d %d",
                posn.x, posn.y, heading, width, height, color.getRed(), color.getGreen(), color.getBlue());
    }

    public String svgFields() {
        return "";
    }

    //////////////////////////////////////
    ////////// OBJECT OVERRIDES //////////
    //////////////////////////////////////


    @Override
    public int compareTo(LiveShape o) {
        return this.textTag().compareTo(o.textTag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(heading, name, width, height, posn.x, posn.y,
                color.getRed(), color.getBlue(), color.getGreen());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof LiveShape) {
            return this.hashCode() == obj.hashCode();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("name=%s, height=%d, width=%d, heading=%d, "
                        + "posn={%d, %d}, color={%d,%d,%d}",
                name, height, width, heading, posn.x, posn.y,
                color.getRed(), color.getGreen(), color.getBlue());
    }


}
