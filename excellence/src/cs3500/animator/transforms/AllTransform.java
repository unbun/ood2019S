package cs3500.animator.transforms;

import cs3500.animator.shapes.LiveShape;
import cs3500.animator.util.Posn;

import java.awt.*;
import java.util.function.Function;

public class AllTransform extends ATransform {

    private Posn p1;
    private int width1;
    private int height1;
    private Color color1;

    private Posn p2;
    private int width2;
    private int height2;
    private Color color2;

    public AllTransform(int startTime, int endTime,
                        Posn p1, int width1, int height1, Color color1,
                        Posn p2, int width2, int height2, Color color2) {
        super(TransformType.ALL, startTime, endTime);
        this.p1 = p1;
        this.width1 = width1;
        this.height1 = height1;
        this.color1 = color1;
        this.p2 = p2;
        this.width2 = width2;
        this.height2 = height2;
        this.color2 = color2;
    }

    @Override
    public Function<LiveShape, LiveShape> func() {
        return liveShape -> liveShape.transform(height2, width2, p2, color2);
    }

    @Override
    public LiveShape applyTimed(LiveShape ls, int time) {
        if (time < startTime()) {
            return ls;
        } else if (time >= startTime() && time < endTime()) {
            return ls.transform(height1, width1, p1, color1);
        } else {
            return this.func().apply(ls);
        }
    }
}
