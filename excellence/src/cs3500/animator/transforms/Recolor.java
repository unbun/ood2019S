package cs3500.animator.transforms;

import cs3500.animator.shapes.LiveShape;

import java.awt.*;
import java.util.function.Function;

public class Recolor extends ATransform {

    private Color c;

    public Recolor(int startTime, int endTime, Color c) {
        super(TransformType.RECOLOR, startTime, endTime);
        this.c = c;
    }

    @Override
    public Function<LiveShape, LiveShape> func() {
        return ls -> ls.recolor(c);
    }
}
