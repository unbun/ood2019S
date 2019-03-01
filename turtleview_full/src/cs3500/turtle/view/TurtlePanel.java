package cs3500.turtle.view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs3500.turtle.model.Position2D;
import cs3500.turtle.tracingmodel.Line;

/**
 * This panel represents the region where the
 * lines of the turtle must be drawn.
 * <p>
 * If one has to create a container that makes
 * custom drawing, the conventional way is to
 * create a class that extends JPanel or JLabel
 */
public class TurtlePanel extends JPanel {
  private List<Line> lines;
  private Position2D turtlePosition;
  private double turtleHeading;
  //the rectangle within which all lines lie
  private Position2D minD, maxD;


  public TurtlePanel() {
    super();
    lines = new ArrayList<Line>();
    this.setBackground(Color.WHITE);
    minD = new Position2D(0, 0);
    maxD = new Position2D(0, 0);
    turtlePosition = new Position2D(0, 0);
    turtleHeading = 0.0;

  }

  public void setTurtlePosition(Position2D pos) {
    turtlePosition = new Position2D(pos);
  }

  public void setTurtleHeading(double h) {
    turtleHeading = h;
  }

  public void setLines(List<Line> lines) {
    this.lines = new ArrayList<Line>(lines);
    List<Position2D> points = new ArrayList<Position2D>();
    for (Line l : this.lines) {
      points.add(new Position2D(l.start));
      points.add(new Position2D(l.end));
    }
    if (points.size() > 0) {

      minD = points.get(0);
      maxD = points.get(1);

      for (Position2D p : points) {
        if (p.getX() < minD.getX()) {
          minD = new Position2D(p.getX(), minD.getY());
        }
        if (p.getY() > minD.getY()) {
          minD = new Position2D(minD.getX(), p.getY());
        }
      }

      for (Position2D p : points) {
        if (p.getX() > maxD.getX()) {
          maxD = new Position2D(p.getX(), maxD.getY());
        }
        if (p.getY() > maxD.getY()) {
          maxD = new Position2D(maxD.getX(), p.getY());
        }
      }
    }
  }

  /**
   * Override the paintComponent method of the JPanel
   * Do NOT override paint!
   *
   * @param g
   */

  @Override
  protected void paintComponent(Graphics g) {
    //never forget to call super.paintComponent!
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.BLACK);

    /*
    the origin of the panel is top left. In order
    to make the origin bottom left, we must "flip" the
    y coordinates so that y = height - y

    We do that by using an affine transform. The flip
    can be specified as scaling y by -1 and then
    translating by height.
     */

    AffineTransform originalTransform = g2d.getTransform();

    //the order of transforms is bottom-to-top
    //so as a result of the two lines below,
    //each y will first be scaled, and then translated
    g2d.translate(0, this.getPreferredSize().getHeight());
    g2d.scale(1, -1);

    for (Line l : lines) {
      Position2D start = l.start;
      Position2D end = l.end;
      g2d.drawLine((int) start.getX(), (int) start.getY(),
              (int) end.getX(), (int) end.getY());
    }

    //draw the turtle
    //an easy way to draw the turtle would be
    //to draw it in its default position, and then
    //rotate it by heading and translating it to
    //its actual position
    g2d.translate(Math.round(turtlePosition.getX()), Math.round(
            turtlePosition.getY
                    ()));
    g2d.rotate(Math.toRadians(turtleHeading));
    g2d.setColor(Color.BLUE);
    g2d.fillOval(-2, -2, 4, 4);
    g2d.setColor(Color.BLACK);
    g2d.fillOval(-1, -1, 2, 2);
    g2d.setColor(Color.RED);
    g2d.fillOval(-8, -4, 8, 8);

    //reset the transform to what it was!
    g2d.setTransform(originalTransform);
  }
}
