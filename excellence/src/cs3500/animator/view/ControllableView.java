package cs3500.animator.view;


import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.util.Constants;


/**
 * The implementation of the edit view that allows users to control the animation with buttons and  the export of a svg file
 * from a visual view for the animation.
 */
public class ControllableView extends JFrame implements IAnimationView, ActionListener {
  private AnimationPanel animationPanel;
  private Appendable ap;

  private AnimationModel model;
  private Timer timer;

  private JButton playButton;
  private String playButtonTitle;
  private JButton restartButton;
  private JCheckBox loopCheckBox;
  private JButton speedUpButton;
  private JButton slowDownButton;
  private JLabel currentSpeed;

  private JButton exportSVG;

  /**
   * Default constructor. Adds buttons and scroll bar to view. Sets panels onto this view, which
   * extends JFrame in Java Swing.
   */
  public ControllableView() {
    super();
    this.animationPanel = new AnimationPanel();
    this.setTitle("Excellence in Animation!");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    // sets gridLayout
    this.setLayout(new GridLayout());

    // adds animation panel to this frame
    animationPanel.setPreferredSize(new Dimension(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT));
    this.add(animationPanel);

    // adds scroll bars to panel
    JScrollPane scrollBar = new JScrollPane(this.animationPanel,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.add(scrollBar);

    // play and pause button, adds to view
    this.playButtonTitle = "Press to play";
    this.playButton = new JButton(this.playButtonTitle);
    animationPanel.add(this.playButton, BorderLayout.NORTH);

    // restart button, adds to view
    this.restartButton = new JButton("Press to restart");
    animationPanel.add(this.restartButton, BorderLayout.NORTH);

    // loop checkbox
    this.loopCheckBox = new JCheckBox("Toggle looping");
    animationPanel.add(this.loopCheckBox, BorderLayout.NORTH);

    // export svg button
    this.exportSVG = new JButton("Click to export as SVG");
    animationPanel.add(this.exportSVG, BorderLayout.NORTH);

    // speed up button
    this.speedUpButton = new JButton("Press to speed up");
    animationPanel.add(this.speedUpButton, BorderLayout.NORTH);

    // slow down button
    this.slowDownButton = new JButton("Press to slow down");
    animationPanel.add(this.slowDownButton, BorderLayout.NORTH);

    this.currentSpeed = new JLabel("Rate: " + animationPanel.getRate() + "x");
    animationPanel.add(this.currentSpeed, BorderLayout.NORTH);

    this.setVisible(true);

    this.pack();
  }

  public AnimationModel getModel() {
    return this.model;
  }

  /**
   * Getter for play/pause JButton.
   *
   * @return the playButton
   */
  public JButton getPlayButton() {
    return this.playButton;
  }

  /**
   * Getter for restart JButton.
   *
   * @return the restartButton
   */
  public JButton getRestartButton() {
    return this.restartButton;
  }

  /**
   * Getter for looping checkbox.
   *
   * @return the loopCheckBox
   */
  public JCheckBox getLoopCheckBox() {
    return this.loopCheckBox;
  }

  /**
   * Getter for speed up JButton.
   *
   * @return the speedUpButton
   */
  public JButton getSpeedUpButton() {
    return this.speedUpButton;
  }

  /**
   * Getter for slow down JButton.
   *
   * @return the slowDownButton
   */
  public JButton getSlowDownButton() {
    return this.slowDownButton;
  }

  public JLabel getCurrentSpeedLabel(){
    return this.currentSpeed;
  }

  /**
   * Getter for export SVG JButton.
   *
   * @return the exportSVG button
   */
  public JButton getExportSVG() {
    return this.exportSVG;
  }

  /**
   * Sets the title of the play button to the given string.
   *
   * @param s the new title of the button
   */
  public void setPlayButtonTitle(String s) {
    this.playButton.setText(s);
    this.playButtonTitle = s;
  }

  /**
   * Getter for the title of the play button.
   *
   * @return the title of the play button
   *
   */
  public String getPlayButtonTitle() {
    return this.playButtonTitle;
  }

  @Override
  public ViewType getViewType() {
    return ViewType.HYBRID;
  }

  @Override
  public String makeView(AnimationModel model) {
    this.model = model;
    this.timer = new Timer(1000 / this.model.getTickRate(), this);
    timer.start();
    return "";
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public AnimationPanel getAnimationPanel() {
    return this.animationPanel;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    repaint();
  }


}
