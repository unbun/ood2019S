package cs3500.animator.transforms;

import cs3500.animator.shapes.LiveShape;

import java.util.function.Function;

public class Resize extends ATransform {

    private int height;
    private int width;

    public Resize(int startTime, int endTime, int height, int width) {
        super(TransformType.RESIZE, startTime, endTime);
        this.height = height;
        this.width = width;
    }

    @Override
    public Function<LiveShape, LiveShape> func() {
        return ls -> ls.resize(height, width);
    }
}
