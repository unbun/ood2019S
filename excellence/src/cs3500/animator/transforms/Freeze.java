package cs3500.animator.transforms;

import cs3500.animator.shapes.LiveShape;

import java.util.function.Function;

public class Freeze extends ATransform {

    public Freeze(int startTime, int endTime) {
        super(TransformType.FREEZE, startTime, endTime);
    }

    @Override
    public Function<LiveShape, LiveShape> func() {
        return ls -> ls;
    }
}
