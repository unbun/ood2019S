package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.shapes.IShape;
import cs3500.animator.util.Constants;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Represents the JPanel where the visual animation will appear.
 */
public class AnimationPanel extends JPanel {

  boolean looping;
  boolean playing;
  private AnimationModel model;
  private int time;
  private double rate = 1;


  /**
   * Animation panel creator.
   */
  public AnimationPanel() {
    super();
    this.setBackground(Color.WHITE);
    this.time = 0;
    this.playing = false;
    this.looping = false;
  }

  public boolean isLooping() {
    return looping;
  }

  public void setLooping(boolean looping) {
    this.looping = looping;
  }

  public boolean isPlaying() {
    return playing;
  }

  public void setPlaying(boolean playing) {
    this.playing = playing;
  }

  /**
   * Getter for the model.
   *
   * @return the model
   */
  public AnimationModel getModel() {
    return this.model;
  }

  /**
   * Model setter.
   *
   * @param m model.
   */
  public void setModel(AnimationModel m) {
    this.model = m;
  }

  /**
   * Updates the frame to hold shapes at correct positions at each tick.
   *
   * @param g graphic being painted on
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (IShape s : model.getShapes()) {
      if (s.getType().equals("oval")) {
        if (time >= s.gett0()) {
          s = s.currState(time);
          g.setColor(new Color(s.getColor().getRed(), s.getColor().getGreen(),
              s.getColor().getBlue()));
          g.fillOval((int) s.getPosn().getX(), (int) s.getPosn().getY(), (int)
              s.getWidth(), (int) s.getHeight());
        }
      }
      if (s.getType().equals("rectangle")) {
        if (time >= s.gett0()) {
          s = s.currState(time);
          g.setColor(new Color(s.getColor().getRed(), s.getColor().getGreen(),
              s.getColor().getBlue()));
          g.fillRect((int) s.getPosn().getX(), (int) s.getPosn().getY(),
              (int) s.getWidth(), (int) s.getHeight());
        }
      }
    }
    if (this.looping && time > model.getEndTime()) {
      this.time = 0;
    } else if (time > model.getEndTime()) {
      time = model.getEndTime();
    }
    this.time += rate;
  }

  /**
   * Returns the current time.
   *
   * @return current time
   */
  public int getTime() {
    return this.time;
  }

  /**
   * Setter for time.
   *
   * @param time the time to change to
   */
  public void setTime(int time) {
    this.time = time;
  }

  /**
   * Slows the speed of the animation.
   */
  public void slowDownAnimation() {
    this.rate *= Constants.SLOWRATE;
  }

  /**
   * Increases the speed of the animation.
   */
  public void speedUpAnimation() {
    this.rate *= Constants.FASTRATE;
  }

  /**
   * Getter for rate of animation.
   *
   * @return rate
   */
  public double getRate() {
    return rate;
  }

}
