package cs3500.turtle.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;

import cs3500.turtle.model.Position2D;
import cs3500.turtle.tracingmodel.Line;

/**
 * The view interface. To motivate the methods here
 * think about all the operations that the controller
 * would need to invoke on the view
 */
public interface IView {
  /**
   * Make the view visible. This is usually called
   * after the view is constructed
   */
  void makeVisible();

  /**
   * Provide the view with an action listener for
   * the button that should cause the program to
   * process a command. This is so that when the button
   * is pressed, control goes to the action listener
   *
   * @param actionEvent
   */
  void setCommandButtonListener(ActionListener actionEvent);

  /**
   * Get the command typed by the user
   *
   * @return
   */
  String getTurtleCommand();

  /**
   * Provide the view with the lines to be drawn
   *
   * @param lines
   */
  void setLines(List<Line> lines);

  /**
   * Provide the view with the current position
   * of the turtle, presumably to show it
   *
   * @param pos
   */
  void setTurtlePosition(Position2D pos);

  /**
   * Provide the view with the current heading
   * of the turtle, presumably to show it
   *
   * @param headingDegrees
   */
  void setTurtleHeading(double headingDegrees);

  /**
   * Transmit an error message to the view, in case
   * the command could not be processed correctly
   *
   * @param error
   */
  void showErrorMessage(String error);

  /**
   * Signal the view to draw itself
   */
  void refresh();
}
