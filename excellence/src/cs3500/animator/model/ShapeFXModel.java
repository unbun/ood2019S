package cs3500.animator.model;


import cs3500.animator.shapes.Oval;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.transforms.MoveTo;
import cs3500.animator.transforms.Recolor;
import cs3500.animator.transforms.Scale;
import cs3500.animator.transforms.Transform;


import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs3500.animator.shapes.IShape;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.Constants;
import cs3500.animator.util.Posn;
import cs3500.animator.util.Utils;


/**
 * The implementation of the AnimatorModel interface. Consists of the model of the Easy Animator.
 * Implements the public methods as specified in the IAnimationModel interface.
 */
public class ShapeFXModel implements AnimationModel {
  private List<IShape> shapeList;
  private List<Transform> operationsList;
  public int width = Constants.CANVAS_WIDTH;
  public int height = Constants.CANVAS_HEIGHT;
  public Posn topLeft = new Posn(0,0);
  private int tickRate = 25;

  /**
   * IAnimationModel default constructor. Default speed is 1 tick/second.
   */
  public ShapeFXModel() {
    this.shapeList = new ArrayList<>();
    this.operationsList = new ArrayList<>();
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public void setBounds(int x, int y, int width, int height) {
    this.topLeft = new Posn(x,y);
    this.width = width;
    this.height = height;
  }

    /**
   * Builder given to us to create the model for the animation.
   */
  public static final class Builder implements AnimationBuilder<AnimationModel> {
    ShapeFXModel model = new ShapeFXModel();

      @Override
      public AnimationModel build() {
        return model;
      }

      @Override
      public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
        model.setBounds(x,y,width, height);
        return this;
      }

      @Override
      public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
        switch (type){
          case "rectangle" : model.createShape(new Rectangle(name, new Posn(0, 0), Color.BLUE,
              0, 500, 10, 10));
            break;
          case "oval" :model.createShape(new Oval(name, new Posn(0, 0), Color.RED, 0,
              500, 10, 10));
            break;
          case "ellipse" :model.createShape(new Oval(name, new Posn(0, 0), Color.RED, 0,
                  500, 10, 10));
            break;
          default: throw new IllegalArgumentException(type + " is not a valid shape type");
        }
        return this;
      }

      @Override
      public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
        boolean isMoved = (x1 != x2) || (y1 != y2);
        boolean isScaled = (h1 != h2) || (w1 != w2);
        boolean isRecolored = (r1 != r2) || (g1 != g2) || (b1 != b2);
        if(isMoved){
          model.createTransform(new MoveTo(name, t1, t2, new Posn(x2, y2)));
        }

        if(isScaled){
          model.createTransform(new Scale(name, t1, t2, w2, h2));
        }

        if(isRecolored){
          model.createTransform(new Recolor(name, t1, t2, new Color(r2, g2, b2)));
        }
        return this;
      }

      @Override
      public AnimationBuilder<AnimationModel> addKeyframe(String name, int t, int x, int y, int w, int h, int r, int g, int b) {
        throw new UnsupportedOperationException("Please do not use key frames for animations");
      }
    }



  @Override
  public void createShape(IShape shape) {
    this.shapeList.add(shape);
  }

  @Override
  public void removeShape(IShape shape) throws IllegalArgumentException {
    if (shapeList.contains(shape)) {
      this.shapeList.remove(shape);
    } else {
      throw new IllegalArgumentException("Shape is not in this animation.");
    }
  }

  @Override
  public void createTransform(Transform trns) {
    this.operationsList.add(trns);
    for (IShape s : this.getShapes()) {
      if (trns.getName().equals(s.getName())) {
        trns.apply(s);
      }
    }
  }

  @Override
  public void setSpeed(int speed) {
    this.tickRate = Utils.requirePositive(speed);
  }



  @Override
  public ArrayList<IShape> getShapes() {
    return new ArrayList<>(shapeList);
  }

  @Override
  public int getTickRate() {
    return this.tickRate;
  }

  @Override
  public int getEndTime() {
    ArrayList<Integer> shapeTimes = new ArrayList<>();
    ArrayList<Transform> allOps = new ArrayList<>();
    ArrayList<Integer> opsTimes = new ArrayList<>();

    shapeTimes.add(0);
    for (IShape s : this.shapeList) {
      shapeTimes.add(s.getDeathTime());
    }
    int shapesEndTime = Collections.max(shapeTimes);


    for (int i = 0; i < this.shapeList.size(); i++) {
      for (Transform o : shapeList.get(i).getOperations()) {
        allOps.add(o);
      }
    }

    opsTimes.add(0);
    for (Transform op : allOps) {
      opsTimes.add(op.getEndTime());
    }
    int opsEndTime = Collections.max(opsTimes);
    return Math.max(shapesEndTime, opsEndTime);
  }
}



