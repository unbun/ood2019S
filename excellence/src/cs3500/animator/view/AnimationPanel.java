package cs3500.animator.view;

import java.awt.Color;

import java.awt.Graphics;
import java.util.Objects;

import javax.swing.JPanel;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.shapes.IShape;
import cs3500.animator.util.Constants;

/**
 * This panel represents the region where the animation will occur. An instance of a
 * JPanel in Java Swing.
 */
public class AnimationPanel extends JPanel {
  private AnimationModel model;
  private int time;

  boolean looping;
  boolean playing;

  private double rate = 1;
  private int lowRateCount = 0;


  /**
   * Animation panel creator. Default constructor.
   */
  public AnimationPanel() {
    super();
    this.setBackground(Color.WHITE);
    this.time = 0;
    this.playing = false;
    this.looping = false;
  }

  public void setLooping(boolean looping) {
    this.looping = looping;
  }

  public void setPlaying(boolean playing) {
    this.playing = playing;
  }

  public boolean isLooping() {
    return looping;
  }

  public boolean isPlaying() {
    return playing;
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
   * Setter for time.
   *
   * @param time the time to change to
   */
  public void setTime(int time) {
    this.time = time;
  }

  /**
   * Getter for the model.
   *
   * @return the model set up
   */
  public AnimationModel getModel() {
    return this.model;
  }

  /**
   * Override of the paintComponent method. Updates the frame to hold shapes at correct positions at
   * each tick.
   *
   * @param g graphic
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if(Objects.isNull(model)){
      return;
    }

    for (IShape s : model.getShapes()) {
      if (s.getType().equals("oval")) {
        if (time >= s.getBirthTime() && time < s.getDeathTime()) {
          s = s.getStateAt(time);

          g.setColor(new Color(s.getColor().getRed(), s.getColor().getGreen(),
                  s.getColor().getBlue()));

          g.fillOval((int) s.getPosn().getX(), (int) s.getPosn().getY(), (int)
                  s.getWidth(), (int) s.getHeight());
        }
      }

      if (s.getType().equals("rectangle")) {
        if (time >= s.getBirthTime() && time < s.getDeathTime()) {
          s = s.getStateAt(time);

          g.setColor(new Color(s.getColor().getRed(), s.getColor().getGreen(),
                  s.getColor().getBlue()));
          g.fillRect((int) s.getPosn().getX(), (int) s.getPosn().getY(),
                  (int) s.getWidth(), (int) s.getHeight());
        }
      }
    }

    if (this.looping && time > model.getEndTime()) {
      this.time = 0;
    } else if (time > model.getEndTime()){
      time = model.getEndTime();
    }

    if (this.playing) {
//      System.out.println(time + ", r=" + rate + ", lrc=" + lowRateCount);

      if(rate < 1){
        lowRateCount ++;
        if(lowRateCount > (1/rate)){
          this.time++;
          lowRateCount = 0;
        }
      } else {
        this.time += rate;
      }
    }
  }

  /**
   * Returns the current time.
   *
   * @return current time
   */
  public int getTime() {
    return this.time;
  }

  public void slowDownRate(){
    this.rate *= Constants.RATE_FACTOR_SLOWER;
  }

  public void speedUpRate(){
    this.rate *= Constants.RATE_FACTOR_SPEEDY;
  }

  public double getRate(){
    return rate;
  }

  /**
   * Increase time by factor of 50. Useful for future fast forward.
   */
  public void increaseTime() {
    this.time = time + (this.model.getTickRate() / 10);
  }

  /**
   * Decrease time by factor of -5. Useful for future rewind function.
   */
  public void decreaseTime() {
    this.time = time - 5;
  }
}
