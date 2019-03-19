package cs3500.animator.shapes;

import cs3500.animator.transforms.Transform;
import cs3500.animator.util.Posn;

import java.awt.*;

/**
 * Represents an oval.
 */
public final class Oval extends LiveShape {


    /**
     * Copy Constructor for Oval
     */
    public Oval(Oval copy) {
        super(copy);
    }

    public Oval(String name) {
        super(name, ShapeClass.OVAL);
    }

    /**
     * A Movable Oval.
     *
     * @param height  height of oval
     * @param width   width of oval
     * @param heading angle the myShape is heading
     * @param x       x of the position of the myShape
     * @param y       y of the positoin of the myShape
     * @param color   the color
     * @param name    the name/id
     */
    public Oval(int height, int width, int heading, int x, int y, Color color,
                String name) {
        super(height, width, heading, new Posn(x, y), color, name, ShapeClass.OVAL);
    }

    /**
     * A Movable Oval.
     *
     * @param height  height of oval
     * @param width   width of oval
     * @param heading angle the oval is heading
     * @param posn    the position of the oval
     * @param color   the color
     * @param name    the name/id
     */
    public Oval(int height, int width, int heading, Posn posn, Color color,
                String name) {
        super(height, width, heading, posn, color, name, ShapeClass.OVAL);
    }

    /**
     * A Movable Oval.
     *
     * @param height     height of Oval
     * @param width      width of Oval
     * @param heading    angle the oval is heading
     * @param posn       the position of the Oval
     * @param color      the color
     * @param name       the name/id
     * @param transforms a list of transforms
     */
    public Oval(int height, int width, int heading, Posn posn, Color color,
                String name, Transform... transforms) {
        super(height, width, heading, posn, color, name, ShapeClass.OVAL, transforms);
    }

    @Override
    public LiveShape copy() {
        return new Oval(this);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Oval) {
            return super.equals(obj);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 31 * "oval".hashCode();
    }

    @Override
    public String toString() {
        return "Oval@[" + super.toString() + "]";
    }
}

