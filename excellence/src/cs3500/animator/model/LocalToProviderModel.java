package cs3500.animator.model;

import cs3500.animator.provider.model.PAnimatorModel;
import cs3500.animator.provider.model.ReadOnlyModel;
import cs3500.animator.shapes.IShape;
import cs3500.animator.shapes.Oval;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.transforms.MoveTo;
import cs3500.animator.transforms.Recolor;
import cs3500.animator.transforms.Resize;
import cs3500.animator.transforms.Transform;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.Posn;
import java.awt.Color;
import java.util.ArrayList;

/**
 * The providers did not provide a Controller or any equivalent interface (such as an
 * ActionListener), and didn't really provide much customer support on that end.
 *
 * <p>Therefore, we decided the best course of action would be to try to adapt our model interfaces
 * to theirs, That way, it behaves as the providers likely intended it to. Now it is compatiable
 * with our controllers and the providers views (although our controllers might need some adapting
 * as well, which will really only be done through the clues in the README and trial and error).
 */
public class LocalToProviderModel extends ShapeFXModel implements PAnimatorModel, ReadOnlyModel {

  @Override
  public void addAMotion(String shapeName, int t1, int x1, int y1, int w1, int h1, int r1, int g1,
      int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    boolean ismoveTo = (x1 != x2) || (y1 != y2);
    boolean isResize = (h1 != h2) || (w1 != w2);
    boolean isRecolor = (r1 != r2) || (g1 != g2) || (b1 != b2);
    if (ismoveTo) {
      this.createTransform(new MoveTo(shapeName, t1, t2, new Posn(x2, y2)));
    }
    if (isResize) {
      this.createTransform(new Resize(shapeName, t1, t2, w2, h2));
    }
    if (isRecolor) {
      this.createTransform(new Recolor(shapeName, t1, t2, new Color(r2, g2, b2)));
    }
  }

  /**
   * Add A motion from alread formed shapes.
   *
   * @param t1 the time of the first shape state
   * @param s1 the first shape state
   * @param t2 the time of the second shape state
   * @param s2 the second shape state
   */
  public void addAMotion(int t1, IShape s1, int t2, IShape s2) {
    addAMotion(s1.getName(), t1, (int) s1.getPosn().x, (int) s1.getPosn().y, (int) s1.getWidth(),
        (int) s1.getHeight(),
        s1.getColor().getRed(), s1.getColor().getGreen(), s1.getColor().getBlue(),
        t2, (int) s2.getPosn().x, (int) s2.getPosn().y, (int) s2.getWidth(), (int) s2.getHeight(),
        s2.getColor().getRed(), s2.getColor().getGreen(), s2.getColor().getBlue());
  }

  @Override
  public void declareAShape(String shapeName, String shapeType) {
    switch (shapeType) {
      case "oval":
        this.createShape(new Oval(shapeName));
        break;
      case "ellipse":
        this.createShape(new Oval(shapeName));
        break;
      case "rectangle":
        this.createShape(new Rectangle(shapeName));
        break;
      case "rect":
        this.createShape(new Rectangle(shapeName));
        break;
      default: //don't crash, just don't do anything in case it was a typo
        break;
    }
  }

  @Override
  public void initCanvas(int x, int y, int width, int height) {
    this.setBounds(x, y, width, height);
  }

  @Override
  public void removeAShape(String shapeName) {
    IShape toRemove = null;
    for (IShape s : this.getShapes()) {
      if (s.getName().equalsIgnoreCase(shapeName)) {
        toRemove = s;
        break;
      }
    }
    this.removeShape(toRemove);
  }

  @Override
  public ArrayList<IShape> fillAllFrames() {
    for (IShape s : this.getShapes()) {
      for (Transform t : s.getTransformations()) {
        int initTime = t.getStartTime();
        IShape initShape = s.currState(t.getStartTime());
        int midTime = ((t.getEndTime() - t.getStartTime()) / 2) + t.getStartTime();
        IShape midShape = s.currState(midTime);
        this.addAMotion(initTime, initShape, midTime, midShape);
      }
    }
    return this.getShapes();
  }

  @Override
  public int getWholeCanvasWidth() {
    return this.width;
  }

  @Override
  public int getWholeCanvasHeight() {
    return this.height;
  }

  @Override
  public int findBeginningTime() {
    return 0;
  }

  @Override
  public int findEndTime() {
    return this.getEndTime();
  }

  @Override
  public int getCanX() {
    return this.width / 2;
  }

  @Override
  public int getCanY() {
    return this.height / 2;
  }

  @Override
  public int getCanWidth() {
    return this.width;
  }

  @Override
  public int getCanHeight() {
    return this.height;
  }


  /**
   * Builder to facilitate the creation of a model to represent an animation.
   */
  public static final class Builder implements AnimationBuilder<AnimationModel> {

    LocalToProviderModel model = new LocalToProviderModel();

    @Override
    public AnimationModel build() {
      return model;
    }

    @Override
    public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      model.setBounds(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
      switch (type) {
        case "rectangle":
          model.createShape(new Rectangle(name, new Posn(0, 0), Color.BLUE,
              0, 10, 10));
          break;
        case "oval":
          model.createShape(new Oval(name, new Posn(0, 0), Color.RED, 0,
              10, 10));
          break;
        case "ellipse":
          model.createShape(new Oval(name, new Posn(0, 0), Color.RED, 0,
              10, 10));
          break;
        default:
          throw new IllegalArgumentException(type + " is not a valid shape type");
      }
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
        int b2) {
      boolean ismoveTo = (x1 != x2) || (y1 != y2);
      boolean isResize = (h1 != h2) || (w1 != w2);
      boolean isRecolor = (r1 != r2) || (g1 != g2) || (b1 != b2);
      if (ismoveTo) {
        model.createTransform(new MoveTo(name, t1, t2, new Posn(x2, y2)));
      }
      if (isResize) {
        model.createTransform(new Resize(name, t1, t2, w2, h2));
      }
      if (isRecolor) {
        model.createTransform(new Recolor(name, t1, t2, new Color(r2, g2, b2)));
      }
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addKeyframe(String name, int t, int x, int y, int w,
        int h, int r, int g, int b) {
      model.createKeyFrame(name, t, x, y, w, h, r, g, b);
      return this;
    }
  }
}
