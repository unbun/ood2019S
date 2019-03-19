package cs3500.animator.model;

import cs3500.animator.shapes.LiveShape;
import cs3500.animator.shapes.Oval;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.shapes.Triangle;
import cs3500.animator.transforms.AllTransform;
import cs3500.animator.transforms.Transform;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.Posn;

import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * <p>Represents an implementation of an animator model with various shapefx that can be altered,
 * grown, and moved at various speeds at various times in various ways. Tha transforms are listed in
 * order of time (if there is a tie, then the first action to be added to the model will go).</p>
 * <p>
 * Invariants:
 * <li>
 * <ul>1) The period of the timer is set to a given rate, and a TimerTask is scheduled once a
 * period.</ul>
 * <ul>2) The map of strings is mapped so that each shape id is the key and each shape is the
 * value</ul>
 * <ul>3) A {@code LiveShape} cannot have the same kind of animator at the same time.</ul>
 * <ul>4) Once the timer starts, the transforms will continually update until they are done or the
 * timer has stopped</ul>
 * <ul>5) Every motion's myShape exists within the class's list of {@code LiveShape}a</ul>
 * </li>
 * </p>
 */
public abstract class ShapeFXModel<V> implements AnimationModel<V> {

    final int CANVAS_HEGHT;
    final int CANVAS_WIDTH;
    final Posn topLeftCorner;
    protected List<LiveShape> shapes;
    private Timer t;
    private int rate;
    private int currTime;

    public ShapeFXModel(int rate) {
        this(rate, new ArrayList<>(), 300, 300, new Posn(0, 0));
    }

    public ShapeFXModel(int rate, LiveShape... shapes) {
        this(rate, Arrays.asList(shapes), 300, 300, new Posn(0, 0));
    }

    public ShapeFXModel(int rate, List<LiveShape> shapes, int cHeight, int cWidth, Posn topLeftCorner) {

        if (shapes.stream().map(LiveShape::textTag).distinct().count() != (long) shapes.size()) {
            throw new IllegalArgumentException(
                    "Shapes list cannot contain shapes with the same ids (types + name)");
        }

        this.shapes = Objects.requireNonNull(shapes);
        this.t = new Timer(true);
        this.rate = rate;
        this.currTime = -1;

        this.CANVAS_HEGHT = cHeight;
        this.CANVAS_WIDTH = cWidth;
        this.topLeftCorner = topLeftCorner;
    }

    @Override
    public void addShape(LiveShape ls) {
        if (!this.shapes.contains(ls)) {
            this.shapes.add(ls);
        }
    }

    @Override
    public void addMotion(String shapeName, Transform t) {
        for (LiveShape ls : this.shapes) {
            if (ls.getName().equals(shapeName)) {
                ls.addTransforms(t);
            }
        }
    }

    ///////////////////////////////////////////
    ////////////// TIMER METHODS //////////////
    ///////////////////////////////////////////

    @Override
    public int getTime() {
        return currTime;
    }

    @Override
    public void restart() {
        currTime = 0;
    }

    @Override
    public void start() {
        if (currTime != -1) {
            throw new IllegalStateException("Cannot start a timer that is already running");
        }

        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                currTime++;
            }
        }, 0, rate);
    }

    @Override
    public void stop() {
        t.cancel();
    }

    public static final class Builder implements AnimationBuilder<TextFXModel> {

        private TextFXModel model;

        public Builder(TextFXModel model) {
            this.model = model;
        }

        @Override
        public TextFXModel build() {
            return this.model;
        }

        @Override
        public AnimationBuilder<TextFXModel> setBounds(int x, int y, int width, int height) {
            this.model = new TextFXModel(50, new Posn(x, y), height, width);
            return this;
        }

        @Override
        public AnimationBuilder<TextFXModel> declareShape(String name, String type) {
            switch (type) {
                case "rectangle":
                    this.model.addShape(new Rectangle(name));
                    break;
                case "triangle":
                    this.model.addShape(new Triangle(name));
                    break;
                case "oval":
                    this.model.addShape(new Oval(name));
                    break;
                default:
                    throw new IllegalArgumentException(type + "is not a valid shape");
            }

            return this;
        }

        @Override
        public AnimationBuilder<TextFXModel> addMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
            Transform transform = new AllTransform(t1, t2, new Posn(x2, y2), w2, h2, new Color(r2, g2, b2), new Posn(x2, y2), w2, h2, new Color(r2, g2, b2));
            this.model.addMotion(name, transform);
            return this;
        }

        @Override
        public AnimationBuilder<TextFXModel> addKeyframe(String name, int t, int x, int y, int w, int h, int r, int g, int b) {
            return this;
        }
    }
}




