package cs3500.turtle.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs3500.turtle.model.Position2D;
import cs3500.turtle.tracingmodel.Line;

/**
 * This is an implementation of the IView interface
 * that uses Java Swing to draw the results of the
 * turtle. It shows any error messages using a
 * pop-up dialog box, and shows the turtle position
 * and heading
 */
public class TurtleGraphicsView extends JFrame implements IView {
  private JButton commandButton, quitButton;
  private JPanel buttonPanel;
  private TurtlePanel turtlePanel;
  private JTextField input;
  private JLabel display;

  public TurtleGraphicsView() {
    super();
    this.setTitle("Turtles!");
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //use a borderlayout with drawing panel in center and button panel in south
    this.setLayout(new BorderLayout());
    turtlePanel = new TurtlePanel();
    turtlePanel.setPreferredSize(new Dimension(500, 500));
    this.add(turtlePanel, BorderLayout.CENTER);

    //button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    //input textfield
    input = new JTextField(15);
    buttonPanel.add(input);

    //buttons
    commandButton = new JButton("Execute");
    buttonPanel.add(commandButton);

    //quit button
    quitButton = new JButton("Quit");
    quitButton.addActionListener((ActionEvent e) -> {
      System.exit(0);
    });
    buttonPanel.add(quitButton);

    this.pack();


  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }


  @Override
  public void setCommandButtonListener(ActionListener actionEvent) {
    commandButton.addActionListener(actionEvent);
  }

  @Override
  public String getTurtleCommand() {
    String command = this.input.getText();
    this.input.setText("");
    return command;
  }

  @Override
  public void setLines(List<Line> lines) {
    turtlePanel.setLines(lines);
  }

  @Override
  public void setTurtlePosition(Position2D pos) {
    turtlePanel
            .setTurtlePosition(pos);
  }

  @Override
  public void setTurtleHeading(double headingDegrees) {
    turtlePanel
            .setTurtleHeading(headingDegrees);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);

  }

}
