package cs3500.animator.shapes;

import cs3500.animator.transforms.Transform;
import cs3500.animator.util.Posn;

import java.awt.*;

/**
 * Represents a triangle.
 */
public final class Triangle extends LiveShape {

    /**
     * Copy Constructor for Oval
     */
    public Triangle(Triangle copy) {
        super(copy);
    }

    public Triangle(String name) {
        super(name, ShapeClass.TRIANGLE);
    }

    /**
     * A Movable Triangle.
     *
     * @param height  height of Triangle
     * @param width   width of Triangle
     * @param heading angle the myShape is heading
     * @param x       x of the position of the myShape
     * @param y       y of the positoin of the myShape
     * @param color   the color
     * @param name    the name/id
     */
    public Triangle(int height, int width, int heading, int x, int y, Color color,
                    String name) {
        super(height, width, heading, new Posn(x, y), color, name, ShapeClass.TRIANGLE);
    }

    /**
     * A Movable Triangle.
     *
     * @param height  height of triangle
     * @param width   width of triangle
     * @param heading angle the triangle is heading
     * @param posn    the position of the triangle
     * @param color   the color
     * @param name    the name/id
     */
    public Triangle(int height, int width, int heading, Posn posn, Color color,
                    String name) {
        super(height, width, heading, posn, color, name, ShapeClass.TRIANGLE);
    }

    /**
     * A Movable Triangle.
     *
     * @param height     height of Triangle
     * @param width      width of Triangle
     * @param heading    angle the oval is heading
     * @param posn       the position of the Triangle
     * @param color      the color
     * @param name       the name/id
     * @param transforms a list of transforms
     */
    public Triangle(int height, int width, int heading, Posn posn, Color color,
                    String name, Transform... transforms) {
        super(height, width, heading, posn, color, name, ShapeClass.TRIANGLE, transforms);
    }

    @Override
    public LiveShape copy() {
        return new Triangle(this);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Triangle) {
            return super.equals(obj);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 31 * "triangle".hashCode();
    }

    @Override
    public String toString() {
        return "Triangle@[" + super.toString() + "]";
    }

}

